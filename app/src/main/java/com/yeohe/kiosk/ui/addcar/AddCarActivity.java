package com.yeohe.kiosk.ui.addcar;

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

@Route(path = RouterConstants.ADDCAR_MOUDLE_ACTIVITY)
public class AddCarActivity extends BaseActivity<AddCarContract.Presenter> implements AddCarContract.View{


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new AddCarPresenter(this);
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
