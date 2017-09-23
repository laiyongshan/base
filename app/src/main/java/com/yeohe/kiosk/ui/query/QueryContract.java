package com.yeohe.kiosk.ui.query;

import com.ccj.base.base.BasePresenter;
import com.ccj.base.base.BaseView;
import com.yeohe.kiosk.bean.Violation;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/9/13.
 */

public interface QueryContract {

    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        void showError(String title,String error);

        void setData(Violation violation);
    }

    interface Presenter extends BasePresenter {
        void loadData(String url,HashMap map);//加载违章数据
    }

    interface Model{

    }

}
