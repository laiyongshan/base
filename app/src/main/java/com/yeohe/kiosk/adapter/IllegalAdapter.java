package com.yeohe.kiosk.adapter;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ccj.base.utils.router.RouterConstants;
import com.ccj.base.utils.router.RouterUtils;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.bean.FirstEvent;
import com.yeohe.kiosk.bean.Violation;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class IllegalAdapter extends BaseAdapter {
    private Activity context;
    private Violation violation;
    private  List<Violation.DataEntity.PeccancyListEntity>  peccancyList=new ArrayList<Violation.DataEntity.PeccancyListEntity>();
    ViewHolder holder;
    int type;

    public static HashMap<Integer, View> map = new HashMap<Integer,View>();

    public  List<Boolean> mChecked= new ArrayList<Boolean>();

    private Intent intent = new Intent();
    public final static String SELECTED_ACTION1 = "com.youhecheguanjia.illegallistselect";
    public final static String SELECTED_ACTION2="com.youhecheguanjia.quotedillegal_select";

    public IllegalAdapter(Activity context,Violation violation,int type){
        this.context=context;
        this.type=type;
        this.violation=violation;

        for (Violation.DataEntity.PeccancyListEntity peccancyListEntity : violation.getData().getPeccancyList()) {
            if (peccancyListEntity.getQuotedprice().equals("1")&&type==1) {
                peccancyList.add(peccancyListEntity);//实时报价
            } else if (peccancyListEntity.getQuotedprice().equals("2")&&type==2) {
                peccancyList.add(peccancyListEntity);//待报价
            } else if (peccancyListEntity.getQuotedprice().equals("-1")&&type==3) {
                peccancyList.add(peccancyListEntity);//不可代办
            }
        }

        if(peccancyList.size()==0) {
            EventBus.getDefault().post(
                    new FirstEvent(""+type));
        }

        for (int i = 0; i < peccancyList.size(); i++) {
            mChecked.add(true);
        }
    }

    @Override
    public int getCount() {
        return peccancyList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }



    Violation.DataEntity.PeccancyListEntity  peccancyListEntity;
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if (map.get(position) == null) {
            holder = new ViewHolder();
            convertView= LayoutInflater.from(context).inflate(R.layout.item_illegal_content,null);

            holder.time_tv= (TextView) convertView.findViewById(R.id.time_tv);
            holder.count_tv= (TextView) convertView.findViewById(R.id.count_tv);
            holder.degree_tv= (TextView) convertView.findViewById(R.id.degree_tv);
            holder.location_tv= (TextView) convertView.findViewById(R.id.location_tv);
            holder.latefee_tv= (TextView) convertView.findViewById(R.id.latefee_tv);
            holder.price_tv= (TextView) convertView.findViewById(R.id.price_tv);
            holder.orderstatus_tv= (TextView) convertView.findViewById(R.id.orderstatus_tv);

            holder.isselect_cb= (CheckBox) convertView.findViewById(R.id.isselect_cb);
            holder.detail_btn= (RoundTextView) convertView.findViewById(R.id.detail_btn);


            if ((peccancyList.get(position).getPickone() ==1)||peccancyList.get(position).getIscommit()==-1) {
                holder.isselect_cb.setEnabled(false);
            }

            final int p = position;
            holder.isselect_cb.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    CheckBox cb = (CheckBox) view;
                    mChecked.set(p, cb.isChecked());
                    if (cb.isChecked()) {
                        intent.putExtra("isSelect", 1);
                    } else {
                        intent.putExtra("isSelect", 0);
                    }

                    intent.putExtra("count", peccancyList.get(position).getCount());
                    intent.putExtra("degree", peccancyList.get(position).getDegree());
                    intent.putExtra("price", peccancyList.get(position).getPrice());
                    intent.putExtra("quotedprice",peccancyList.get(position).getQuotedprice());
                    intent.putExtra("lateFre",peccancyList.get(position).getLatefee());


                    if(type==1) {
                        intent.setAction(SELECTED_ACTION1);
                    }else if(type==2){
                        intent.setAction(SELECTED_ACTION2);
                    }

                    context.sendBroadcast(intent);

                }
            });

            convertView.setTag(holder);
        }else{
            convertView = map.get(position);
            holder = (ViewHolder) convertView.getTag();
        }

        peccancyListEntity=peccancyList.get(position);

        holder.time_tv.setText(peccancyListEntity.getTime());
        holder.count_tv.setText(peccancyListEntity.getCount());
        holder.location_tv.setText(peccancyListEntity.getLocation()+"...");
        holder.latefee_tv.setText(peccancyListEntity.getLatefee()+"");
        holder.price_tv.setText(peccancyListEntity.getPrice()+"");
        holder.degree_tv.setText(peccancyListEntity.getDegree());
        holder.orderstatus_tv.setText(peccancyListEntity.getOrderstatus());

        if(peccancyListEntity.getOrderstatus().equals("未处理")){

        }else{
            holder.orderstatus_tv.setTextColor(Color.rgb(80,196,147));
        }


        if(type==3){
            holder.isselect_cb.setVisibility(View.INVISIBLE);
        }else if(type==2){
            holder.isselect_cb.setVisibility(View.VISIBLE);
        }else if(type==1){
            holder.isselect_cb.setVisibility(View.VISIBLE);
        }


        if(peccancyList.get(position).getIscommit()==1){
            holder.isselect_cb.setChecked(true);
            holder.isselect_cb.setClickable(true);
            holder.isselect_cb.setVisibility(View.VISIBLE);
        }else{
            holder.isselect_cb.setChecked(false);
            holder.isselect_cb.setClickable(false);
            holder.isselect_cb.setVisibility(View.INVISIBLE);
        }



        holder.detail_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ARouter.getInstance().build(RouterConstants.QUERY_MOUDLE_DETAIL_ACIVITY)
                        .withString("orderstatus",peccancyList.get(position).getOrderstatus())
                        .withString("time",peccancyList.get(position).getTime())
                        .withString("location",peccancyList.get(position).getLocation())
                        .withString("reason",peccancyList.get(position).getReason())
                        .withString("count",peccancyList.get(position).getCount())
                        .withString("degree",peccancyList.get(position).getDegree())
                        .withString("archive",peccancyList.get(position).getArchive())
                        .withString("latefee",peccancyList.get(position).getLatefee()+"")
                        .withString("price",peccancyList.get(position).getPrice()+"")
                        .navigation();
            }
        });

        return convertView;
    }


    class ViewHolder {
        TextView time_tv,location_tv,count_tv,latefee_tv,price_tv,degree_tv,orderstatus_tv;
        CheckBox isselect_cb;
        RoundTextView detail_btn;
    }
}
