package com.yeohe.kiosk.ui.annual;

import android.app.Activity;
import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.utils.router.RouterConstants;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.ui.login.LoginPresenter;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/1.
 */
@Route(path = RouterConstants.ANNUAL_MOUDLE_ACTIVITY)
public class AnnualActivity extends BaseActivity<AnnualContract.Presenter> implements AnnualContract.View {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new AnnualPresenter(this);
        mPresenter.start();
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
