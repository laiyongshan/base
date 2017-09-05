package com.yeohe.kiosk.ui.main;

import android.app.Application;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.utils.DialogCreator;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.base.utils.router.RouterUtils;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.yeohe.kiosk.AppApplication;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.ui.annual.AnnualPresenter;
import com.yeohe.kiosk.utils.ScreenSizeUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;


@Route(path = RouterConstants.MAIN_MOUDLE_ACTIVITY)
public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View{

    @BindView(R.id.fun_sv)
    ScrollView fun_sv;

    @BindViews({R.id.query_btn,R.id.annual_btn,R.id.order_btn})
    Button[] btns;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this);
        mPresenter.start();

        if(ScreenSizeUtil.getScreenWidth(this)<=ScreenSizeUtil.getScreenHeight(this)) {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) fun_sv.getLayoutParams();
            linearParams.width = ScreenSizeUtil.getScreenWidth(this) / 3 * 2;        // 当控件的高强制设成120象素
            fun_sv.setLayoutParams(linearParams);
        }else{
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) fun_sv.getLayoutParams();
            linearParams.width = ScreenSizeUtil.getScreenWidth(this)/ 3;        // 当控件的高强制设成120象素
            fun_sv.setLayoutParams(linearParams);
        }
    }


    @OnClick({R.id.login_btn, R.id.query_btn,R.id.annual_btn,R.id.order_btn})
    public void btn_click(View view){
        switch (view.getId()){
            case R.id.login_btn:
                RouterUtils.navigation(RouterConstants.LOGIN_MOUDLE_ACTIVITY);
                break;

            case R.id.query_btn:
                RouterUtils.navigation(RouterConstants.QUERY_MOUDLE_ACTIVITY);
                break;

            case R.id.annual_btn:
                RouterUtils.navigation(RouterConstants.ANNUAL_MOUDLE_ACTIVITY);
                break;

            case R.id.order_btn:
                RouterUtils.navigation(RouterConstants.ORDERLIST_MOUDLE_ACTIVITY);
                break;
        }
    }

    @Override
    public void showProgress() {

    }

    @Override
    public void hideProgress() {

    }

    @Override
    public void showError(String error) {

    }
}
