package com.yeohe.kiosk.ui.query;

import com.yeohe.kiosk.ui.query.QueryContract.Presenter;

/**
 * Created by Administrator on 2017/9/1.
 */

public class QueryPresenter implements Presenter {

    private QueryContract.View view;
    public QueryPresenter(QueryContract.View view){
        this.view=view;
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }
}
