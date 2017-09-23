package com.yeohe.kiosk.ui.order;

import android.content.Context;
import android.databinding.ViewDataBinding;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ListView;
import android.widget.RelativeLayout;

import com.flyco.roundview.RoundTextView;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.databinding.AdapterOrderFillingListBinding;
import com.yeohe.kiosk.utils.ScreenSizeUtil;
import com.yeohe.kiosk.utils.StringUtils;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * 订单补款 列表
 * Created by Administrator on 2017/5/27 0027.
 */

public class OrderFillingListActivity extends AppCompatActivity {

    private ListView listView;
    private ArrayList<OrderDeatilBean.MakeUpMoneyBean> makeUpMoneyList;
    private OrderFillAdapter adapter;

    @BindView(R.id.main_body_layout)
    RelativeLayout main_body_layout;

    @BindView(R.id.back_btn)
    RoundTextView back_btn;

    @BindView(R.id.list)
    ListView list;


    int width,hight;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_filling_list);
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

                makeUpMoneyList = new Gson().fromJson(str, new TypeToken<List<OrderDeatilBean.MakeUpMoneyBean>>() {
                }.getType());
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        if (makeUpMoneyList == null) {
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


    /**
     * 初始化
     */
    private void init() {
        listView = (ListView) findViewById(R.id.list);
        adapter = new OrderFillAdapter(this);
        adapter.setData(makeUpMoneyList);
        listView.setAdapter(adapter);

    }



    class OrderFillAdapter extends CommonBindAdapter<OrderDeatilBean.MakeUpMoneyBean> {

        public OrderFillAdapter(Context context) {
            super(context, new ArrayList<OrderDeatilBean.MakeUpMoneyBean>(), R.layout.adapter_order_filling_list);
        }

        @Override
        public void convert(ViewDataBinding bind, OrderDeatilBean.MakeUpMoneyBean t, int position) {
            AdapterOrderFillingListBinding b = (AdapterOrderFillingListBinding) bind;
            //金额
            b.price.setText(StringUtils.isEmpty(t.getExtra_money()) ? "" : ("¥" + t.getExtra_money() + ""));
            //原因
            b.reason.setText(StringUtils.isEmpty(t.getRemark()) ? "" : t.getRemark());
            //创建时间
            b.createTimeStr.setText(StringUtils.isEmpty(t.getCreatetimestr()) ? "" : ("创建时间：" + t.getCreatetimestr()));
            //支付时间
            if (StringUtils.isEmpty(t.getPaytimestr()))
                b.payTimeStr.setVisibility(View.GONE);
            else {
                b.payTimeStr.setVisibility(View.VISIBLE);
                b.payTimeStr.setText("支付时间：" + t.getPaytimestr() + "");
            }
            if (t.getStatus() != null && t.getStatus().equals("2"))//1未支付2已支付
                b.imageStatus.setVisibility(View.VISIBLE);
            else
                b.imageStatus.setVisibility(View.GONE);

        }
    }


}
