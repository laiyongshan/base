package com.yeohe.kiosk.ui.order;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.base.utils.router.RouterUtils;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.utils.ScreenSizeUtil;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/6.
 * 订单类型选择界面
 */
@Route(path = RouterConstants.ORDER_STYLE_MOUDEL_ACTIVITY)
public class OrderStyleActivity extends BaseActivity {

    @BindView(R.id.main_body_layout)
    RelativeLayout main_body_layout;

    @BindViews({R.id.order_type1_tv,R.id.order_type2_tv,R.id.back_btn})
    RoundTextView[] roundTextViews;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_style);
        ButterKnife.bind(this);


        if(ScreenSizeUtil.getScreenWidth(this)<=ScreenSizeUtil.getScreenHeight(this)) {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
        }else{
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = (int)(ScreenSizeUtil.getScreenWidth(this)*0.618);        //
            linearParams.height=(int)(ScreenSizeUtil.getScreenHeight(this)*0.618);
            main_body_layout.setLayoutParams(linearParams);
        }

    }

    Intent intent;
    @OnClick({R.id.order_type1_tv,R.id.order_type2_tv,R.id.back_btn})
    public void click(View view){
        switch (view.getId()){
            case R.id.order_type1_tv://违章订单
//                RouterUtils.navigation(RouterConstants.ORDERLIST_MOUDLE_ACTIVITY);
//                finish();
                intent=new Intent(OrderStyleActivity.this,OrderActivity.class);
                intent.putExtra("title","普通订单");
                intent.putExtra("orderListType",1);
                startActivity(intent);
                finish();
                break;

            case R.id.order_type2_tv://年检订单
//                RouterUtils.navigation(RouterConstants.ORDERLIST_MOUDLE_ACTIVITY);
//                finish();
                intent=new Intent(OrderStyleActivity.this,OrderActivity.class);
                intent.putExtra("orderListType",2);
                intent.putExtra("title","年检订单");
                startActivity(intent);
                finish();
                break;

            case R.id.back_btn:
                finish();
                break;
        }

    }
}
