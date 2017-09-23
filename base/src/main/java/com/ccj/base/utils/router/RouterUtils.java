package com.ccj.base.utils.router;

import android.app.Activity;
import android.widget.Toast;

import com.alibaba.android.arouter.facade.Postcard;
import com.alibaba.android.arouter.facade.callback.NavigationCallback;
import com.alibaba.android.arouter.launcher.ARouter;

//https://github.com/alibaba/ARouter
public class RouterUtils {



    public static void inject(Object obj){
        ARouter.getInstance().inject(obj);

    }

    /**
     * 跳转
     * 得到 跳转对象
     * @param path
     * @return
     */
    public static Object navigation(String path) {
        // 构建标准的路由请求
        return  ARouter.getInstance().build(path).navigation();
    }




    public static void startActivityForResult(Activity activity,String path,int requestCode,String argKey,Object argValue) {
        ARouter.getInstance().build(path).
                withObject(argKey, argValue).
                navigation(activity, requestCode);
    }

    public void startActivityForCallback(final Activity activity, String path) {
    // 使用两个参数的navigation方法，可以获取单次跳转的结果
        ARouter.getInstance().build(path).navigation(activity, new NavigationCallback() {
            @Override
            public void onFound(Postcard postcard) {
                Toast.makeText(activity, "发现目标Activity", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onLost(Postcard postcard) {
                Toast.makeText(activity, "没有目标Activity", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onArrival(Postcard postcard) {
                Toast.makeText(activity, "跳转完成", Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onInterrupt(Postcard postcard) {
                Toast.makeText(activity, "已被拦截", Toast.LENGTH_SHORT).show();
            }
        });
    }


}


