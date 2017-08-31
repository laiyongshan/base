package com.yeohe.kiosk.ui.main;

import android.app.Application;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.alibaba.android.arouter.facade.annotation.Route;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.utils.router.RouterConstants;
import com.tencent.bugly.beta.Beta;
import com.tencent.bugly.crashreport.CrashReport;
import com.yeohe.kiosk.AppApplication;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.utils.ScreenSizeUtil;
import com.zhy.http.okhttp.OkHttpUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


@Route(path = RouterConstants.MAIN_MOUDLE_ACTIVITY)
public class MainActivity extends BaseActivity<MainContract.Presenter> implements MainContract.View{
    @BindView(R.id.test_btn)
    Button test_btn;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //绑定组件
        ButterKnife.bind(this);


    }


    @OnClick(R.id.test_btn)
    public void click(View view){
        switch (view.getId()){
            case R.id.test_btn:
//                CrashReport.testJavaCrash();
//                Beta.checkUpgrade();
                AppApplication.showLongToast(ScreenSizeUtil.getScreenHeight(MainActivity.this)+"");
                break;
        }
    }
}
