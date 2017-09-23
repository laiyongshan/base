package com.yeohe.kiosk.ui.annual;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.os.Handler;
import android.renderscript.RenderScript;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.base.Constants;
import com.ccj.base.utils.DialogCreator;
import com.ccj.base.utils.router.RouterConstants;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.bean.Annual;
import com.yeohe.kiosk.http.URLs;
import com.yeohe.kiosk.ui.addcar.AddCarActivity;
import com.yeohe.kiosk.ui.dialog.AnnualTipsDialog;
import com.yeohe.kiosk.ui.dialog.DoubtDialog;
import com.yeohe.kiosk.ui.dialog.PrefixDialog;
import com.yeohe.kiosk.ui.login.LoginActivity;
import com.yeohe.kiosk.ui.login.LoginPresenter;
import com.yeohe.kiosk.utils.AllCapTransformationMethod;
import com.yeohe.kiosk.utils.DialogUtil;
import com.yeohe.kiosk.utils.ScreenSizeUtil;
import com.yeohe.kiosk.widgets.ClearEditText;

import org.w3c.dom.Text;

import java.util.Calendar;
import java.util.HashMap;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;
import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/9/1.
 */
@Route(path = RouterConstants.ANNUAL_MOUDLE_ACTIVITY)
public class AnnualActivity extends BaseActivity<AnnualContract.Presenter> implements AnnualContract.View {

    @BindViews({R.id.next_btn1})
    RoundTextView[] nexts;

    @BindViews({R.id.annual_item_layout1,R.id.annual_item_layout2,R.id.annual_item_layout3})
    RelativeLayout[] item_layouts;

    @BindView(R.id.datapicker_tv)
    TextView datapicker_tv;

    @BindView(R.id.express_to_addrss_tv)
    TextView express_to_addrss_tv;

    @BindView(R.id.prefix_tv)
    TextView prefix_tv;

    @BindView(R.id.annual_price_tv)
    TextView annual_price_tv;

    @BindView(R.id.carengine_doubt_img)
    ImageView carengine_doubt_img;

    @BindView(R.id.carcode_doubt_img)
    ImageView carcode_doubt_img;

    @BindView(R.id.add_annual_btn)
    RoundTextView add_annual_btn;

    @BindViews({R.id.car_num_et,R.id.car_code_et,R.id.car_engine_et,R.id.car_owner_et,R.id.idcard_last_et,R.id.car_phone_et,R.id.express_et})
    ClearEditText carets[];

    @BindView(R.id.main_body_layout)
    RelativeLayout main_body_layout;

    @BindView(R.id.content_sv)
    ScrollView content_sv;

    @BindView(R.id.content_sv2)
    ScrollView content_sv2;

    private int mYear,mMonth,mDay;
    final int DATE_DIALOG = 1;

    private int flag=0;
    private Context context;

    private Annual annual;
    private String price;

    DoubtDialog doubtDialog=null;
    PrefixDialog prefixDialog;

    private ProvinceBrocast provinceBrocast;//接收选择的身份简称

    private HashMap params1=new HashMap();
    private HashMap addParams=new HashMap();

    AllCapTransformationMethod allCapTransformationMethod = new AllCapTransformationMethod();//字母大写

