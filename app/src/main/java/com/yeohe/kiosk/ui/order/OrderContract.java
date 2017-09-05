package com.yeohe.kiosk.ui.order;

import com.ccj.base.base.BasePresenter;
import com.ccj.base.base.BaseView;

/**
 * Created by Administrator on 2017/9/1.
 */

public interface OrderContract {
    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        void showError(String error);
    }

    interface Presenter extends BasePresenter {
        void onDestroy();
    }

    interface Model{

    }
}
