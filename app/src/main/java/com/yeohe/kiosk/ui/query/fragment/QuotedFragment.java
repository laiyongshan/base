package com.yeohe.kiosk.ui.query.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.volley.VolleyError;
import com.ccj.base.base.Constants;
import com.ccj.base.utils.TLog;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.base.utils.router.RouterUtils;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.adapter.IllegalAdapter;
import com.yeohe.kiosk.bean.FirstEvent;
import com.yeohe.kiosk.bean.Violation;
import com.yeohe.kiosk.http.URLs;
import com.yeohe.kiosk.ui.dialog.KonwDialog;
import com.yeohe.kiosk.utils.EncryptUtil;
import com.yeohe.kiosk.utils.UIHelper;
import com.yeohe.kiosk.vollery.VolleyInterface;
import com.yeohe.kiosk.vollery.VolleyUtil;
import com.yeohe.kiosk.widgets.MyListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;
import okhttp3.Call;
import okhttp3.Request;

@SuppressLint("ValidFragment")
public class QuotedFragment extends Fragment {

    private IllegalAdapter illegalAdapter;
    private Violation violation;
    private View view;

    @BindView(R.id.quotes_illegal_lv)
    MyListView quotes_illegal_lv;

    @BindView(R.id.no_illegal_layout)
    LinearLayout no_illegal_layout;

    @BindView(R.id.commit_layout)
    RelativeLayout commit_layout;

    @BindView(R.id.quoted_commit_btn)
    RoundTextView quoted_commit_btn;


    @BindView(R.id.count_illegal_choose_tv)
    TextView count_illegal_choose_tv;

    private SelectBrocastReceiver selectBrocastReceiver;
    private IntentFilter selectIntentFilter;
    public final static String SELECTED_ACTION2 = "com.youhecheguanjia.quotedillegal_select";
    private final int TYPE = 2;

    List<Integer> listItemQuotedID = new ArrayList<Integer>();//待报价
    private List<Violation.DataEntity.PeccancyListEntity> peccancyList;

    public QuotedFragment(Violation violation) {
        this.violation = violation;

        peccancyList = violation.getData().getPeccancyList();
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_quoted, null);
        ButterKnife.bind(this, view);

        //注册EventBus
        EventBus.getDefault().register(this);

        selectIntentFilter = new IntentFilter(SELECTED_ACTION2);
        selectBrocastReceiver = new SelectBrocastReceiver();
        getActivity().registerReceiver(selectBrocastReceiver, selectIntentFilter);

        initView();//初始化控件

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus

