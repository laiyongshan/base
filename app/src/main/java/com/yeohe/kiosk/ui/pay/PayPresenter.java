package com.yeohe.kiosk.ui.pay;

import android.content.Context;

import com.ccj.base.utils.TLog;
import com.yeohe.kiosk.ui.pay.PayContract.Presenter;
import com.yeohe.kiosk.utils.EncryptUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/9/1.
 */

public class PayPresenter implements Presenter {

    private PayContract.View view;
    private Context context;
    public PayPresenter(Context context, PayContract.View view){
        this.view=view;
        this.context=context;
    }

    @Override
    public void start() {

    }

    @Override
    public void onDestroy() {

    }

    @Override
    public void loadQRUrl(String url, HashMap map) {
        OkHttpUtils.post().url(url).params(EncryptUtil.encrypt(map)).build().execute(new StringCallback() {
            @Override
            public void onBefore(Request request, int id) {
                view.showProgress();
            }

            @Override
            public void onAfter(int id) {
                view.hideProgress();
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
                TLog.logI(e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
                TLog.logI(EncryptUtil.decryptJson(response,context)+"");
            }
        });
    }

    @Override
    public void loadPayStatus(String url, HashMap map) {
        OkHttpUtils.post().url(url).params(EncryptUtil.encrypt(map)).build().execute(new StringCallback() {
            @Override
            public void onBefore(Request request, int id) {
            }

            @Override
            public void onAfter(int id) {
            }

            @Override
            public void onError(Call call, Exception e, int id) {
                e.printStackTrace();
                TLog.logI(e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
                TLog.logI(EncryptUtil.decryptJson(response,context)+"");
            }

        });
    }


    //获取支付通道信息
    @Override
    public void getPayType(String order) {

    }

    //违章检验订单信息
    @Override
    public void checkPayment(HashMap map) {

    }

    //年检订单校验
    @Override
    public void annualOrderCheck(HashMap map) {

    }
}
