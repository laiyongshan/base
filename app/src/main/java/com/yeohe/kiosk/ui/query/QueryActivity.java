package com.yeohe.kiosk.ui.query;

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
 * 违章查询界面
 */
@Route(path= RouterConstants.QUERY_MOUDLE_ACTIVITY)
public class QueryActivity extends BaseActivity<QueryContract.Presenter> implements QueryContract.View{

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        mPresenter = new QueryPresenter(this);
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
