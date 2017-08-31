package com.yeohe.kiosk.ui.login;


import com.ccj.base.bean.User;
import com.ccj.base.utils.TLog;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * login的presenter层 进行对view 和 model 的控制,
 * Created by ccj on 2016/7/7.
 */
public class LoginPresenter implements LoginContract.Presenter {

    private LoginContract.View loginView;
    public LoginPresenter(LoginContract.View loginView) {
        this.loginView = loginView;
    }

    @Override
    public void login(String username, String password) {

    }

    @Override
    public void start() {

    }


    @Override
    public void onDestroy() {
        TLog.log("-->loginPresenter  onDestroy");
    }


}
