package com.yeohe.kiosk.ui.annual;

import android.content.Context;

import com.ccj.base.utils.TLog;
import com.yeohe.kiosk.AppApplication;
import com.yeohe.kiosk.bean.Annual;
import com.yeohe.kiosk.bean.Province;
import com.yeohe.kiosk.utils.EncryptUtil;
import com.yeohe.kiosk.utils.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;
import com.zhy.http.okhttp.utils.Exceptions;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/9/1.
 */

public class AnnualPresenter implements AnnualContract.Presenter {

    private AnnualContract.View view;
    private Context context;
    public AnnualPresenter(Context context,AnnualContract.View view){
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
    public void loadSupportsProvincePrice(String url,HashMap map) {
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
                view.showError(e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
                TLog.logI(EncryptUtil.decryptJson(response, context));
                Annual annual = GsonUtil.GsonToBean(EncryptUtil.decryptJson(response, context), Annual.class);
                if(annual.getCode()==0) {
                    view.showAdressAndPrice(annual);
                }else{
                    view.showError(annual.getMsg()+"");
                }
            }

        });
    }

    @Override
    public void commitAnnualOrder(String url,HashMap map) {
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
                view.showError(e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
                TLog.logI(EncryptUtil.decryptJson(response,context));
                Annual.ResultBean resultBean = GsonUtil.GsonToBean(EncryptUtil.decryptJson(response, context), Annual.ResultBean.class);
                AppApplication.showLongToast(resultBean.getMsg().toString());
                if(resultBean.getStatus().equals("ok")) {
                    view.showSucessDialog(resultBean.getData().getOrdercode() + "", resultBean.getData().getOrdercode());
                }else{
                    view.showError(resultBean.getMsg()+"");
                }
            }
        });
    }
}
