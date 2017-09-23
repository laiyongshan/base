package com.yeohe.kiosk.ui.annual;

import com.ccj.base.base.BasePresenter;
import com.ccj.base.base.BaseView;
import com.yeohe.kiosk.bean.Annual;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/9/1.
 */

public interface AnnualContract {
    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        void showError(String error);
        HashMap getAddParams();
        void showAdressAndPrice(Annual annual);

        void showSucessDialog(String orderCoder);

        void navigateToPay(String orderCode);//提交订单成功，跳转到支付
    }

    interface Presenter extends BasePresenter {
        void loadSupportsProvincePrice(String url,HashMap map);//获取支持的省份价格及证件邮寄地址
        void commitAnnualOrder(String url,HashMap map);//提交年检订单

    }

    interface Model{

    }
}
