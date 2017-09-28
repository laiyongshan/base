package com.yeohe.kiosk.ui.query;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.net.ConnectivityManager;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.android.volley.VolleyError;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.base.Constants;
import com.ccj.base.utils.DialogCreator;
import com.ccj.base.utils.TLog;
import com.ccj.base.utils.router.RouterConstants;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.AppApplication;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.adapter.IllegalFragmentPagerAdapter;
import com.yeohe.kiosk.bean.CarType;
import com.yeohe.kiosk.bean.FirstEvent;
import com.yeohe.kiosk.bean.Province;
import com.yeohe.kiosk.bean.Violation;
import com.yeohe.kiosk.http.URLs;
import com.yeohe.kiosk.ui.addcar.AddCarActivity;
import com.yeohe.kiosk.ui.addcar.AddCarContract;
import com.yeohe.kiosk.ui.addcar.AddCarPresenter;
import com.yeohe.kiosk.ui.query.fragment.DontHandleFragment;
import com.yeohe.kiosk.ui.query.fragment.QuotedFragment;
import com.yeohe.kiosk.ui.query.fragment.RealTimeFragment;
import com.yeohe.kiosk.utils.DialogUtil;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/1.
 *
 * 违章查询界面
 */
@Route(path= RouterConstants.QUERY_MOUDLE_ACTIVITY)
public class QueryActivity extends BaseActivity<QueryContract.Presenter> implements QueryContract.View{

    @BindView(R.id.back_btn)
    RoundTextView back_btn;

    @BindViews({R.id.real_time_quotes_tv,R.id.to_quotes_tv,R.id.not_handel_tv})
    TextView[] illegals;
    private List<TextView> tvList=new ArrayList<TextView>();;

    private ViewPager illegal_viewpager;
    private List<Fragment> fragmentList;
    private FragmentPagerAdapter fragmentPagerAdapter;

    @Autowired
    String carid;

    private int searchtype=-1;//是否本人本车  1代扣分   2本人本車

    private AppApplication appContext;

    private HashMap queryParams=new HashMap();

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_query);
        ARouter.getInstance().inject(this);
        ButterKnife.bind(this);

        //注册EventBus
        EventBus.getDefault().register(this);

        mPresenter = new QueryPresenter(this,this);
        mPresenter.start();

        appContext=(AppApplication) this.getApplicationContext();

        initView();

        TLog.logI("carid>>>>>>"+carid);
        queryParams.put("token", Constants.token);
        queryParams.put("carid",carid+"");
        queryParams.put("searchtype",1);
        mPresenter.loadData(URLs.CLIENT_QUERY_POST,queryParams);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        EventBus.getDefault().unregister(this);//反注册EventBus
    }

    @Subscribe
    public void onEvent(FirstEvent event) {
    /* Do something */
        if(event.getMsg().equals("finish")){
            finish();
        }
    }



    //初始化Viewpager
    private void initViewPager(Violation violation){
        illegal_viewpager= (ViewPager) findViewById(R.id.illegal_viewpager);
        illegal_viewpager .setOnTouchListener( new View.OnTouchListener()//修改不可左右滑动
        {
            @Override
            public boolean onTouch(View v, MotionEvent event)
            {
                return true;  //修改为true
            }
        });

        fragmentList=new ArrayList<Fragment>();
        Fragment realTimeFragment=new RealTimeFragment(violation,1);
        Fragment dontHandleFragment=new DontHandleFragment(violation);
        Fragment quotedFragment=new QuotedFragment(violation);
        fragmentList.add(realTimeFragment);
        fragmentList.add(quotedFragment);
        fragmentList.add(dontHandleFragment);
        FragmentManager fm=getSupportFragmentManager();

        fragmentPagerAdapter=new IllegalFragmentPagerAdapter(fm,fragmentList);
        illegal_viewpager.setAdapter(fragmentPagerAdapter);

        illegal_viewpager.setOffscreenPageLimit(3);

    }


    @OnClick({R.id.back_btn,R.id.real_time_quotes_tv,R.id.to_quotes_tv,R.id.not_handel_tv})
    public void click(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
            case R.id.real_time_quotes_tv://实时报价
                tvChooose(illegals[0]);
                setView(0);
                break;

            case R.id.to_quotes_tv://待报价
                tvChooose(illegals[1]);
                setView(1);
                break;

            case R.id.not_handel_tv://不可代办
                tvChooose(illegals[2]);
                setView(2);
                break;
        }
    }

    private void tvChooose(TextView tv){
        tv.setBackgroundResource(R.drawable.button_normal);
        for(int i=0;i<tvList.size();i++){
            if((tv.getId())!=(tvList.get(i).getId())){
                tvList.get(i).setBackgroundColor(Color.argb(0,255,255,255));
            }
        }
    }


    private void setView(int type){

        switch(type){
            case 0://实时报价
                if(illegal_viewpager!=null) {
                    illegal_viewpager.setCurrentItem(0);
                }
                break;

            case 1://待报价
                if(illegal_viewpager!=null) {
                    illegal_viewpager.setCurrentItem(1);
                }
                break;

            case 2://不可代办
                if(illegal_viewpager!=null) {
                    illegal_viewpager.setCurrentItem(2);
                }
                break;
        }
    }

    @Override
    public void initView() {
        tvList.add(illegals[0]);
        tvList.add(illegals[1]);
        tvList.add(illegals[2]);
    }

    @Override
    public void showProgress() {
        progressDialog= DialogCreator.createLoadingDialog(QueryActivity.this,"Loading...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String title,String error) {
        DialogUtil.showErrDialog(QueryActivity.this,title,error);
    }

    @Override
    public void setData(Violation violation) {
        DialogCreator.createBaseCustomDialog(QueryActivity.this, "提示", violation.getData().getMessage().getContent().toString(),null).show();
        initViewPager(violation);//初始化ViewPager

    }



}
