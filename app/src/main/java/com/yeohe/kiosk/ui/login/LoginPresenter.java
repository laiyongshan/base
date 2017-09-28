package com.yeohe.kiosk.ui.login;


import android.content.Context;

import com.ccj.base.bean.User;
import com.ccj.base.utils.SharedPreferenceUtil;
import com.ccj.base.utils.TLog;
import com.yeohe.kiosk.bean.CarType;
import com.yeohe.kiosk.http.URLs;
import com.yeohe.kiosk.utils.EncryptUtil;
import com.yeohe.kiosk.utils.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * login的presenter层 进行对view 和 model 的控制,
 * Created by ccj on 2016/7/7.
 */
public class LoginPresenter implements LoginContract.Presenter{

    private LoginContract.View loginView;
    private Context context;
    public LoginPresenter(Context context, LoginContract.View loginView) {
        this.loginView = loginView;
        this.context=context;
    }


    /*
    * 登录
    * */
    HashMap map;
    @Override
    public void login(String username, String password) {
        map=new HashMap();
        map.put("username",username);
        map.put("password",password);

        OkHttpUtils.post().url(URLs.LOGIN_URL).params(EncryptUtil.encrypt(map)).build().execute(new StringCallback() {
            @Override
            public void onBefore(Request request, int id) {
                loginView.showProgress();
            }

            @Override
            public void onAfter(int id) {
                loginView.hideProgress();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
                TLog.logI(e.toString());
                loginView.showError(e.toString());

                loginView.showFaileDialog(e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
                TLog.logI(EncryptUtil.decryptJson(response,context)+"");

                SharedPreferenceUtil.getInstance().setUsername("15088132079");//设置用户名称
                SharedPreferenceUtil.getInstance().setToken("");//设置token

            }
        });
    }

    /*
    * 获取手机验证码
    * */
    @Override
    public void getAuthCode(String  phoneNum) {
        map=new HashMap();
        map.put("phoneNum",phoneNum);
        OkHttpUtils.post().url(URLs.GET_AUTH_CODE).params(EncryptUtil.encrypt(map)).build().execute(new StringCallback() {
            @Override
            public void onBefore(Request request, int id) {
                loginView.showProgress();
            }

            @Override
            public void onAfter(int id) {
                loginView.hideProgress();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
                TLog.logI(e.toString());
                loginView.showError(e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
                TLog.logI(EncryptUtil.decryptJson(response,context)+"");
            }
        });
    }

    @Override
    public void start() {

    }


    @Override
    public void onDestroy() {
        TLog.log("-->loginPresenter  onDestroy");
    }


}
