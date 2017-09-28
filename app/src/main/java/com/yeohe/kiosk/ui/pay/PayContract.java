package com.yeohe.kiosk.ui.pay;

import com.ccj.base.base.BasePresenter;
import com.ccj.base.base.BaseView;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/9/1.
 */

public interface PayContract {
    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        void showError(String error);

        void showQrCode(String qr_url);
    }

    interface Presenter extends BasePresenter {
        void onDestroy();
        void loadQRUrl(String url,HashMap map);//获取二维码
        void loadPayStatus(String url,HashMap map);//循环检测扫码支付的结果

        void getPayType(String order,int orderType);//获取支付通道信息
        void checkPayment(String orderCode,String ordermoney);//违章支付订单校验
        void annualOrderCheck(String orderCode,String ordermoney);//年检订单校验

    }

    interface Model{

    }
}
