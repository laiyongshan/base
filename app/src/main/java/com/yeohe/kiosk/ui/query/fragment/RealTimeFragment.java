package com.yeohe.kiosk.ui.query.fragment;

import android.annotation.SuppressLint;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.volley.VolleyError;
import com.ccj.base.base.Constants;
import com.ccj.base.utils.DialogCreator;
import com.ccj.base.utils.TLog;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.base.utils.router.RouterUtils;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.adapter.IllegalAdapter;
import com.yeohe.kiosk.bean.FirstEvent;
import com.yeohe.kiosk.bean.Violation;
import com.yeohe.kiosk.http.URLs;
import com.yeohe.kiosk.ui.annual.AnnualActivity;
import com.yeohe.kiosk.ui.dialog.KonwDialog;
import com.yeohe.kiosk.ui.pay.PayActivity;
import com.yeohe.kiosk.utils.EncryptUtil;
import com.yeohe.kiosk.utils.GsonUtil;
import com.yeohe.kiosk.utils.UIHelper;
import com.yeohe.kiosk.vollery.VolleyInterface;
import com.yeohe.kiosk.vollery.VolleyUtil;
import com.yeohe.kiosk.widgets.MyListView;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
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

/**
 * Created by Administrator on 2017/4/20 0020.
 */

@SuppressLint("ValidFragment")
public class RealTimeFragment extends Fragment {
    private IllegalAdapter illegalAdapter;
    private Violation violation;
    private View view;

    @BindView(R.id.realtime_illegal_lv)
    ListView realtime_illegal_lv;

    @BindView(R.id.no_illegal_layout)
    LinearLayout no_illegal_layout;

    @BindView(R.id.commit_layout)
    RelativeLayout commit_layout;

    @BindView(R.id.price_tv)
    TextView price_tv;

    @BindView(R.id.sevice_fee_tv)
    TextView sevice_fee_tv;

    @BindView(R.id.count_illegal_choose_tv)
    TextView count_illegal_choose_tv;

    @BindView(R.id.real_commit_btn)
    RoundTextView real_commit_btn;

    List<Integer> listItemID = new ArrayList<Integer>();
    private List<Violation.DataEntity.PeccancyListEntity> peccancyList;

    private int searchtype = 0;


    private SelectBrocastReceiver selectBrocastReceiver;
    private IntentFilter selectIntentFilter;
    private final static String SELECTED_ACTION1 = "com.youhecheguanjia.illegallistselect";

