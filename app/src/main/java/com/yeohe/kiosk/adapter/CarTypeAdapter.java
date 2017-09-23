package com.yeohe.kiosk.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.ccj.base.adapter.BaseListAdapter;
import com.ccj.base.base.BaseBean;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.bean.CarType;
import com.yeohe.kiosk.bean.CarType.DataEntity;

import java.util.ArrayList;
import java.util.List;

import static com.ccj.base.api.VolleyUtils.context;
import static com.yeohe.kiosk.bean.CarType.DataEntity.*;

/**
 * Created by Administrator on 2017/9/12.
 */

public class CarTypeAdapter extends BaseAdapter{

    ViewHolder holder;
    ArrayList<DataEntity.CarTypeListEntity> cartypeList;

    public CarTypeAdapter(Context context,ArrayList<CarTypeListEntity> cartypeList) {
        this.cartypeList=cartypeList;
    }

    @Override
    public int getCount() {
        return cartypeList.size();
    }

    @Override
    public Object getItem(int position) {
        return cartypeList.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    /**
     * 重写此方法即可,
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView==null){
            holder = new ViewHolder();
            convertView=LayoutInflater.from(context).inflate(R.layout.item_cartype_list,null);
            holder.car_type_tv= (TextView) convertView.findViewById(R.id.car_type_tv);
            holder.car_type_iv= (ImageView) convertView.findViewById(R.id.car_type_iv);

            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }

        holder.car_type_tv.setText(cartypeList.get(position).getTypename());
//        holder.car_type_tv.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                holder.car_type_iv.setVisibility(View.VISIBLE);
//            }
//        });

        return convertView;
    }


    class ViewHolder {
        TextView car_type_tv;
        ImageView car_type_iv;
    }
}
