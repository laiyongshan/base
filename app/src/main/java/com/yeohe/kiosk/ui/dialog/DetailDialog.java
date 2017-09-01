package com.yeohe.kiosk.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;

import com.yeohe.kiosk.R;

/**
 * Created by Administrator on 2017/8/31.
 *
 * 违章详情页弹出框
 *
 */

public class DetailDialog extends Dialog {


    public DetailDialog(@NonNull Context context, @StyleRes int themeResId) {
        super(context, themeResId);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_detail);

    }
}
