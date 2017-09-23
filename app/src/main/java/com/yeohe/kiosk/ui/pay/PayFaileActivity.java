package com.yeohe.kiosk.ui.pay;

import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.utils.router.RouterConstants;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.utils.ScreenSizeUtil;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/1.
 * 支付失败界面
 */
@Route(path= RouterConstants.PAY_FAIL_ACTIVITY)
public class PayFaileActivity extends BaseActivity {
    @BindView(R.id.main_body_layout)
    RelativeLayout main_body_layout;

    @BindView(R.id.back_btn)
    RoundTextView back_btn;

    @BindView(R.id.faile_reson_tv)
    TextView faile_reson_tv;//支付失败原因

    @BindView(R.id.repay_btn)
    RoundTextView repay_btn;//重新支付按钮


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payfaile);
        ButterKnife.bind(this);

        if(ScreenSizeUtil.getScreenWidth(this)<=ScreenSizeUtil.getScreenHeight(this)) {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
        }else{
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = (int)(ScreenSizeUtil.getScreenWidth(this)*0.618);        //
            main_body_layout.setLayoutParams(linearParams);
        }

    }


    @OnClick({R.id.back_btn,R.id.repay_btn})
    public void click(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;

            case R.id.repay_btn://重新支付

                break;
        }
    }


}
