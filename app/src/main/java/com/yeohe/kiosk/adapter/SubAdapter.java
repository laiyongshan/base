package com.yeohe.kiosk.adapter;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.yeohe.kiosk.R;
import com.yeohe.kiosk.bean.Annual;

import java.util.ArrayList;
import java.util.List;

public class SubAdapter extends BaseAdapter {
	
	Context context;
	LayoutInflater layoutInflater;
	public int foodpoition;

	private Annual annual;

	public SubAdapter(Context context, Annual annual, int position) {
		this.context = context;
		layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.foodpoition = position;
		this.annual=annual;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return annual.getData().getProvinces().get(foodpoition).getDetails().size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return getItem(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		ViewHolder viewHolder = null;
		final int location=position;
		if (convertView == null) {
			convertView = layoutInflater.inflate(R.layout.sublist_item, null);
			viewHolder = new ViewHolder();
			viewHolder.textView = (TextView) convertView
					.findViewById(R.id.letter_tv);
			convertView.setTag(viewHolder);
		} else {
			viewHolder = (ViewHolder) convertView.getTag();
		}
		viewHolder.textView.setText(annual.getData().getProvinces().get(foodpoition).getDetails().get(position).getZimu()+"");
		viewHolder.textView.setTextColor(Color.BLACK);
		
		return convertView;
	}

	public static class ViewHolder {
		public TextView textView;
	}

}
