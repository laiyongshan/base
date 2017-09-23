package com.yeohe.kiosk.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.TextView;

import com.yeohe.kiosk.R;
import com.yeohe.kiosk.ui.query.fragment.RealTimeFragment;


/**
 * Created by Administrator on 2017/6/27.
 */

public class KonwDialog extends Dialog implements View.OnClickListener{

    private TextView konw_cancel_tv,konw_sure_tv;

    public interface AgreeInterface{
        void isAgree(boolean isagree);
    }

    AgreeInterface agreeInterface;

    public KonwDialog(@NonNull Context context, @StyleRes int themeResId,AgreeInterface agreeInterface) {
        super(context, themeResId);
        this.agreeInterface=agreeInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_konw);

        initView();
    }


    /*
    * 初始化控件
    * */
    private void initView(){
        konw_cancel_tv= (TextView) findViewById(R.id.konw_cancel_tv);
        konw_cancel_tv.setOnClickListener(this);
        konw_sure_tv= (TextView) findViewById(R.id.konw_sure_tv);
        konw_sure_tv.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.konw_cancel_tv:
                agreeInterface.isAgree(false);
                break;

            case R.id.konw_sure_tv:
                agreeInterface.isAgree(true);
                break;
        }
    }
}
