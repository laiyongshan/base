package com.yeohe.kiosk.ui.main;

import com.ccj.base.base.BasePresenter;
import com.ccj.base.base.BaseView;

/**
 * Created by Administrator on 2017/8/30.
 */

public interface MainContract {

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
