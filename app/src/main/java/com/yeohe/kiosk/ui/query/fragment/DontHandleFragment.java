package com.yeohe.kiosk.ui.query.fragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.LinearLayout;

import com.yeohe.kiosk.R;
import com.yeohe.kiosk.adapter.IllegalAdapter;
import com.yeohe.kiosk.bean.FirstEvent;
import com.yeohe.kiosk.bean.Violation;
import com.yeohe.kiosk.widgets.MyListView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

@SuppressLint("ValidFragment")
public class DontHandleFragment extends Fragment implements AdapterView.OnItemClickListener{

    private View view;

    @BindView(R.id.donthandle_illegal_lv)
    MyListView dont_handle_lv;

    @BindView(R.id.no_illegal_layout)
    LinearLayout no_illegal_layout;


    private IllegalAdapter illegalAdapter;
    private Violation violation;

    public DontHandleFragment(Violation violation){
        this.violation=violation;
    }


    @Override
    public View onCreateView(LayoutInflater inflater,  ViewGroup container, Bundle savedInstanceState) {

        view=inflater.inflate(R.layout.fragment_dont_handle,null);
        ButterKnife.bind(this, view);

        //注册EventBus
        EventBus.getDefault().register(this);

        initView();//初始化控件

        return view;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);//反注册EventBus
    }


    @Subscribe
    public void onEvent(FirstEvent event) {
    /* Do something */
        if(event.getMsg().equals("3")){
            no_illegal_layout.setVisibility(View.VISIBLE);
        }
    }

    private void initView(){

        illegalAdapter=new IllegalAdapter(getActivity(),violation,3);
        dont_handle_lv.setAdapter(illegalAdapter);
    }


    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}