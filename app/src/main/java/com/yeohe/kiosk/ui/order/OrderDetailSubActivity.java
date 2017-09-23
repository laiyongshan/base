package com.yeohe.kiosk.ui.order;

import android.databinding.DataBindingUtil;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.ScrollView;

import com.flyco.roundview.RoundTextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.databinding.ActivityOrderDetailSubBinding;
import com.yeohe.kiosk.utils.ScreenSizeUtil;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 罚款列表
 * Created by Administrator on 2017/5/10 0010.
 */

public class OrderDetailSubActivity extends AppCompatActivity {

    private OrderDetailAdapter detailAdapter;
    private ArrayList<OrderDeatilBean.PeccancyListBean> peccancyList;
    private ActivityOrderDetailSubBinding bind;

    @BindView(R.id.main_body_layout)
    RelativeLayout main_body_layout;

    @BindView(R.id.back_btn)
    RoundTextView back_btn;

    @BindView(R.id.listview)
    ListView listview;

    int width,hight;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bind = DataBindingUtil.setContentView(this, R.layout.activity_order_detail_sub);
        ButterKnife.bind(this);

        width= ScreenSizeUtil.getScreenWidth(this);
        hight=ScreenSizeUtil.getScreenHeight(this);
        if(width<=hight) {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
        }else{
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = (int)(width*0.618);
            main_body_layout.setLayoutParams(linearParams);
            main_body_layout.setPadding(((int)(width*0.1)),0,((int)(width*0.1)),0);
        }


        if (getIntent().hasExtra("list")) {
            String str = getIntent().getStringExtra("list");
            try {

                peccancyList = new Gson().fromJson(str, new TypeToken<List<OrderDeatilBean.PeccancyListBean>>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }
//            peccancyList=getIntent().getParcelableArrayListExtra("list");
        }
        if (peccancyList == null) {
            finish();
            return;
        } else {
            init();
        }
    }

    @OnClick({R.id.back_btn})
    public void click(View view){
        switch (view.getId()){
            case R.id.back_btn:
                finish();
                break;
        }
    }


    private void init() {
        detailAdapter = new OrderDetailAdapter(this);
        detailAdapter.setData(peccancyList);
        bind.listview.setAdapter(detailAdapter);
    }
}
