package com.yeohe.kiosk.ui.annual;

/**
 * Created by Administrator on 2017/9/1.
 */

public class AnnualPresenter implements AnnualContract.Presenter {

    private AnnualContract.View view;
    public AnnualPresenter(AnnualContract.View view){
        this.view=view;
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }
}
