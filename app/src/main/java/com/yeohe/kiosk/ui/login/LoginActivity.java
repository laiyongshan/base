package com.yeohe.kiosk.ui.login;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.webkit.ServiceWorkerWebSettings;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.ccj.base.Constants;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.base.BaseApplication;
import com.ccj.base.utils.SharedPreferenceUtil;
import com.ccj.base.utils.TLog;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.base.utils.router.RouterUtils;
import com.flyco.roundview.RoundTextView;
import com.weavey.loading.lib.LoadingLayout;
import com.yeohe.kiosk.AppApplication;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.utils.KeyBoardUtils;
import com.yeohe.kiosk.utils.ScreenSizeUtil;
import com.yeohe.kiosk.widgets.ClearEditText;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

import static com.ccj.base.Constants.PARAMS_RESULT_FROM_LOGIN;
import static com.ccj.base.Constants.RESULT_FROM_LOGIN;

/**
 * Created by Administrator on 2016/7/7.
 */

@Route(path = RouterConstants.LOGIN_MOUDLE_ACTIVITY)
public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {

    @BindView(R.id.login_layout)
    RelativeLayout login_layout;

    @BindView(R.id.back_btn)
    RoundTextView login_back_btn;

    @BindView(R.id.login_btn)
    RoundTextView login_btn;

    @BindView(R.id.get_auth_btn)
    RoundTextView get_auth_btn;

    @BindViews({R.id.user_phone_et,R.id.auth_code_et})
    ClearEditText[] ets;

    @Override
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this,this);
        mPresenter.start();

        if(ScreenSizeUtil.getScreenWidth(this)<=ScreenSizeUtil.getScreenHeight(this)) {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) login_layout.getLayoutParams();
            linearParams.width = ScreenSizeUtil.getScreenWidth(this) / 5 * 4;        // 当控件的高强制设成120象素
            login_layout.setLayoutParams(linearParams);
        }else{
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) login_layout.getLayoutParams();
            linearParams.width = ScreenSizeUtil.getScreenWidth(this)/5*2;
            login_layout.setLayoutParams(linearParams);
        }

    }

    @OnClick({R.id.back_btn,R.id.login_btn,R.id.get_auth_btn})
    public void click(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();//返回
                break;

            case R.id.login_btn:

                KeyBoardUtils.closeKeybord(ets[1],LoginActivity.this);

                if (TextUtils.isEmpty(ets[0].getText())){
                    //设置晃动
                    ets[0].setShakeAnimation();
                    //设置提示
                    AppApplication.showLongToast("用户手机号不能为空！");
                    return;
                }else if (TextUtils.isEmpty(ets[1].getText())){
                    ets[1].setShakeAnimation();
                    AppApplication.showLongToast("验证码码不能为空！");
                    return;
                }else{
                    mPresenter.login(ets[0].getText().toString().trim(),ets[1].getText().toString().trim());
                }
                break;

            case R.id.get_auth_btn://获取验证码
                if(!ets[0].getText().toString().trim().equals("")) {
                    mPresenter.getAuthCode(ets[0].getText().toString().trim());
                }else{
                    //设置晃动
                    ets[0].setShakeAnimation();
                    new SweetAlertDialog(LoginActivity.this).setTitleText("提示").setContentText("手机号码不能为空").setConfirmText("OK").show();
                }
                break;
        }
    }



    @Override
    public void navigateToMain() {
        ARouter.getInstance().build(RouterConstants.MAIN_MOUDLE_ACTIVITY)
                .withBoolean("isLogin",true)
                .navigation();
    }

    SweetAlertDialog sucessDialog;
    @Override
    public void showSucessDialog() {
        sucessDialog=new SweetAlertDialog(LoginActivity.this,SweetAlertDialog.SUCCESS_TYPE)
                    .setTitleText("登录成功");
        sucessDialog.setCanceledOnTouchOutside(false);
        sucessDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sucessDialog.dismiss();
                navigateToMain();
            }
        },2000);

    }

    SweetAlertDialog failDialog;
    @Override
    public void showFaileDialog(String err) {
        failDialog=new SweetAlertDialog(LoginActivity.this,SweetAlertDialog.ERROR_TYPE).setTitleText("登录失败").setContentText(err+"");
        failDialog.setCanceledOnTouchOutside(false);
        failDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                failDialog.dismiss();
            }
        },5000);
    }

    @Override
    public void showProgress() {
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String error) {
        BaseApplication.showShortToast(error);
    }

    @Override
    public void initView() {
    }
}
