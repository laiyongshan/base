package com.yeohe.kiosk.ui.main;

/**
 * Created by Administrator on 2017/8/30.
 */

public class MainPresenter implements MainContract.Presenter {

    private MainContract.View view;
    public MainPresenter(MainContract.View view){
        this.view=view;
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }
}
