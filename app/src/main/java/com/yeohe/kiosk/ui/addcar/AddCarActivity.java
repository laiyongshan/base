package com.yeohe.kiosk.ui.addcar;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.alibaba.android.arouter.utils.TextUtils;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.base.Constants;
import com.ccj.base.utils.DialogCreator;
import com.ccj.base.utils.TLog;
import com.ccj.base.utils.router.RouterConstants;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.AppApplication;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.bean.CarType;
import com.yeohe.kiosk.bean.City;
import com.yeohe.kiosk.bean.Province;
import com.yeohe.kiosk.http.URLs;
import com.yeohe.kiosk.ui.cartype.CarTypeDialog;
import com.yeohe.kiosk.ui.dialog.DoubtDialog;
import com.yeohe.kiosk.ui.dialog.ProvinceDialog;
import com.yeohe.kiosk.ui.login.LoginActivity;
import com.yeohe.kiosk.ui.login.LoginPresenter;
import com.yeohe.kiosk.ui.query.QueryActivity;
import com.yeohe.kiosk.utils.AllCapTransformationMethod;
import com.yeohe.kiosk.utils.DialogUtil;
import com.yeohe.kiosk.utils.EncryptUtil;
import com.yeohe.kiosk.utils.KeyBoardUtils;
import com.yeohe.kiosk.utils.ScreenSizeUtil;
import com.yeohe.kiosk.widgets.ClearEditText;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/9/1.
 */

@Route(path = RouterConstants.ADDCAR_MOUDLE_ACTIVITY)
public class AddCarActivity extends BaseActivity<AddCarContract.Presenter> implements AddCarContract.View{

    @BindView(R.id.back_btn)
    RoundTextView back_btn;

    @BindViews({R.id.carcode_doubt_img,R.id.carengine_doubt_img})
    ImageView[] doubt_imgs;

    @BindView(R.id.car_type_tv)
    TextView car_type_tv;

    @BindView(R.id.province_code_tv)
    TextView province_code_tv;

    @BindView(R.id.add_car_btn)
    RoundTextView add_car_btn;

    @BindView(R.id.car_num_et)
    ClearEditText car_num_et;

    @BindView(R.id.car_engine_et)
    ClearEditText car_engine_et;

    @BindView(R.id.car_code_et)
    ClearEditText car_code_et;

    @BindView(R.id.main_body_layout)
    RelativeLayout main_body_layout;

    @BindView(R.id.content_layout)
    LinearLayout content_layout;

    DoubtDialog doubtDialog=null;
    CarTypeDialog carTypeDialog=null;

    private HashMap params=new HashMap();
    private HashMap conditionParams=new HashMap();

    private String cartype;//车辆类型

    AllCapTransformationMethod allCapTransformationMethod = new AllCapTransformationMethod();//字母大写

