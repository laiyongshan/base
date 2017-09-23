package com.yeohe.kiosk.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.GridView;

import com.ccj.base.utils.TLog;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.adapter.Province_GV_Adapter;
import com.yeohe.kiosk.bean.Province;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/8/31 0031.
 */
public class ProvinceDialog extends Dialog implements AdapterView.OnItemClickListener {
    private Context mContext;
    private Window window = null;
    private GridView province_gv;
    private int type;
    private List<Province.DataEntity.ProvinceListEntity> provinces = new ArrayList<Province.DataEntity.ProvinceListEntity>();
    public static String province = "";

    public interface ProvinceCallBack{
        void provinceCallBack(String provincceCode);
    }

    public ProvinceCallBack provinceCallBack;

    public ProvinceDialog(Context context, int themeResId, List<Province.DataEntity.ProvinceListEntity>  provinces,ProvinceCallBack provinceCallBack) {
        super(context, themeResId);
        this.mContext = context;
        this.provinces = provinces;
        this.provinceCallBack=provinceCallBack;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_province);
        initView();
    }

    public void initView() {
        province_gv = (GridView) findViewById(R.id.provincecode_gv);
        province_gv.setAdapter(new Province_GV_Adapter(mContext, provinces));
        province_gv.setOnItemClickListener(this);
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
//        window.setBackgroundDrawableResource(Color.GRAY); //设置对话框背景为半透明
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

    @Override
    public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

        TLog.logI(Province_GV_Adapter.provinceList.get(i).getProvinceprefix());
        provinceCallBack.provinceCallBack(Province_GV_Adapter.provinceList.get(i).getProvinceprefix());
        dismiss();
    }

}
