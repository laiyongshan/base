package com.yeohe.kiosk.ui.login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.Constants;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.base.BaseApplication;
import com.ccj.base.utils.router.RouterConstants;
import com.yeohe.kiosk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.ccj.base.Constants.PARAMS_RESULT_FROM_LOGIN;
import static com.ccj.base.Constants.RESULT_FROM_LOGIN;

/**
 * Created by Administrator on 2016/7/7.
 */

@Route(path = RouterConstants.LOGIN_MOUDLE_ACTIVITY)
public class LoginActivity extends BaseActivity<LoginContract.Presenter> implements LoginContract.View {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new LoginPresenter(this);
        mPresenter.start();
    }


    @Override
    public void navigateToMain() {

    }

    @Override
    public void navigateToRegister() {

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
