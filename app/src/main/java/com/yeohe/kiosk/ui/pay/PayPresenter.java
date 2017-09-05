package com.yeohe.kiosk.ui.pay;

import com.yeohe.kiosk.ui.pay.PayContract.Presenter;

/**
 * Created by Administrator on 2017/9/1.
 */

public class PayPresenter implements Presenter {

    private PayContract.View view;
    public PayPresenter(PayContract.View view){
        this.view=view;
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }
}
