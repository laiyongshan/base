package com.yeohe.kiosk.ui.order;

import android.content.Context;
import android.databinding.ViewDataBinding;

import com.yeohe.kiosk.R;
import com.yeohe.kiosk.databinding.AdapterOrderDetailBinding;
import com.yeohe.kiosk.utils.StringUtils;

import java.util.ArrayList;

/**
 * 订单详情 适配器
 * Created by Administrator on 2017/5/8 0008.
 */

public class OrderDetailAdapter extends CommonBindAdapter<OrderDeatilBean.PeccancyListBean> {

    public OrderDetailAdapter(Context context) {
        super(context, new ArrayList<OrderDeatilBean.PeccancyListBean>(), R.layout.adapter_order_detail);
    }

    @Override
    public void convert(ViewDataBinding bind, OrderDeatilBean.PeccancyListBean t, int position) {
        AdapterOrderDetailBinding binding = (AdapterOrderDetailBinding) bind;
        binding.setBean(t);
        //设置滞纳金
        binding.delayPrice.setText(StringUtils.isEmpty(t.getLatefine())?"":"¥"+t.getLatefine());
        //设置服务费
        binding.servicePrice.setText(StringUtils.isEmpty(t.getPrice()) ? ""
                : (t.getPrice().equals("0") ? "待报价" :
                "¥" + t.getPrice()));
//        Log.d("TAG", t.toString());
    }
}
