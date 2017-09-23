package com.yeohe.kiosk.ui.query;

import android.content.Context;

import com.ccj.base.base.BasePresenter;
import com.ccj.base.base.BaseView;
import com.ccj.base.utils.TLog;
import com.yeohe.kiosk.bean.CarType;
import com.yeohe.kiosk.bean.Province;
import com.yeohe.kiosk.bean.Violation;
import com.yeohe.kiosk.ui.pay.PayContract;
import com.yeohe.kiosk.utils.EncryptUtil;
import com.yeohe.kiosk.utils.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/9/13.
 */

public class QueryPresenter implements QueryContract.Presenter {

    private QueryContract.View view;
    private Context context;
    public QueryPresenter(Context context, QueryContract.View view){
        this.view=view;
        this.context=context;
    }

    @Override
    public void start() {

    }

    @Override
    public void loadData(String url,HashMap map) {

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
                view.showError("",e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
                Violation violation= GsonUtil.GsonToBean(EncryptUtil.decryptJson(response,context),Violation.class);
                if(violation.getCode()==0) {
                    view.setData(violation);//初始化数据
                    TLog.logI(EncryptUtil.decryptJson(response, context) + "");
                }else{
                    view.showError("查询失败",violation.getMsg().toString()+"");

                }
            }
        });

    }


    @Override
    public void onDestroy() {

    }
}
