package com.yeohe.kiosk.utils;

import android.app.Activity;

import com.alibaba.android.arouter.launcher.ARouter;
import com.ccj.base.utils.router.RouterConstants;
import com.yeohe.kiosk.ui.addcar.AddCarActivity;

import org.greenrobot.eventbus.EventBus;

import cn.pedant.SweetAlert.SweetAlertDialog;

/**
 * Created by Administrator on 2017/9/21.
 */

public class DialogUtil {


    public static void showErrDialog(final Activity activity,String title,String err){
        new SweetAlertDialog(activity, SweetAlertDialog.ERROR_TYPE)
                .setTitleText(""+title)
                .setContentText(""+err)
                .setConfirmText("确定")
                .show();
    }

}
