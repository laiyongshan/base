package com.yeohe.kiosk.ui.pay;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.utils.router.RouterConstants;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.ui.order.OrderPresenter;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/1.
 *
 * 支付界面
 */

@Route(path = RouterConstants.PAY_MOUDLE_ACTIVITY)
public class PayActivity extends BaseActivity<PayContract.Presenter> implements PayContract.View {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new PayPresenter(this);
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
