package com.yeohe.kiosk.ui.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.base.utils.router.RouterUtils;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.utils.ScreenSizeUtil;

import org.w3c.dom.Text;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/1.
 *
 * 支付成功界面
 *
 */
@Route(path= RouterConstants.PAY_SUCESS_ACTIVITY)
public class PaySucessActivity extends BaseActivity {

    @BindView(R.id.main_body_layout)
    RelativeLayout main_body_layout;

    @BindView(R.id.back_btn)
    RoundTextView back_btn;

    @BindView(R.id.pay_money_tv)
    TextView pay_money_tv;

    @BindView(R.id.merchant_name_tv)
    TextView  merchant_name_tv;

    @BindView(R.id.order_code_tv)
    TextView order_code_tv;

    @BindView(R.id.max_flowing_no_tv)
    TextView max_flowing_no_tv;

    @BindView(R.id.finish_btn)
    RoundTextView finish_btn;

    @Autowired
    String payMoney="";//支付金额

    @Autowired
    String merchant_name="";//商户名称

    @Autowired
    String orderCode="";//订单编号

    @Autowired
    String max_flowing_no="";//流水号

    int width,hight;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paysucess);
        ButterKnife.bind(this);

        width=ScreenSizeUtil.getScreenWidth(this);
        hight=ScreenSizeUtil.getScreenHeight(this);
        if(width<=hight) {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
        }else{
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = (int)(ScreenSizeUtil.getScreenWidth(this)*0.618);        //
            main_body_layout.setLayoutParams(linearParams);
        }

        initView();

    }


    @Override
    public void initView() {
        super.initView();
        pay_money_tv.setText(payMoney+"元");
        merchant_name_tv.setText(merchant_name+"");
        order_code_tv.setText(orderCode+"");
        max_flowing_no_tv.setText(max_flowing_no+"");
    }

    @OnClick({R.id.back_btn,R.id.finish_btn})
    public void click(View view){
        switch (view.getId()){

            case R.id.back_btn:
                finish();
                break;

            case R.id.finish_btn://完成支付
                RouterUtils.navigation(RouterConstants.ORDER_STYLE_MOUDEL_ACTIVITY);
                finish();
                break;
        }
    }

}
