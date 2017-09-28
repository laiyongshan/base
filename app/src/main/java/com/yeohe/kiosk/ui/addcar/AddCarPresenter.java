package com.yeohe.kiosk.ui.addcar;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ccj.base.base.Constants;
import com.ccj.base.utils.DialogCreator;
import com.ccj.base.utils.TLog;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.base.utils.router.RouterUtils;
import com.google.gson.Gson;
import com.yeohe.kiosk.bean.CarType;
import com.yeohe.kiosk.bean.Condition;
import com.yeohe.kiosk.bean.Province;
import com.yeohe.kiosk.http.URLs;
import com.yeohe.kiosk.utils.EncryptUtil;
import com.yeohe.kiosk.utils.GsonUtil;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import okhttp3.Call;
import okhttp3.Request;

/**
 * Created by Administrator on 2017/9/1.
 */

public class AddCarPresenter implements AddCarContract.Presenter {

    private AddCarContract.View view;
    private Activity context;
    public AddCarPresenter(Activity context, AddCarContract.View view){
        this.view=view;
        this.context=context;
    }

    @Override
    public void start() {

    }

    @Override
    public void loadProvinceCode(String url,HashMap map) {
        OkHttpUtils.post().url(url).params(map).build().execute(new StringCallback() {
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
                view.showError("网络请求错误",e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
               Province province= GsonUtil.GsonToBean(EncryptUtil.decryptJson(response,context),Province.class);
                TLog.logI(EncryptUtil.decryptJson(response,context)+province.getData().getProvinceList().size()+"");

                view.showProvinceCode(province.getData().getProvinceList());
            }

        });
    }

    @Override
    public void loadCarTypeList(String url,HashMap map) {
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
                view.showError("网络请求错误",e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
                TLog.logI(EncryptUtil.decryptJson(response,context)+"");
                CarType carType= GsonUtil.GsonToBean(EncryptUtil.decryptJson(response,context),CarType.class);

                if(carType.getData()!=null) {
                    view.showCarTypeList(carType.getData().getCarTypeList());
                }
            }

        });
    }

    /*
    * 获取输入条件
    * */
    @Override
    public void getConditions(String url,HashMap map) {
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
                view.showError("网络请求错误",e.toString());
            }
            @Override
            public void onResponse(String response, int id) {
                TLog.logI(EncryptUtil.decryptJson(response,context)+"");
                Condition condition= GsonUtil.GsonToBean(EncryptUtil.decryptJson(response,context),Condition.class);
                if(condition.getStatus().equals("ok"))
                    view.showConditionsHint(condition.getData().getCarcodelen(),condition.getData().getCardrivelen());
            }

        });
    }

    @Override
    public void addCar(String url,HashMap map) {

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
                view.showError("网络请求错误",e.toString());
                view.hideProgress();
            }
            @Override
            public void onResponse(String response, int id) {
                TLog.logI(EncryptUtil.decryptJson(response,context)+"");
                try {
                    JSONObject obj=new JSONObject(EncryptUtil.decryptJson(response,context));
                    String status=obj.optString("status");
                    if(status.equals("ok")){
                        String carid = obj.getJSONObject("data").getString("carid") + "";
                        queryIllegal(URLs.VIOLATION_QUERY,getQueryParams(carid));//查询违章
//                        TLog.logI(""+carid);
//                        ARouter.getInstance().build(RouterConstants.QUERY_MOUDLE_ACTIVITY)
//                                .withString("carid", carid)
//                                .navigation();//跳转到违章查询

                    }else{
                        view.showError("添加车辆失败",obj.optString("msg"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

        });

    }

    HashMap queryParams;
    @Override
    public HashMap getQueryParams(String carid) {
        queryParams=new HashMap();
        queryParams.put("token", Constants.token);
        queryParams.put("carid", carid);
        queryParams.put("searchtype","1");
        return queryParams;
    }

    /*
    * 查询违章
    * */
    @Override
    public void queryIllegal(String url, final HashMap map) {
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
                view.showError("网络请求错误",e.toString());
                view.hideProgress();

            }
            @Override
            public void onResponse(String response, int id) {
                TLog.logI(EncryptUtil.decryptJson(response,context)+"");

                try {
                    JSONObject obj = new JSONObject(EncryptUtil.decryptJson(response,context));
                    String status=obj.optString("status");
                    if(status.equals("ok")) {
                        ARouter.getInstance().build(RouterConstants.QUERY_MOUDLE_ACTIVITY)
                                .withString("carid", (String) map.get("carid"))
                                .navigation();//跳转到违章查询

                        context.finish();

                    }else{
                        view.showError("查询失败",obj.optString("msg")+"");
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                    view.showError("",e.toString());
                }


            }

        });
    }

    @Override
    public void onDestroy() {

    }
}
