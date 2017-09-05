package com.yeohe.kiosk.ui.addcar;

import com.ccj.base.base.BasePresenter;
import com.ccj.base.base.BaseView;
import com.ccj.base.bean.User;

/**
 * Created by Administrator on 2017/9/1.
 */

public interface AddCarContract {

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
