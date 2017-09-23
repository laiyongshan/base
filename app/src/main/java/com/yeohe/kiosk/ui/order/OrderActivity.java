package com.yeohe.kiosk.ui.order;

import android.os.Build;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.view.ViewPager;
import android.view.KeyEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.bean.FirstEvent;
import com.yeohe.kiosk.utils.ScreenSizeUtil;
import com.yeohe.kiosk.widgets.SlidingTabLayout;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.json.JSONObject;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/4/18 0018.
 */

public class OrderActivity extends FragmentActivity implements View.OnClickListener {

    private TextView orderlist_title_tv;
    private ArrayList<Fragment> fragments;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    private ImageView order_back_img;

    private int orderListType;//1:违章订单   2：年检订单

    @BindView(R.id.main_body_layout)
    RelativeLayout main_body_layout;

    @BindView(R.id.back_btn)
    RoundTextView back_btn;

    int width,hight;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_order);
        ButterKnife.bind(this);

        width= ScreenSizeUtil.getScreenWidth(this);
        hight=ScreenSizeUtil.getScreenHeight(this);
        if(width<=hight) {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
        }else{
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = (int)(width*0.618);
            main_body_layout.setLayoutParams(linearParams);
            RelativeLayout.LayoutParams linearParams2 = (RelativeLayout.LayoutParams) viewPager.getLayoutParams();
            viewPager.setLayoutParams(linearParams2);
            viewPager.setPadding(((int)(width*0.15)),0,((int)(width*0.15)),0);
        }


        orderListType=getIntent().getIntExtra("orderListType",0);

        in();

    }

    @OnClick({R.id.back_btn})
    public void click(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }


    private void in() {

        EventBus.getDefault().register(this);
        fragments = new ArrayList<Fragment>();//打fragment初始化并放到List里面
        fragments.add(new MyOrderFragmentq3(orderListType));//添加未支付fragment
        fragments.add(new MyOrderFragmentq1(orderListType));//添加处理中fragment
        fragments.add(new MyOrderFragmentq2(orderListType));//添加已成功fragment
        fragments.add(new MyOrderFragmentq4(orderListType));//添加撤销fragment
        fragments.add(new MyOrderFragmentq5(orderListType));//添加全部fragment

        viewPager.setAdapter(new MyOrderPagerAdapter(getSupportFragmentManager(), fragments));
        viewPager.setOffscreenPageLimit(4);
        SlidingTabLayout slidingTabLayout = (SlidingTabLayout)
                findViewById(R.id.sliding_tabs);
        slidingTabLayout.setDistributeEvenly(true); //是否填充满屏幕的宽度
        slidingTabLayout.setViewPager(viewPager);

        slidingTabLayout.setCustomTabColorizer(
                new SlidingTabLayout.TabColorizer() {
                    @Override
                    public int getIndicatorColor(int position) {
                        return 0xFFFFFFFF;//设置导航栏下划线颜色
                    }
                });

    }


//    /**

    /**
     * 点击头像弹出窗口实现监听类
     */
    private View.OnClickListener itemsOnClick = new View.OnClickListener() {
        public void onClick(View v) {
            switch (v.getId()) {

            }
        }
    };




    /**
     * 整体刷新
     */
    @Override
    public void onClick(View v) {
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    public static String CONSTANT_SELECT_FRAGMENT = "select_fragment";

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
    }


    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        //如果按下的是返回键，并且没有重复
        if (keyCode == KeyEvent.KEYCODE_BACK && event.getRepeatCount() == 0) {
            finish();
//            overridePendingTransition(R.anim.slide_up_in, R.anim.slide_down_out);
            return false;
        }
        return false;
    }

    /**
     * 订单查询主页面删除事件
     * @param event
     */
    @Subscribe
    public void onEvent(FirstEvent event){
    }


    /**
     * 通知整体刷新
     *
     * @param event
     */
    @Subscribe
    public void onEventMainThread(FirstEvent event){
    }
}
