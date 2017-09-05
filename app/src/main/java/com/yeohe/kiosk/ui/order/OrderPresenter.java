package com.yeohe.kiosk.ui.order;

import com.yeohe.kiosk.ui.order.OrderContract.Presenter;

/**
 * Created by Administrator on 2017/9/1.
 */

public class OrderPresenter implements Presenter {

    private OrderContract.View view;
    public OrderPresenter(OrderContract.View view){
        this.view=view;
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }
}
