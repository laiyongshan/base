package com.yeohe.kiosk.ui.addcar;

import com.ccj.base.base.BasePresenter;
import com.ccj.base.base.BaseView;
import com.ccj.base.bean.User;
import com.yeohe.kiosk.bean.CarType;
import com.yeohe.kiosk.bean.Province;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/9/1.
 */

public interface AddCarContract {

    interface View extends BaseView {
        void showProgress();
        void hideProgress();
        void showError(String title,String error);
        void showProvinceCode(List<Province.DataEntity.ProvinceListEntity> provinceList);
        void showCarTypeList(ArrayList<CarType.DataEntity.CarTypeListEntity> cartypeList);
        void showConditionsHint(int carcodelen,int cardrivelen);

        HashMap getCarParam();
    }

    interface Presenter extends BasePresenter {
        void loadProvinceCode(String url,HashMap map);//加载可查询的省份简称
        void loadCarTypeList(String url,HashMap map);//获取车辆类型列表
        void getConditions(String url,HashMap map);//获取输入条件
        void addCar(String url,HashMap map);//添加车辆

        HashMap getQueryParams(String carid);
        void queryIllegal(String url,HashMap map);//查询违章
    }

    interface Model{

    }

}