    int width,hight;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addcar);
        ButterKnife.bind(this);


        width=ScreenSizeUtil.getScreenWidth(AddCarActivity.this);
        hight=ScreenSizeUtil.getScreenHeight(AddCarActivity.this);
        if(width<=hight) {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = width / 10 * 9;        // 当控件的高强制设成120象素
            main_body_layout.setLayoutParams(linearParams);
        }else{
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = (int)(width*0.618);        //
            main_body_layout.setLayoutParams(linearParams);
            main_body_layout.setPadding(hight/10,0,hight/10,0);
        }

        mPresenter = new AddCarPresenter(this,this);
        mPresenter.start();

        car_engine_et.setTransformationMethod(allCapTransformationMethod);
        car_code_et.setTransformationMethod(allCapTransformationMethod);
        car_num_et.setTransformationMethod(allCapTransformationMethod);
        car_num_et.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }
            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count){

            }
            @Override
            public void afterTextChanged(Editable s) {
                if(s==null||s.toString().trim().equals("")) {
                    car_engine_et.setText("");
                    car_code_et.setText("");
                }else if(s.length()==1){
                    conditionParams.put("token",Constants.token);
                    conditionParams.put("carprefix",province_code_tv.getText().toString()+s.subSequence(0,1).toString().toUpperCase());
                    mPresenter.getConditions(URLs.GET_QUERY_CONDITIONS,conditionParams);
                }
            }
        });
    }


    @OnClick({R.id.back_btn,
            R.id.carcode_doubt_img,
            R.id.carengine_doubt_img,
            R.id.car_type_tv,
            R.id.province_code_tv,
            R.id.add_car_btn})
    public void click(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();//返回
                break;
            case R.id.carcode_doubt_img:
                doubtDialog = new DoubtDialog(AddCarActivity.this, R.style.Dialog, R.mipmap.carcode_img);
                doubtDialog.showDialog();
                doubtDialog.setCancelable(true);
                break;

            case R.id.carengine_doubt_img:
                doubtDialog = new DoubtDialog(AddCarActivity.this, R.style.Dialog, R.mipmap.carcode_img);
                doubtDialog.showDialog();
                doubtDialog.setCancelable(true);
                break;

            case R.id.car_type_tv:
                params.put("token",Constants.token);
                mPresenter.loadCarTypeList(URLs.GET_CAR_TYPE_LIST,params);
                break;

            case R.id.province_code_tv:
                mPresenter.loadProvinceCode(URLs.GET_OPEN_PROVINCE,new HashMap());
                break;

            case R.id.add_car_btn://提价车辆并查询
                if(car_type_tv.getText().toString().equals("")){
                    DialogCreator.createBaseCustomDialog(AddCarActivity.this, "提示","请选择车辆类型",null).show();
                }else if(car_num_et.getText().toString().length()<6){
                    DialogCreator.createBaseCustomDialog(AddCarActivity.this, "提示","请输入完整车牌号码",null).show();
                }else if(car_code_et.getText().toString().length()<4){
                    DialogCreator.createBaseCustomDialog(AddCarActivity.this, "提示","请输入车辆发动机号",null).show();
                }else if(car_engine_et.getText().toString().length()<4){
                    DialogCreator.createBaseCustomDialog(AddCarActivity.this, "提示","请输入车辆车架号",null).show();
                }else {
                    mPresenter.addCar(URLs.ADD_CAR, getCarParam());
                }
                break;

        }
    }


    @Override
    public void showProgress() {
        progressDialog=DialogCreator.createLoadingDialog(AddCarActivity.this,"Loading...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String title,String error) {
        DialogUtil.showErrDialog(AddCarActivity.this,title,error);
    }

    ProvinceDialog provinceDialog=null;
    @Override
    public void showProvinceCode(List<Province.DataEntity.ProvinceListEntity> provinceList) {
        provinceDialog=new ProvinceDialog(AddCarActivity.this, R.style.Dialog, provinceList, new ProvinceDialog.ProvinceCallBack() {
            @Override
            public void provinceCallBack(String provincceCode) {
                province_code_tv.setText(provincceCode+"");
            }
        });
        provinceDialog.show();
    }

    @Override
    public void showCarTypeList(ArrayList<CarType.DataEntity.CarTypeListEntity> cartypeList) {
        carTypeDialog=new CarTypeDialog(AddCarActivity.this,cartypeList, R.style.Dialog, new CarTypeDialog.CarTypeInterface() {
            @Override
            public void cartype_callback(String typename, String typecode){
                car_type_tv.setText(typename+"");
                cartype=typecode;
            }
        });
        carTypeDialog.show();
    }

    @Override
    public void showConditionsHint(int carcodelen, int cardrivelen) {
        if(carcodelen==99){
            car_code_et.setHint("请输入完整车身架号");
        }else if(carcodelen==0){
            car_code_et.setHint("请输入后"+6+"位车身架号");
        }else{
            car_code_et.setHint("请输入后"+carcodelen+"位车身架号");
        }

        if(cardrivelen==99){
            car_engine_et.setHint("请输入完整车身架号");
        }else if(cardrivelen==0){
            car_engine_et.setHint("请输入后"+6+"位车身架号");
        }else{
            car_engine_et.setHint("请输入后"+cardrivelen+"位发动机号");
        }
    }



    HashMap carParams=null;
    @Override
    public HashMap getCarParam() {
        carParams=new HashMap();
        carParams.put("token", Constants.token);
        carParams.put("carnumber",car_num_et.getText().toString().trim().toUpperCase());
        carParams.put("carcode",car_code_et.getText().toString().toString().toUpperCase());
        carParams.put("cardrivenumber",car_engine_et.getText().toString().toUpperCase());
        carParams.put("proprefix",province_code_tv.getText().toString().trim().toUpperCase());
        carParams.put("cartype",cartype);//车辆类型
        TLog.logI(cartype+"");
        return carParams;
    }

}