    int width,hight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annual);
        ButterKnife.bind(this);

        width=ScreenSizeUtil.getScreenWidth(AnnualActivity.this);
        hight=ScreenSizeUtil.getScreenHeight(AnnualActivity.this);
        if(ScreenSizeUtil.getScreenWidth(this)<=ScreenSizeUtil.getScreenHeight(this)) {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = ScreenSizeUtil.getScreenWidth(this) / 10 * 9;        // 当控件的高强制设成120象素
            main_body_layout.setLayoutParams(linearParams);
        }else{
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = (int)(width*0.618);        // ;        // 当控件的高强制设成120象素
            main_body_layout.setLayoutParams(linearParams);
            RelativeLayout.LayoutParams linearParams2 = (RelativeLayout.LayoutParams) content_sv.getLayoutParams();
            linearParams2.width = (int)(linearParams.width/5*4);
            content_sv.setLayoutParams(linearParams2);

            RelativeLayout.LayoutParams linearParams3 = (RelativeLayout.LayoutParams) content_sv2.getLayoutParams();
            linearParams3.width = (int)(linearParams.width/5*4);
            content_sv2.setLayoutParams(linearParams3);
        }


        mPresenter = new AnnualPresenter(this,this);
        mPresenter.start();

        params1.put("token", Constants.token);
        mPresenter.loadSupportsProvincePrice(URLs.GET_SUPPORTED_PROVINCES,params1);

        carets[2].setTransformationMethod(allCapTransformationMethod);
        carets[1].setTransformationMethod(allCapTransformationMethod);
        carets[0].setTransformationMethod(allCapTransformationMethod);

        final Calendar ca = Calendar.getInstance();
        mYear = ca.get(Calendar.YEAR);
        mMonth = ca.get(Calendar.MONTH);
        mDay = ca.get(Calendar.DAY_OF_MONTH);

        context=AnnualActivity.this;
    }


    AnnualTipsDialog annualTipsDialog;
    @Override
    protected void onStart() {
        super.onStart();
        if(annualTipsDialog==null) {
            annualTipsDialog=new AnnualTipsDialog(AnnualActivity.this, R.style.Dialog);
        }
        annualTipsDialog.show();

        IntentFilter filter=new IntentFilter(PrefixDialog.PREFIX_ACTION_NAME);
        provinceBrocast=new ProvinceBrocast();
        registerReceiver(provinceBrocast,filter);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(provinceBrocast);
    }

    /*
* 车牌前缀的广播接收者
* */
    class ProvinceBrocast extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            if(intent!=null) {
                prefix_tv.setText(intent.getStringExtra("prefix")+"");
                price=intent.getStringExtra("price")+"";
                annual_price_tv.setText("价格：￥"+price);

            }
            prefixDialog.dismiss();
        }
    }

    @OnClick({R.id.add_annual_btn,R.id.prefix_tv,R.id.back_btn,R.id.next_btn1,R.id.datapicker_tv,R.id.carengine_doubt_img,R.id.carcode_doubt_img})
    public void onClick(View view){
        switch(view.getId()){
            case R.id.back_btn:
                if(flag==0){
                    finish();
                }else if(flag==1){
                    item_layouts[0].setVisibility(View.VISIBLE);
                    item_layouts[2].setVisibility(View.GONE);
                    flag=0;
                }
                break;

            case R.id.next_btn1:
                item_layouts[0].setVisibility(View.GONE);
                item_layouts[2].setVisibility(View.VISIBLE);
                flag=1;
                break;

            case R.id.datapicker_tv://年审时间选择
                showDialog(DATE_DIALOG);
                break;

            case R.id.carcode_doubt_img:
                doubtDialog = new DoubtDialog(AnnualActivity.this, R.style.Dialog, R.mipmap.carcode_img);
                doubtDialog.showDialog();
                doubtDialog.setCancelable(true);
                break;

            case R.id.carengine_doubt_img:
                doubtDialog = new DoubtDialog(AnnualActivity.this, R.style.Dialog, R.mipmap.carcode_img);
                doubtDialog.showDialog();
                doubtDialog.setCancelable(true);
                break;

            case R.id.prefix_tv:
                prefixDialog = new PrefixDialog(AnnualActivity.this,R.style.Dialog,annual);
                prefixDialog.showDialog();
                prefixDialog.setCancelable(true);
                break;

            case R.id.add_annual_btn:
                if(prefix_tv.getText().toString().trim().equals("")){
                    DialogCreator.createBaseCustomDialog(AnnualActivity.this, "提示",getResources().getString(R.string.annual_hint1),null).show();
                }else if(carets[0].getText().toString().trim().equals("")){
                    DialogCreator.createBaseCustomDialog(AnnualActivity.this, "提示",getResources().getString(R.string.annual_hint2),null).show();
                }else if(carets[1].getText().toString().trim().equals("")){
                    DialogCreator.createBaseCustomDialog(AnnualActivity.this, "提示",getResources().getString(R.string.annual_hint3),null).show();
                }else if(carets[2].getText().toString().trim().equals("")){
                    DialogCreator.createBaseCustomDialog(AnnualActivity.this, "提示",getResources().getString(R.string.annual_hint4),null).show();
                }else if(datapicker_tv.getText().toString().trim().equals("")){
                    DialogCreator.createBaseCustomDialog(AnnualActivity.this, "提示",getResources().getString(R.string.annual_hint5),null).show();
                }else if(carets[3].getText().toString().trim().equals("")){
                    DialogCreator.createBaseCustomDialog(AnnualActivity.this, "提示",getResources().getString(R.string.annual_hint6),null).show();
                }else if(carets[5].getText().toString().trim().equals("")){
                    DialogCreator.createBaseCustomDialog(AnnualActivity.this, "提示",getResources().getString(R.string.annual_hint7),null).show();
                }else if(carets[6].getText().toString().trim().equals("")){
                    DialogCreator.createBaseCustomDialog(AnnualActivity.this, "提示",getResources().getString(R.string.annual_hint8),null).show();
                }else if(carets[4].getText().toString().trim().length()<4){
                    DialogCreator.createBaseCustomDialog(AnnualActivity.this, "提示",getResources().getString(R.string.annual_hint9),null).show();
                }else {
                    mPresenter.commitAnnualOrder(URLs.ANNUAL_ADD_ORDER, getAddParams());
                }
                break;
        }
    }

    @Override
    protected Dialog onCreateDialog(int id) {
        switch (id) {
            case DATE_DIALOG:
                return new DatePickerDialog(this, mdateListener, mYear, mMonth, mDay);
        }
        return null;
    }

    /**
     * 设置日期 利用StringBuffer追加
     */
    public void display() {
        datapicker_tv.setText(new StringBuffer().append(mYear).append("-").append(mMonth + 1).append("-").append(mDay).append(" "));
    }

    private DatePickerDialog.OnDateSetListener mdateListener = new DatePickerDialog.OnDateSetListener() {

        @Override
        public void onDateSet(DatePicker view, int year, int monthOfYear,
                              int dayOfMonth) {
            mYear = year;
            mMonth = monthOfYear;
            mDay = dayOfMonth;
            display();
        }
    };

    @Override
    public void showProgress() {
        progressDialog= DialogCreator.createLoadingDialog(AnnualActivity.this,"Loading...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String error) {
        DialogUtil.showErrDialog(AnnualActivity.this,"",error);
    }

    @Override
    public HashMap getAddParams() {
        addParams.put("token",Constants.token);
        addParams.put("proprefix",prefix_tv.getText().toString().trim());//粤A
        addParams.put("carnumber",carets[0].getText().toString().trim().toUpperCase());//车牌号码
        addParams.put("cardrivenumber",carets[2].getText().toString().trim().toUpperCase());//发动机号
        addParams.put("carcode",carets[1].getText().toString().trim().toUpperCase());//车架号后六位
        addParams.put("name",carets[3].getText().toString().trim());//客户姓名
        addParams.put("mobile",carets[5].getText().toString().trim());//客户电话
        addParams.put("checkyear_day",datapicker_tv.getText().toString().trim());//年审日
        addParams.put("server_address",carets[6].getText().toString().trim());//收件地址
        addParams.put("id_card",carets[4].getText().toString().trim()+"");//车主身份证号码后4位
        addParams.put("orderMoney",price);//订单金额
        return addParams;
    }

    @Override
    public void showAdressAndPrice(Annual annual) {
        if(annual.getCode()==0) {

            this.annual=annual;

            express_to_addrss_tv.setText(annual.getData().getAddress());
            prefix_tv.setText(""+annual.getData().getProvinces().get(0).getProvince()+annual.getData().getProvinces().get(0).getDetails().get(0).getZimu());
            annual_price_tv.setText("价格：￥"+annual.getData().getProvinces().get(0).getDetails().get(0).getPrice());
        }
    }

    /*
    * 显示提交订单成功按钮
   */
    SweetAlertDialog sucessDialog;
    @Override
    public void showSucessDialog(final String orderCode) {
        sucessDialog=new SweetAlertDialog(AnnualActivity.this,SweetAlertDialog.SUCCESS_TYPE)
                .setTitleText("提交成功");
        sucessDialog.setCanceledOnTouchOutside(false);
        sucessDialog.show();

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                sucessDialog.dismiss();
                navigateToPay(orderCode);
            }
        },2000);
    }



    /*跳转去支付*/
    @Override
    public void navigateToPay(String orderCode) {
        ARouter.getInstance().build(RouterConstants.PAY_MOUDLE_ACTIVITY)
                .withString("orderCode",orderCode)
                .navigation();
    }

}
