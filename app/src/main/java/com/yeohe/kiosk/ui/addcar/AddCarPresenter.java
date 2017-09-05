package com.yeohe.kiosk.ui.addcar;

/**
 * Created by Administrator on 2017/9/1.
 */

public class AddCarPresenter implements AddCarContract.Presenter {

    private AddCarContract.View view;
    public AddCarPresenter(AddCarContract.View view){
        this.view=view;
    }


    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }
}
