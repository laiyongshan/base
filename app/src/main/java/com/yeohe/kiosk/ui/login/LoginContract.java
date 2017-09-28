package com.yeohe.kiosk.ui.login;


import com.ccj.base.base.BasePresenter;
import com.ccj.base.base.BaseView;
import com.ccj.base.bean.User;

import java.util.HashMap;

/**
 * 登录关联接口类
 *
 * Created by Administrator on 2016/7/7.
 */
public interface LoginContract {


    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        void showError(String error);
        void navigateToMain();//跳转到首页

        void showSucessDialog();//登录成功弹框
        void showFaileDialog(String err);//登录失败提示框
    }

    interface Presenter extends BasePresenter {
        void login(String username, String password);
        void getAuthCode(String phoneNum);
        void onDestroy();
    }

    interface Model{
        void saveUserInfo(User user);
        void saveLoginState(Boolean isLogin);
        void saveRememberPass(User user);
    }

}
