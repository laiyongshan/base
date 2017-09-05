package com.yeohe.kiosk.ui.order;

import android.os.Bundle;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.utils.router.RouterConstants;
import com.yeohe.kiosk.R;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2017/9/1.
 *
 * 订单列表界面
 */
@Route(path=RouterConstants.ORDERLIST_MOUDLE_ACTIVITY)
public class OrderListActivity extends BaseActivity implements  OrderContract.View {

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
