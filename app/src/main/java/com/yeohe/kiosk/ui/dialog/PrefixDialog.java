package com.yeohe.kiosk.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;

import com.yeohe.kiosk.R;
import com.yeohe.kiosk.adapter.PreAdapter;
import com.yeohe.kiosk.adapter.SubAdapter;
import com.yeohe.kiosk.bean.Annual;
import com.yeohe.kiosk.widgets.MultiListView;

import java.util.List;

/**
 * Created by Administrator on 2017/6/16.
 */

public class PrefixDialog extends Dialog {

    private Context mContext;
    private Window window = null;
    private MultiListView preListView;
    private MultiListView subListView;

    private Annual annual;

    private PreAdapter preAdapter;
    private SubAdapter subAdapter;

    public static String PREFIX_ACTION_NAME = "com.youhecheguanjia.prefix";

    public PrefixDialog(@NonNull Context context, @StyleRes int themeResId, Annual annual) {
        super(context, themeResId);
        this.mContext = context;
        this.annual=annual;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_prefix);

        initView();//初始化控件

        preAdapter=new PreAdapter(mContext,annual);
        preListView.setAdapter(preAdapter);

        selectDefult();

        preListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> arg0, View arg1, int position,
                                    long arg3) {
                // TODO Auto-generated method stub
                final int location=position;
                preAdapter.setSelectedPosition(position);
                preAdapter.notifyDataSetInvalidated();
                subAdapter=new SubAdapter(mContext, annual, position);
                subAdapter.notifyDataSetChanged();
                subListView.setAdapter(subAdapter);
                subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {

                    @Override
                    public void onItemClick(AdapterView<?> arg0, View arg1,
                                            int position, long arg3) {
                        // TODO Auto-generated method stub
                        Intent intent=new Intent();
                        intent.setAction(PREFIX_ACTION_NAME);
                        intent.putExtra("prefix",annual.getData().getProvinces().get(location).getProvince()+annual.getData().getProvinces().get(location).getDetails().get(position).getZimu());
                        intent.putExtra("price",annual.getData().getProvinces().get(location).getDetails().get(position).getPrice());
                        mContext.sendBroadcast(intent);
                    }
                });
            }
        });

    }


    /*
    * 初始化控件
    * */
    private void initView(){
        preListView=(MultiListView) findViewById(R.id.preListView);
        subListView=(MultiListView) findViewById(R.id.subListView);
    }

    private void selectDefult(){
        if(annual!=null) {
            final int location = 0;
            preAdapter.setSelectedPosition(0);
            preAdapter.notifyDataSetInvalidated();
            subAdapter = new SubAdapter(mContext, annual, 0);
            subListView.setAdapter(subAdapter);
            subListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                @Override
                public void onItemClick(AdapterView<?> arg0, View arg1,
                                        int position, long arg3) {
                    // TODO Auto-generated method stub
                    Intent intent = new Intent();
                    intent.setAction(PREFIX_ACTION_NAME);
                    intent.putExtra("prefix",annual.getData().getProvinces().get(location).getProvince()+annual.getData().getProvinces().get(location).getDetails().get(position).getZimu());
                    intent.putExtra("price",annual.getData().getProvinces().get(location).getDetails().get(position).getPrice());
                    mContext.sendBroadcast(intent);
                }
            });
        }
    }



    public void showDialog() {
        show();
        windowDeploy();
        //设置触摸对话框意外的地方取消对话框
        setCanceledOnTouchOutside(true);
    }


    //设置窗口显示动画
    public void windowDeploy() {
        window = getWindow(); //得到对话框
//        window.setWindowAnimations(R.style.dialogWindowAnim); //设置窗口弹出动画
//        window.setBackgroundDrawableResource(Color.WHITE); //设置对话框背景为半透明
        if(window!=null) {
            WindowManager.LayoutParams wl = window.getAttributes();
            //根据x，y坐标设置窗口需要显示的位置
//        wl.x = x; //x小于0左移，大于0右移
//        wl.y = y; //y小于0上移，大于0下移
            // wl.alpha = 0.5f; //设置透明度
            WindowManager m = window.getWindowManager();
            Display d = m.getDefaultDisplay();
            wl.gravity = Gravity.BOTTOM; //设置重力
            window.setAttributes(wl);
            wl.width = d.getWidth(); //设置dialog的宽度为当前手机屏幕的宽度
            getWindow().setAttributes(wl);
        }
    }

}
