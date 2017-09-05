package com.yeohe.kiosk.ui.order;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.utils.router.RouterConstants;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.ui.annual.AnnualPresenter;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/1.
 *
 * 订单详情界面
 */

@Route(path = RouterConstants.ORDERDETAIL_MOUDEL_ACTIVITY)
public class OrderDetailActivity extends BaseActivity implements OrderContract.View{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new OrderPresenter(this);
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