    public RealTimeFragment(Violation violation, int searchtype) {
        this.violation = violation;
        peccancyList = violation.getData().getPeccancyList();
        this.searchtype = searchtype;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        view = inflater.inflate(R.layout.fragment_real_time, null);
        ButterKnife.bind(this, view);

        //注册EventBus
        EventBus.getDefault().register(this);


        selectIntentFilter = new IntentFilter(SELECTED_ACTION1);
        selectBrocastReceiver = new SelectBrocastReceiver();
        getActivity().registerReceiver(selectBrocastReceiver, selectIntentFilter);

        initView();

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


    @Subscribe
    public void onEvent(FirstEvent event) {
    /* Do something */
        if (event.getMsg().equals("1")) {
            no_illegal_layout.setVisibility(View.VISIBLE);
            commit_layout.setVisibility(View.GONE);
        }
    }


    int count;
    float totalServiceFee;//总服务费
    float totalPrice;//总费用
    float totalCount;//总罚款
    float totalLateFee;//总滞纳金
    int totalDegree;//总扣分

    private void initView() {
        illegalAdapter = new IllegalAdapter(getActivity(), violation, 1);
        realtime_illegal_lv.setAdapter(illegalAdapter);

        listItemID.clear();
        for (int i = 0; i < illegalAdapter.mChecked.size(); i++) {
            if (illegalAdapter.mChecked.get(i)) {
                if ((peccancyList.get(i).getIscommit() == 1) && (!(peccancyList.get(i).getDegree().equals("0")
                        && peccancyList.get(i).getPrice() == 0 && peccancyList.get(i).getQuotedprice().equals("1"))))
                    if (peccancyList.get(i).getQuotedprice().equals("1")) {
                        listItemID.add(i);
                    }
            }
        }

        for (Violation.DataEntity.PeccancyListEntity peccancyListEntity : peccancyList) {
            if (peccancyListEntity.getQuotedprice().equals("1") &&
                    peccancyListEntity.getIscommit() == 1 &&
                    (!(peccancyListEntity.getDegree().equals("0") && peccancyListEntity.getPrice() == 0 && peccancyListEntity.getQuotedprice().equals("1")))) {
                count++;
                totalServiceFee += (float) peccancyListEntity.getPrice();
                totalCount += Float.valueOf(peccancyListEntity.getCount());
                totalLateFee += Float.valueOf(peccancyListEntity.getLatefee());
                totalDegree += Integer.valueOf(peccancyListEntity.getDegree());

                TLog.logI(totalServiceFee + "\n" + (float) peccancyListEntity.getPrice());
                TLog.logI(totalDegree + "");
            }
        }

        totalPrice += (totalServiceFee + totalCount + totalLateFee);
        count_illegal_choose_tv.setText("" + count);
        price_tv.setText(totalPrice + "元");
        sevice_fee_tv.setText(totalServiceFee + "元");

    }

    KonwDialog konwDialog;

    @OnClick({R.id.real_commit_btn})
    public void click(View view) {
        switch (view.getId()) {
            case R.id.real_commit_btn:
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


    /**
     * 提交訂單
     */

    private void commitOrder() {
        if ((listItemID.size() == 0) && totalPrice == 0) {
//                    Toast.makeText(IllegalQueryActivty.this,"未选择任何违章或违章已全部提交，用户可以到主界面的\"订单查询\"页面中查看",Toast.LENGTH_LONG).show();
            new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                    .setTitleText("提 示")
                    .setContentText(getString(R.string.dialog_commit_tips))
                    .setConfirmText("确 定")
                    .show();
        } else {

//            if (addDravingLisenceTipsDialog != null) {
//                addDravingLisenceTipsDialog.dismiss();
//            }
//            addDravingLisenceTipsDialog = new AddDravingLisenceTipsDialog(getActivity(), R.style.Dialog,carid);
//
//            if (searchtype == 1 &&((IllegalQueryActivty)getActivity()).getIsNeedLisence() == 1) {
//
//                addDravingLisenceTipsDialog.show();
//            } else {
            if (searchtype == 1) {//代扣分订单提交
                if (!listItemID.isEmpty()) {
                    commitType1Order();
                }
            } else if (searchtype == 2) {//本人本车订单提交
                if (totalDegree >= 12) {
                    new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                            .setTitleText("提 示")
                            .setContentText("本人本车订单所选违章总扣分不应大于或等于12分")
                            .setConfirmText("确 定")
                            .show();
                } else {
                }
            }
//            }
        }
    }


    List<Violation.DataEntity.PeccancyListEntity> order_violations = new ArrayList<Violation.DataEntity.PeccancyListEntity>();
    String peccancyids = "";//违章id拼成

    //    AddDravingLisenceTipsDialog addDravingLisenceTipsDialog;//补充资料弹出框
    public String getPeccancyids() {
        listItemID.clear();
        for (int i = 0; i < illegalAdapter.mChecked.size(); i++) {
            if (illegalAdapter.mChecked.get(i)) {
                if (peccancyList.get(i).getIscommit() == 1 &&
                        (!(peccancyList.get(i).getDegree().equals("0") &&
                                peccancyList.get(i).getPrice() == 0 && peccancyList.get(i).getQuotedprice().equals("1"))))
                    if (peccancyList.get(i).getQuotedprice().equals("1")) {
                        listItemID.add(i);
                    }
            }
        }

        order_violations.clear();
        peccancyids = "";
        for (int i = 0; i < listItemID.size(); i++) {
            order_violations.add(peccancyList.get(listItemID.get(i)));
            if (i == listItemID.size() - 1) {
                peccancyids += peccancyList.get(listItemID.get(i)).getId();
            } else {
                peccancyids += peccancyList.get(listItemID.get(i)).getId() + ",";
            }
        }

        return peccancyids;
    }


    /**
     * 非本人本车订单提交
     */
    public void commitType1Order() {
//        getPeccancyids();//获取选中的违章Id
        if (!listItemID.isEmpty()) {
            commitOrder(commitOrderParams(getPeccancyids()), URLs.COMMIT_ORDER);
        }
    }

    HashMap map;

    /*
    * 非本人本车提交订单请求参数
    * */
    public HashMap commitOrderParams(String peccancyids) {
        map = new HashMap();
        String token = Constants.token;
        map.put("token", token);
        map.put("carid", Constants.carid);
        map.put("peccancyids", peccancyids);//违章ID拼成
        map.put("totalprice", totalPrice + "");//支付总金额
        return map;
    }


    /**
     * 提交订单
     */
    public String ordercode = "";//普通订单编号

    public void commitOrder(HashMap map, final String url) {

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
                    String status = obj.optString("status");
                    String show_msg = obj.optString("show_msg");

                    if (status.equals("ok")) {
                        if (url.equals(URLs.COMMIT_ORDER)) {//普通订单

                            JSONObject dataObj = obj.getJSONObject("data");
                            ordercode = dataObj.getString("ordercode");//得到普通订单号
                            TLog.logI("普通订单编号：" + ordercode);

                            new SweetAlertDialog(getActivity(), SweetAlertDialog.SUCCESS_TYPE)
                                    .setTitleText("提交成功")
                                    .setConfirmText("立即支付")
                                    .setConfirmClickListener(new SweetAlertDialog.OnSweetClickListener() {
                                        @Override
                                        public void onClick(SweetAlertDialog sDialog) {
                                            navigateToPay();
                                            EventBus.getDefault().post("finish");
                                        }
                                    })
                                    .show();

                            new Handler().postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    navigateToPay();
                                    EventBus.getDefault().post("finish");
                                }
                            },2000);
                        }
                    } else if (status.equals("fail")) {
                        String errormsg = obj.getString("show_msg");
                        Toast.makeText(getActivity(), "订单提交失败，" + errormsg, Toast.LENGTH_LONG).show();
                        new SweetAlertDialog(getActivity(), SweetAlertDialog.WARNING_TYPE)
                                .setTitleText("提交失败")
                                .setContentText("" + show_msg)
                                .setConfirmText("确定")
                                .show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    Toast.makeText(getActivity(), "请求发生异常，提交订单失败", Toast.LENGTH_SHORT).show();
                } finally {
                    TLog.logI(EncryptUtil.decryptJson(response.toString(), getActivity()));
                }
            }

        });
    }


    //跳转到支付
    private void navigateToPay(){
        Intent intent = new Intent(getActivity(), PayActivity.class);
        intent.putExtra("ordernumber", ordercode);
        intent.putExtra("ordertype", 1);
        intent.putExtra("paymoney", totalPrice+"");
        startActivity(intent);
    }


    class SelectBrocastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {

            String quotedpriceType = intent.getStringExtra("quotedprice");
            float price = Float.valueOf(intent.getIntExtra("price", 0));
            float count = Float.valueOf(intent.getStringExtra("count"));
            int degree = Integer.valueOf(intent.getStringExtra("degree"));
            float lateFre = Float.valueOf(intent.getIntExtra("lateFre", 0));
            int isSelect = intent.getIntExtra("isSelect", -1);


            if (isSelect == 1 && quotedpriceType.equals("1")) {
                totalPrice += (price + count + lateFre);
                totalServiceFee += price;
                totalDegree += degree;
            } else if (isSelect == 0 && quotedpriceType.equals("1")) {
                totalPrice -= (price + count + lateFre);
                totalDegree -= degree;
                totalServiceFee -= price;
            }

            listItemID.clear();
            for (int i = 0; i < illegalAdapter.mChecked.size(); i++) {
                if (illegalAdapter.mChecked.get(i)) {
                    if (peccancyList.get(i).getIscommit() == 1 &&
                            (!(peccancyList.get(i).getDegree().equals("0") && peccancyList.get(i).getPrice() == 0 && peccancyList.get(i).getQuotedprice().equals("1"))))
                        if (peccancyList.get(i).getQuotedprice().equals("1")) {
                            listItemID.add(i);
                        }
                }
            }


            if (totalPrice < 0) {
                totalPrice = 0;
                totalServiceFee = 0;
            }
            if (totalDegree < 0) {
                totalDegree = 0;
            }
            if (listItemID.isEmpty()) {
                price_tv.setText(0 + "元");
            } else {
                price_tv.setText(totalPrice + "元");
                sevice_fee_tv.setText(totalServiceFee + "元");
            }

            if ((listItemID.size() == 0) && totalPrice == 0) {
                real_commit_btn.setClickable(false);
                real_commit_btn.setEnabled(false);
//                real_commit_btn.setBackgroundResource(R.drawable.tijiaoa2);
                price_tv.setText(0 + "元");
                sevice_fee_tv.setText(0 + "元");
            } else {
                real_commit_btn.setClickable(true);
                real_commit_btn.setEnabled(true);
//                real_commit_btn.setBackgroundDrawable(getResources().getDrawable(R.drawable.tijiao_bg));
            }

            count_illegal_choose_tv.setText(listItemID.size() + "");//已选多少条违章
        }
    }


}