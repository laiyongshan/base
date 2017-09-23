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
import com.ccj.base.utils.SharedPreferenceUtil;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.ui.addcar.AddCarActivity;
import com.yeohe.kiosk.ui.addcar.AddCarContract;
import com.yeohe.kiosk.utils.ScreenSizeUtil;
import com.yeohe.kiosk.widgets.RoundImageView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/23.
 */

public class ExitDialog extends Dialog {
    @BindView(R.id.exit_img)
    RoundImageView exit_img;

    @BindView(R.id.cancel_btn)
    ImageView cancel_btn;

    @BindView(R.id.sure_exit_btn)
    RoundTextView sure_exit_btn;


    private Activity context;

    public ExitDialog(@NonNull Activity context, @StyleRes int themeResId) {
        super(context, themeResId);
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_exit);

        ButterKnife.bind(this);

        setCanceledOnTouchOutside(false);

        Glide.with(context)
                .load("http://upload-images.jianshu.io/upload_images/2972448-78cb561ab24f41c4.jpg?imageMogr2/auto-orient/strip%7CimageView2/2/w/1240")
                .centerCrop()
                .into(exit_img);

    }


    @OnClick({R.id.exit_img,R.id.cancel_btn,R.id.sure_exit_btn})
    public void click(View view){
        switch(view.getId()){
            case R.id.exit_img:
                dismiss();
                break;

            case R.id.cancel_btn:
                dismiss();
                break;

            case R.id.sure_exit_btn:
                SharedPreferenceUtil.getInstance().DeleteUser();//退出删除用户名称
                SharedPreferenceUtil.getInstance().DeleteToken();//退出删除token值
                dismiss();
                break;
        }
    }

}
