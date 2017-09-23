package com.yeohe.kiosk.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;

import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/9.
 */

public class AnnualTipsDialog extends Dialog {

    @BindView(R.id.dismiss_tv)
    RoundTextView dismiss_tv;


    public AnnualTipsDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_annual_tips);

        ButterKnife.bind(this);

    }

    @OnClick(R.id.dismiss_tv)
    public void click(View view){
        switch(view.getId()){
            case R.id.dismiss_tv:
                dismiss();
                break;
        }
    }

}
