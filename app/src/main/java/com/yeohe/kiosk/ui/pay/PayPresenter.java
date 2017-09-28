package com.yeohe.kiosk.ui.pay;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import com.alibaba.android.arouter.launcher.ARouter;
import com.android.volley.VolleyError;
import com.ccj.base.utils.SharedPreferenceUtil;
import com.ccj.base.utils.TLog;
import com.ccj.base.utils.router.RouterConstants;
import com.yeohe.kiosk.bean.Deal;
import com.yeohe.kiosk.http.URLs;
import com.yeohe.kiosk.ui.pay.PayContract.Presenter;
import com.yeohe.kiosk.utils.EncryptUtil;
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

public class PayPresenter implements Presenter {

    private PayContract.View view;
    private Activity context;

    public PayPresenter(Activity context, PayContract.View view) {
        this.view = view;
        this.context = context;
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
                TLog.logI(EncryptUtil.decryptJson(response, context) + "");
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
                TLog.logI(EncryptUtil.decryptJson(response, context) + "");
                try {
                    JSONObject obj = new JSONObject(EncryptUtil.decryptJson(response.toString(), context));
                    String status = obj.optString("status");
                    String show_msg = "";
                    Deal deal = new Deal();
                    if (status.equals("ok")) {

                        JSONObject dataObj = obj.getJSONObject("data");
                        int payStatus = dataObj.optInt("payStatus");

                        if (payStatus == 1) {//未支付

                        } else if (payStatus == 2) {//支付成功

                            PayActivity.timer.cancel();

                            String merchant_name = dataObj.optString("merchant_name");
                            String ordercode = dataObj.optString("ordercode");
                            String merchant_number = "";
                            if (dataObj.has("merchant_number")) {
                                merchant_number = dataObj.optString("merchant_number");
                            }
                            String max_flowing_no = dataObj.optString("max_flowing_no");
                            String pay_time = dataObj.optString("pay_time");
                            String merchant_type = dataObj.optString("merchant_type");
                            String pay_money = dataObj.optString("pay_money");
                            String car_type = "";
                            if (dataObj.has("car_type")) {
                                car_type = dataObj.optString("car_type");
                            }
                            String car_number = dataObj.optString("car_number");
                            String card_no = "";
                            if (dataObj.has("card_no")) {
                                card_no = dataObj.optString("card_no");
                            }

//                            deal.setMerchant_name(merchant_name);
                            deal.setOrdercode(ordercode);
                            deal.setMerchant_number(merchant_number + "");
//                            deal.setMax_flowing_no(max_flowing_no);
                            deal.setPay_time(pay_time);
//                            deal.setMerchant_type(merchant_type);
                            deal.setPay_money(pay_money);
                            deal.setCar_type(car_type + "");
                            deal.setCar_number(car_number);
                            deal.setCard_no(card_no + "");

                            ARouter.getInstance().build(RouterConstants.PAY_SUCESS_ACTIVITY)
                                    .withString("payMoney",pay_money)
                                    .withString("merchant_name",merchant_name)
                                    .withString("orderCode",ordercode)
                                    .withString("max_flowing_no",max_flowing_no)
                                    .navigation();
                            context.finish();
                        } else if (payStatus == -1) {//支付失败

                            PayActivity.timer.cancel();

                            if (obj.has("show_msg")) {
                                show_msg = obj.optString("payMsg");
                            }

                            ARouter.getInstance().build(RouterConstants.PAY_SUCESS_ACTIVITY)
                                    .withString("error_msg",show_msg)
                                    .navigation();
                        } else if (payStatus == -2) {//过期或未支付
                        }
                    } else {

                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                } finally {
                }
            }


        });
    }


    HashMap params;
    //获取支付通道信息
    @Override
    public void getPayType(String order,int orderType) {
        params=new HashMap();
        params.put("token", SharedPreferenceUtil.getInstance().getToken());
        params.put("ordercode",order);
        if(orderType==3) {
            params.put("is_annual_inspection", 1);
        }
        OkHttpUtils.post().url(URLs.GET_PAY_TYPE).params(EncryptUtil.encrypt(params)).build().execute(new StringCallback() {
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
                TLog.logI(EncryptUtil.decryptJson(response, context) + "");
            }
        });
    }


    //违章检验订单信息
    @Override
    public void checkPayment(String orderCode,String ordermoney) {
        params=new HashMap();
        params.put("token", SharedPreferenceUtil.getInstance().getToken());
        params.put("ordercode",orderCode);
        params.put("ordermoney",ordermoney);
        OkHttpUtils.post().url(URLs.ORDER_CHECK).params(EncryptUtil.encrypt(params)).build().execute(new StringCallback() {
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
                TLog.logI(EncryptUtil.decryptJson(response, context) + "");
            }
        });
    }

    //年检订单校验
    @Override
    public void annualOrderCheck(String orderCode,String ordermoney) {
        params=new HashMap();
        params.put("token", SharedPreferenceUtil.getInstance().getToken());
        params.put("ordercode",orderCode);
        params.put("ordermoney",ordermoney);
        OkHttpUtils.post().url(URLs.ANNUAL_ORDER_CHECK).params(EncryptUtil.encrypt(params)).build().execute(new StringCallback() {
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
                TLog.logI(EncryptUtil.decryptJson(response, context) + "");
            }
        });
    }
}
