package com.yeohe.kiosk.utils;

import android.app.Activity;

/**
 * Created by Administrator on 2017/8/31.
 */

public class ScreenSizeUtil {


    /*
    * 获取屏幕宽度
    * */
    public static int getScreenWidth(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getWidth();
    }


    /*
    * 获取屏幕高度
     */
    public static int getScreenHeight(Activity activity) {
        return activity.getWindowManager().getDefaultDisplay().getHeight();
    }

}
