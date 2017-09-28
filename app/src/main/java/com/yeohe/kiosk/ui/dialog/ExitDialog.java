package com.yeohe.kiosk.ui.dialog;

import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.bumptech.glide.Glide;
import com.ccj.base.base.Constants;
import com.ccj.base.utils.SharedPreferenceUtil;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.bean.FirstEvent;
import com.yeohe.kiosk.ui.addcar.AddCarActivity;
import com.yeohe.kiosk.ui.addcar.AddCarContract;
import com.yeohe.kiosk.utils.ScreenSizeUtil;
import com.yeohe.kiosk.widgets.RoundImageView;

import org.greenrobot.eventbus.EventBus;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/23.
 */

public class ExitDialog extends Dialog {

    @BindView(R.id.exit_img)
    ImageView exit_img;

    @BindView(R.id.cancel_btn)
    ImageView cancel_btn;

    @BindView(R.id.sure_exit_btn)
    RoundTextView sure_exit_btn;

    @BindView(R.id.main_body_layout)
    RelativeLayout main_body_layout;

    private Activity context;


    public interface ExitInterface{
        void exit();
    }

    ExitInterface exitInterface;

    int width, hight;

    public ExitDialog(@NonNull Activity context, @StyleRes int themeResId,ExitInterface exitInterface) {
        super(context, themeResId);
        this.context=context;
        this.exitInterface=exitInterface;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit);

        ButterKnife.bind(this);

        width = ScreenSizeUtil.getScreenWidth(context);
        hight = ScreenSizeUtil.getScreenHeight(context);
        if (width <= hight) {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width = width / 10 *5;
            linearParams.height=hight/10*5;
            main_body_layout.setLayoutParams(linearParams);
        } else {
            RelativeLayout.LayoutParams linearParams = (RelativeLayout.LayoutParams) main_body_layout.getLayoutParams();
            linearParams.width =(int)(width / 10 *3);
            linearParams.height=hight/10*7;
            main_body_layout.setLayoutParams(linearParams);
        }

        Glide.with(context)
                .load("http://img3.duitang.com/uploads/item/201508/09/20150809071105_Vm2Jr.jpeg")
                .centerCrop()
                .into(exit_img);
    }


    @OnClick({R.id.cancel_btn,R.id.sure_exit_btn})
    public void click(View view){
        switch(view.getId()){

            case R.id.cancel_btn:
                dismiss();
                break;

            case R.id.sure_exit_btn:
                SharedPreferenceUtil.getInstance().DeleteUser();//退出删除用户名称
                SharedPreferenceUtil.getInstance().DeleteToken();//退出删除token值
                SharedPreferenceUtil.getInstance().setIsLogin(false);
                exitInterface.exit();
                dismiss();
                break;
        }
    }

}
