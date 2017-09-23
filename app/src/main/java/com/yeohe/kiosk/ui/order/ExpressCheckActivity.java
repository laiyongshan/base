package com.yeohe.kiosk.ui.order;

import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;
import com.android.volley.VolleyError;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.http.URLs;
import com.yeohe.kiosk.utils.EncryptUtil;
import com.yeohe.kiosk.utils.ScreenSizeUtil;
import com.yeohe.kiosk.utils.TokenSQLUtils;
import com.yeohe.kiosk.utils.UIHelper;
import com.yeohe.kiosk.vollery.VolleyInterface;
import com.yeohe.kiosk.vollery.VolleyUtil;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/6/16.
 */

public class ExpressCheckActivity extends Activity {

    private ImageView back_iv;

    private RecyclerView rvTrace;
    private List<Trace> traceList = new ArrayList<Trace>();
    private TraceListAdapter adapter;

    private TextView express_status_tv,express_name_tv,express_code_tv,timediff_tv;

    @BindView(R.id.main_body_layout)
    RelativeLayout main_body_layout;

    @BindView(R.id.content_layout)
    LinearLayout content_layout;

    @BindView(R.id.content_sv)
    ScrollView content_sv;

    @BindView(R.id.back_btn)
    RoundTextView back_btn;

    int width,hight;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_express);
        ButterKnife.bind(this);

        width= ScreenSizeUtil.getScreenWidth(ExpressCheckActivity.this);
        hight=ScreenSizeUtil.getScreenHeight(ExpressCheckActivity.this);
        if(width<=hight) {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = width / 10 * 9;        //
            main_body_layout.setLayoutParams(linearParams);
        }else{
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = (int)(width*0.618);        //
            main_body_layout.setLayoutParams(linearParams);
            RelativeLayout.LayoutParams linearParams2 = (RelativeLayout.LayoutParams) content_sv.getLayoutParams();
            linearParams2.width = (int)(linearParams.width/5*4);
            content_sv.setLayoutParams(linearParams2);
        }

        String express=getIntent().getStringExtra("express");

        initView();//初始化控件
        initData(express!=null?express:"");//获取数据
    }

    private void initView(){

        express_status_tv= (TextView) findViewById(R.id.express_status_tv);
        express_name_tv= (TextView) findViewById(R.id.express_name_tv);
        express_code_tv= (TextView) findViewById(R.id.express_code_tv);

        timediff_tv= (TextView) findViewById(R.id.timediff_tv);

        rvTrace= (RecyclerView) findViewById(R.id.rvTrace);
    }

    @OnClick({R.id.back_btn})
    public void click(View view){
        switch(view.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }

    /**
     * 初始化数据
     * */
    private void initData(String express){
        UIHelper.showPd(ExpressCheckActivity.this);
        HashMap map=new HashMap();
        map.put("token", TokenSQLUtils.check());
        map.put("express",express);
        VolleyUtil.getVolleyUtil(ExpressCheckActivity.this).StringRequestPostVolley(URLs.GET_EXPRESS_INFO, EncryptUtil.encrypt(map), new VolleyInterface() {
            @Override
            public void ResponseResult(Object jsonObject) {
                try {
                    JSONObject obj=new JSONObject(EncryptUtil.decryptJson(jsonObject.toString(),ExpressCheckActivity.this));
                    String status=obj.optString("status");
                    if(status.equals("ok")) {
                        JSONObject dataObj = obj.optJSONObject("data");
                        JSONArray arr = dataObj.optJSONArray("data");
                        String expTextName = dataObj.optString("expTextName");
                        String mailNo = dataObj.optString("mailNo");
                        String timediff = dataObj.optString("timediff");
                        express_status_tv.setText("" + dataObj.optString("status"));
                        express_name_tv.setText("" + expTextName);
                        express_code_tv.setText("" + mailNo);
                        timediff_tv.setText("耗时" + timediff);

                        Trace trace;
                        for (int i = 0; i < arr.length(); i++) {
                            trace = new Trace();
                            trace.setAcceptStation(arr.getJSONObject(i).getString("context"));
                            trace.setAcceptTime(arr.getJSONObject(i).getString("time"));
                            traceList.add(trace);
                        }

                        adapter = new TraceListAdapter(ExpressCheckActivity.this, traceList);
                        rvTrace.setLayoutManager(new LinearLayoutManager(ExpressCheckActivity.this));
                        rvTrace.setAdapter(adapter);
                    }else{
                        if(obj.has("show_msg")){
                            Toast.makeText(ExpressCheckActivity.this,""+obj.optString("show_msg"), Toast.LENGTH_SHORT).show();
                        }
                    }
                }catch (JSONException e) {
                    e.printStackTrace();
                }finally{
                    UIHelper.dismissPd();
                }


            }

            @Override
            public void ResponError(VolleyError volleyError) {

            }
        });
    }


}
