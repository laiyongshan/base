package com.yeohe.kiosk.vollery;

import com.android.volley.VolleyError;

/**
 * Created by Administrator on 2016/9/8 0008.
 * volleyInterface 是自定义回调接口
 */

public interface VolleyInterface {
    void ResponseResult(Object jsonObject) ;

    void ResponError(VolleyError volleyError);
}

