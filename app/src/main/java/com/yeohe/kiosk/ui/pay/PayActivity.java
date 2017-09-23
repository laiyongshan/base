package com.yeohe.kiosk.ui.pay;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.bumptech.glide.Glide;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.base.Constants;
import com.ccj.base.utils.DialogCreator;
import com.ccj.base.utils.TLog;
import com.ccj.base.utils.router.RouterConstants;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.http.URLs;
import com.yeohe.kiosk.ui.addcar.AddCarActivity;
import com.yeohe.kiosk.utils.ScreenSizeUtil;

import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/1.
 *
 * 支付界面
 */

@Route(path = RouterConstants.PAY_MOUDLE_ACTIVITY)
public class PayActivity extends BaseActivity<PayContract.Presenter> implements PayContract.View {

    @BindView(R.id.main_body_layout)
    RelativeLayout main_body_layout;

    @BindView(R.id.back_btn)
    RoundTextView back_btn;

    @BindView(R.id.weixin_pay_img)
    ImageView weixin_pay_img;

    @BindView(R.id.ali_pay_img)
    ImageView ali_pay_img;

    @BindView(R.id.type_tips_tv)
    TextView type_tips_tv;

    @BindView(R.id.pay_qr_code_img)
    ImageView pay_qr_code_img;

    public static Timer timer;
    private TimerTask task;

    private String ordercode;//订单号
    private float paymoney;//应付费用
    private int ordertype;//1：违章订单  2：补款订单  3：年检订单

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pay);
        ButterKnife.bind(this);

        ordercode = getIntent().getStringExtra("ordernumber");//得到订单号
        ordertype=getIntent().getIntExtra("ordertype",0);
        paymoney=Float.valueOf(getIntent().getStringExtra("zonfakuan"));//支付总金额
        TLog.logI(ordercode+"\n"+paymoney);

        if(ScreenSizeUtil.getScreenWidth(this)<=ScreenSizeUtil.getScreenHeight(this)) {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
        }else{
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = ScreenSizeUtil.getScreenWidth(this)/2;
            main_body_layout.setLayoutParams(linearParams);
        }

        mPresenter = new PayPresenter(this,this);
        mPresenter.start();

        showQrCode("");

        task=new TimerTask() {
            @Override
            public void run() {
                Message msg=new Message();
                msg.what=1;
                handler.sendMessage(msg);
            }
        };

        timer=new Timer();
        timer.schedule(task,3000,3000);
    }

    @Override
    protected void onResume() {
        super.onResume();
            if(ordertype==3){//年检订单检测
//                mPresenter.annualOrderCheck();
            }else{//违章订单检测
//                mPresenter.checkPayment();
            }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        timer.cancel();
    }

    Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg){
            // TODO Auto-generated method stub
            // 要做的事情
            switch (msg.what) {
                case 1:
                    //获取扫码支付状态
                    mPresenter.loadPayStatus(URLs.GET_PAY_STATUS,getStatusParams());
                    break;
            }
            super.handleMessage(msg);
        }
    };

    @OnClick({R.id.back_btn,R.id.weixin_pay_img,R.id.ali_pay_img})
    public void onClick(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;

            case R.id.weixin_pay_img://微信扫码支付
                payType(1);
                break;

            case R.id.ali_pay_img://支付宝扫码支付
                payType(2);
                break;
        }
    }

    int flag;
    private void payType(int type){
        if(type==1){
            weixin_pay_img.setImageResource(R.drawable.weixinzhifu);
            ali_pay_img.setImageResource(R.drawable.zhifubao2);
            type_tips_tv.setText("微信扫一扫，向我付款");

            mPresenter.loadQRUrl(URLs.GET_QR_CODE_URL,getQrcodeUrlParam(1));

            flag=1;

        }else if(type==2){
            weixin_pay_img.setImageResource(R.drawable.weixinzhifu2);
            ali_pay_img.setImageResource(R.drawable.zhifubao);
            type_tips_tv.setText("支付宝扫一扫，向我付款");

            mPresenter.loadQRUrl(URLs.GET_QR_CODE_URL,getQrcodeUrlParam(2));

            flag=2;
        }
    }


    HashMap map=new HashMap();
    private HashMap getQrcodeUrlParam(int paytype){
        map=new HashMap();
        if(Constants.token!=null) {
            map.put("token", Constants.token);
        }else{
            map.put("token","");
        }
        map.put("ordercode",ordercode==null?"":ordercode);
        map.put("paytype",paytype);
        map.put("paymoney",""+paymoney);
        return map;
    }

    private HashMap getStatusParams(){
        map=new HashMap();
        if(Constants.token!=null) {
            map.put("token", Constants.token);
        }else{
            map.put("token", "");
        }
        map.put("ordercode",ordercode==null?"":ordercode);
        return map;
    }


    @Override
    public void showProgress() {
        progressDialog= DialogCreator.createLoadingDialog(PayActivity.this,"Loading...");
        progressDialog.show();
    }

    @Override
    public void hideProgress() {
        progressDialog.dismiss();
    }

    @Override
    public void showError(String error) {

    }

    //显示二维码
    @Override
    public void showQrCode(String qr_url) {
        Glide.with(this)
                .load("http://upload-images.jianshu.io/upload_images/2972448-78cb561ab24f41c4.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240")
                .override(280, 280)
                .centerCrop()
                .into(pay_qr_code_img);
    }


    class MyCount extends CountDownTimer {
        /**
         * @param millisInFuture    The number of millis in the future from the call
         *                          to {@link #start()} until the countdown is done and {@link #onFinish()}
         *                          is called.
         * @param countDownInterval The interval along the way to receive
         *                          {@link #onTick(long)} callbacks.
         */
        public MyCount(long millisInFuture, long countDownInterval) {
            super(millisInFuture, countDownInterval);
        }

        @Override
        public void onTick(long millisUntilFinished) {

        }

        @Override
        public void onFinish() {
            payType(flag);//二维码过期，自动刷新二维码？
        }
    }

}