        if (selectBrocastReceiver != null) {
            getActivity().unregisterReceiver(selectBrocastReceiver);
        }
    }

    KonwDialog konwDialog;

    @OnClick({R.id.quoted_commit_btn})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.quoted_commit_btn:
                konwDialog = new KonwDialog(getActivity(), R.style.Dialog, new KonwDialog.AgreeInterface() {
                    @Override
                    public void isAgree(boolean isagree) {
                        if (isagree) {
                            //同意
                            commitOrder();
                            konwDialog.dismiss();
                        } else {
                            konwDialog.dismiss();
                        }
                    }
                });//用户须知显示
                konwDialog.show();
                break;

        }
    }


    //提交订单
    public void commitOrder() {
        if (listItemQuotedID.size() == 0) {
//                    Toast.makeText(IllegalQueryActivty.this,"未选择任何违章或违章已全部提交，用户可以到主界面的\"订单查询\"页面中查看",Toast.LENGTH_LONG).show();
            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("提 示")
                    .setContentText(getString(R.string.dialog_commit_tips))
                    .setConfirmText("确 定")
                    .show();
        } else {
            if (listItemQuotedID.size() != 0) {
                commitType3Order();
            }
        }
    }

    /**
     * 待报价订单提交
     */
    public void commitType3Order() {
        getPeccancyids();//获取选中的违章Id
        if (!listItemQuotedID.isEmpty()) {
            commitQuotedOrder(commitOrderParams(peccancyidsquoted), URLs.COMMIT_QUOTED_PRICE_ORDER);
        }
    }

    String peccancyidsquoted="";//待报价违章id拼成
    List<Violation> order_violations=new ArrayList<Violation>();//提交的订单列表
    public String  getPeccancyids() {
        listItemQuotedID.clear();
        for (int i = 0; i < illegalAdapter.mChecked.size(); i++) {
            if (illegalAdapter.mChecked.get(i)) {
                if (peccancyList.get(i).getIscommit() == 1 &&
                        (!(peccancyList.get(i).getDegree().equals("0") &&peccancyList.get(i).getPrice() == 0 && peccancyList.get(i).getQuotedprice().equals("2")))) {
                    if (peccancyList.get(i).getQuotedprice().equals("2")){
                        listItemQuotedID.add(i);
                    }
                }
            }
        }

        order_violations.clear();
        peccancyidsquoted="";
        for(int i=0;i<listItemQuotedID.size();i++){
            if (i == listItemQuotedID.size() - 1) {
                peccancyidsquoted += peccancyList.get(listItemQuotedID.get(i)).getId();
            } else {
                peccancyidsquoted += peccancyList.get(listItemQuotedID.get(i)).getId() + ",";
            }
        }

        return peccancyidsquoted;
    }

    /*
    * 非本人本车提交订单请求参数
    * */
    HashMap map=new HashMap();
    public HashMap commitOrderParams(String peccancyids){
        map=new HashMap<String,Object>();
        String token = Constants.token;
        map.put("token",token);
        map.put("carid",Constants.carid);
        map.put("peccancyids",peccancyids);//违章ID拼成
        map.put("totalprice",totalprice+"");//支付总金额
        return map;
    }


    /**
     * 提交订单
     * */
    public String ordercodequoted="";//待报价订单编号
    public void commitQuotedOrder(HashMap map, final String url) {


        OkHttpUtils.post().url(url).params(EncryptUtil.encrypt(map)).build().execute(new StringCallback() {
            @Override
            public void onBefore(Request request, int id) {
                UIHelper.showPd(getActivity());
            }

            @Override
            public void onAfter(int id) {
                UIHelper.dismissPd();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
                TLog.logI(e.toString());
                Toast.makeText(getActivity(), "网络请求错误，请检查网络连接设置！", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onResponse(String response, int id) {
                TLog.logI(EncryptUtil.decryptJson(response, getActivity()) + "");
                try {
                    JSONObject obj = new JSONObject(EncryptUtil.decryptJson(response.toString(), getActivity()));
//                    JSONObject dataObj = obj.getJSONObject("data");
                    String status = obj.getString("status");
//                    int code = obj.getInt("code");
//                    if (status.equals("fail")) {
//                        if (dataObj.has("code")) {
//                            code = dataObj.getInt("code");
//                        }
//                    }
//
//                    if (url.equals(URLs.COMMIT_QUOTED_PRICE_ORDER)) {//待报价订单
//
//                        UIHelper.showErrTips(code, getActivity());//提示错误信息
//
//                        if (code == 0) {
//                            Toast.makeText(getActivity(), "提交订单成功", Toast.LENGTH_SHORT).show();
//                        } else {
//                            Toast.makeText(getActivity(), "请求发生异常，提交订单失败", Toast.LENGTH_SHORT).show();
//                        }
//                    }
                    if (status.equals("ok")) {
                        if (url.equals(URLs.COMMIT_QUOTED_PRICE_ORDER)) {//待报价订单
//                            RouterUtils.navigation(RouterConstants.ORDER_STYLE_MOUDEL_ACTIVITY);
                            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("提交成功")
                                    .setContentText(getString(R.string.dialog_wait_pay_tips))
                                    .setConfirmText("确 定")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            EventBus.getDefault().post(new FirstEvent("finish"));
                                        }
                                    })
                                    .show();
                        }
                    } else if (status.equals("fail")) {
//                        String errormsg = dataObj.getString("errormsg");
                        String show_msg=obj.optString("show_msg");
                        Toast.makeText(getActivity(), "订单提交失败，" + show_msg, Toast.LENGTH_LONG).show();
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("提交失败")
                                .setContentText(""+show_msg)
                                .setConfirmText("确定")
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "请求发生异常，提交订单失败", Toast.LENGTH_SHORT).show();
                } finally {

                }
            }

        });


    }



    @Subscribe
    public void onEvent(FirstEvent event) {
    /* Do something */
        if (event.getMsg().equals("2")) {
            no_illegal_layout.setVisibility(View.VISIBLE);
            commit_layout.setVisibility(View.GONE);
        }
    }


    int count;
    int totalDegree;
    float totalprice = 0;//总金额

    private void initView() {

        illegalAdapter = new IllegalAdapter(getActivity(), violation, 2);
        quotes_illegal_lv.setAdapter(illegalAdapter);

        listItemQuotedID.clear();
        for (int i = 0; i < illegalAdapter.mChecked.size(); i++) {
            if (illegalAdapter.mChecked.get(i)) {
                if ((peccancyList.get(i).getIscommit() == 1) && (!(peccancyList.get(i).getDegree().equals("0")
                        && peccancyList.get(i).getPrice() == 0 && peccancyList.get(i).getQuotedprice().equals("2")))) {
                    if (peccancyList.get(i).getQuotedprice().equals("2")) {
                        listItemQuotedID.add(i);
                    }
                }
            }
        }

        for (Violation.DataEntity.PeccancyListEntity peccancyListEntity : violation.getData().getPeccancyList()) {
            if (peccancyListEntity.getQuotedprice().equals("2") &&
                    peccancyListEntity.getIscommit() == 1 &&
                    (!(peccancyListEntity.getDegree().equals("0") && peccancyListEntity.getPrice() == 0 && peccancyListEntity.getQuotedprice().equals("2")))) {
                count++;
                totalDegree += Integer.valueOf(peccancyListEntity.getDegree());
            }
        }

        count_illegal_choose_tv.setText("" + count);

    }


    class SelectBrocastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            int quotedpriceType = intent.getIntExtra("quotedprice", 0);
            int isSelect = intent.getIntExtra("isSelect", -1);

            listItemQuotedID.clear();
            for (int i = 0; i < illegalAdapter.mChecked.size(); i++) {
                if (illegalAdapter.mChecked.get(i)) {
                    if (peccancyList.get(i).getIscommit() == 1 &&
                            (!(peccancyList.get(i).getDegree().equals("0") && peccancyList.get(i).getPrice() == 0 && peccancyList.get(i).getQuotedprice().equals("2"))))
                        if (peccancyList.get(i).getQuotedprice().equals("2")) {
                            listItemQuotedID.add(i);
                        }
                }
            }

            if ((listItemQuotedID.size() == 0)) {
                quoted_commit_btn.setClickable(false);
                quoted_commit_btn.setEnabled(false);
//                quoted_commit_btn.setBackgroundResource(R.drawable.tijiaoa2);
            } else {
                quoted_commit_btn.setClickable(true);
                quoted_commit_btn.setEnabled(true);
//                quoted_commit_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.tijiao_bg));
            }

            count_illegal_choose_tv.setText(listItemQuotedID.size() + "");//已选多少条违章
        }
    }


}