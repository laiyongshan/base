package com.yeohe.kiosk.ui.main;

import android.app.Application;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.base.Constants;
import com.ccj.base.utils.DialogCreator;
import com.ccj.base.utils.NetUtils;
import com.ccj.base.utils.SharedPreferenceUtil;
import com.ccj.base.utils.TLog;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.base.utils.router.RouterUtils;
import com.flyco.roundview.RoundTextView;
import com.shizhefei.guide.GuideHelper;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.yeohe.kiosk.AppApplication;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.ui.annual.AnnualPresenter;
import com.yeohe.kiosk.ui.dialog.ExitDialog;
import com.yeohe.kiosk.utils.ScreenSizeUtil;
import com.yeohe.kiosk.utils.StringUtils;
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
    RoundTextView[] btns;

    @BindView(R.id.login_exit_layout)
    LinearLayout login_exit_layout;

    @BindView(R.id.exit_btn)
    RoundTextView exit_btn;

    @BindView(R.id.login_btn)
    Button login_btn;

    @BindView(R.id.head_portrait_iv)
    ImageView head_portrait_iv;//用户头像

    @Autowired
    Boolean isLogin;

    String phoneNum;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mPresenter = new MainPresenter(this);
        mPresenter.start();



        if(ScreenSizeUtil.getScreenWidth(this)<=ScreenSizeUtil.getScreenHeight(this)) {
            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) fun_sv.getLayoutParams();
            linearParams.width = ScreenSizeUtil.getScreenWidth(this) / 3 * 2;        //
            fun_sv.setLayoutParams(linearParams);
        }else{
            LinearLayout.LayoutParams linearParams = (LinearLayout.LayoutParams) fun_sv.getLayoutParams();
            linearParams.width = ScreenSizeUtil.getScreenWidth(this)/3;        //
            fun_sv.setLayoutParams(linearParams);

            RelativeLayout.LayoutParams linearParams2 = (RelativeLayout.LayoutParams) login_exit_layout.getLayoutParams();
            login_exit_layout.setPadding(0,0,ScreenSizeUtil.getScreenWidth(this)/20,0);
        }

        SharedPreferenceUtil.getInstance().setToken(Constants.token);
        SharedPreferenceUtil.getInstance().setUsername("15088132079");
        SharedPreferenceUtil.getInstance().setIsLogin(true);

    }

    @Override
    protected void onResume() {
        super.onResume();
        phoneNum= SharedPreferenceUtil.getInstance().getUsername();
        if(!SharedPreferenceUtil.getInstance().getIsLogin()){
            login_btn.setText("登  录");
            head_portrait_iv.setImageResource(R.mipmap.user_login);
        }else {
            login_btn.setText(StringUtils.showPhoneNum(phoneNum));
            head_portrait_iv.setImageResource(R.mipmap.header_icon);
        }
        TLog.logI("phoneNum"+phoneNum+"\n"+isLogin);

        if(isLogin!=null&&isLogin) {
            GuideHelper guideHelper = new GuideHelper(MainActivity.this);
            GuideHelper.TipData tipData = new GuideHelper.TipData(R.drawable.tip3, login_exit_layout);
            guideHelper.addPage(tipData);
            guideHelper.show(false);
        }


        Beta.autoCheckUpgrade = true;//自动检测更新

    }

    @OnClick({R.id.login_exit_layout, R.id.query_btn,R.id.annual_btn,R.id.order_btn,R.id.exit_btn})
    public void btn_click(View view){
        switch (view.getId()){
            case R.id.login_exit_layout:
                if(SharedPreferenceUtil.getInstance().getToken()==null||SharedPreferenceUtil.getInstance().getToken().equals("")) {//跳去登录
                    RouterUtils.navigation(RouterConstants.LOGIN_MOUDLE_ACTIVITY);
                }else{//退出登录
//                    RouterUtils.navigation(RouterConstants.LOGIN_MOUDLE_ACTIVITY);
                    showExitDialog();
                }
//                RouterUtils.navigation(RouterConstants.QUERY_MOUDLE_ACTIVITY);
                break;

            case R.id.query_btn:
                if(SharedPreferenceUtil.getInstance().getToken()==null||SharedPreferenceUtil.getInstance().getToken().equals("")){//跳去登录
                    RouterUtils.navigation(RouterConstants.LOGIN_MOUDLE_ACTIVITY);
//                    RouterUtils.navigation(RouterConstants.PAY_SUCESS_ACTIVITY);
//                    RouterUtils.navigation(RouterConstants.PAY_FAIL_ACTIVITY);
                }else {
                    RouterUtils.navigation(RouterConstants.ADDCAR_MOUDLE_ACTIVITY);
                }
                break;

            case R.id.annual_btn:
                if(SharedPreferenceUtil.getInstance().getToken()==null||SharedPreferenceUtil.getInstance().getToken().equals("")) {//跳去登录
                    RouterUtils.navigation(RouterConstants.LOGIN_MOUDLE_ACTIVITY);
                }else {
                    RouterUtils.navigation(RouterConstants.ANNUAL_MOUDLE_ACTIVITY);
                }
                break;

            case R.id.order_btn:
                if(SharedPreferenceUtil.getInstance().getToken()==null||SharedPreferenceUtil.getInstance().getToken().equals("")) {//跳去登录
                    RouterUtils.navigation(RouterConstants.LOGIN_MOUDLE_ACTIVITY);
                }else {
                    RouterUtils.navigation(RouterConstants.ORDER_STYLE_MOUDEL_ACTIVITY);
                }
//                RouterUtils.navigation(RouterConstants.PAY_SUCESS_ACTIVITY);
//                 RouterUtils.navigation(RouterConstants.QUERY_MOUDLE_ACTIVITY);
                break;

            case R.id.exit_btn:
                showExitDialog();
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

    ExitDialog exitDialog;
    @Override
    public void showExitDialog() {
        exitDialog=new ExitDialog(MainActivity.this, R.style.Dialog, new ExitDialog.ExitInterface() {
            @Override
            public void exit() {
                login_btn.setText("登  录");
                head_portrait_iv.setImageResource(R.mipmap.user_login);
            }
        });
        exitDialog.show();
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                exitDialog.dismiss();//一分钟自动退出
            }
        },60*1000);
    }

    /*
    * 返回键退出
    * */
    @Override
    public void onBackPressed() {
        super.onBackPressed();

    }

}
