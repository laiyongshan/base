package com.yeohe.kiosk.ui.query;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alibaba.android.arouter.facade.annotation.Autowired;
import com.alibaba.android.arouter.facade.annotation.Route;
import com.alibaba.android.arouter.launcher.ARouter;
import com.ccj.base.base.BaseActivity;
import com.ccj.base.utils.BitmapUtil;
import com.ccj.base.utils.TLog;
import com.ccj.base.utils.router.RouterConstants;
import com.flyco.roundview.RoundTextView;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.bean.Violation;

import butterknife.BindView;
import butterknife.BindViews;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by Administrator on 2017/9/13.
 */
@Route(path = RouterConstants.QUERY_MOUDLE_DETAIL_ACIVITY)
public class IllegalDetailActivity extends Activity {

    @BindView(R.id.close_btn)
    RoundTextView close_btn;

    @BindViews({R.id.orderstatus_tv,
            R.id.archive_tv,R.id.location_tv,R.id.reason_tv,R.id.time_tv,R.id.count_tv,R.id.degree_tv,R.id.price_tv,R.id.latefee_tv})
    TextView[] tvs;

    @Autowired
    String orderstatus;

    @Autowired
    String archive;

    @Autowired
    String location;

    @Autowired
    String reason;

    @Autowired
    String time;

    @Autowired
    String count;

    @Autowired
    String degree;

    @Autowired
    String price;

    @Autowired
    String latefee;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_illegal_detail);
        getWindow().setLayout(RelativeLayout.LayoutParams.FILL_PARENT, RelativeLayout.LayoutParams.FILL_PARENT);

        ARouter.getInstance().inject(this);
        ButterKnife.bind(this);

        initView();
    }

    //初始化控件
    private void initView(){
        tvs[0].setText(orderstatus+"");
        tvs[1].setText(archive+"");
        tvs[2].setText(location+"");
        tvs[3].setText(reason+"");
        tvs[4].setText(time+"");
        tvs[5].setText(count+"");
        tvs[6].setText(degree+"");
        tvs[7].setText(price+"");
        tvs[8].setText(latefee);
    }


    @OnClick({R.id.close_btn})
    public void click(View view){
        switch (view.getId()){
            case R.id.close_btn:
                finish();
                break;
        }
    }

}
