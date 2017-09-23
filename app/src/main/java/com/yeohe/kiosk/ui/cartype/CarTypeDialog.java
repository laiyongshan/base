package com.yeohe.kiosk.ui.cartype;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.StyleRes;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import com.yeohe.kiosk.R;
import com.yeohe.kiosk.adapter.CarTypeAdapter;
import com.yeohe.kiosk.bean.CarType;
import com.yeohe.kiosk.ui.addcar.AddCarActivity;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnItemClick;

/**
 * Created by Administrator on 2017/9/11.
 */

public class CarTypeDialog extends Dialog {

    @BindView(R.id.car_type_lv)
    ListView car_type_lv;
    CarTypeInterface carTypeInterface;

    private ArrayList<CarType.DataEntity.CarTypeListEntity> cartypeList=null;
    private Context context;

    public interface CarTypeInterface{
        public void cartype_callback(String typename,String typecode);
    }


    public CarTypeDialog(Context context, ArrayList<CarType.DataEntity.CarTypeListEntity> cartypeList, @StyleRes int themeResId, CarTypeInterface carTypeInterface) {
        super(context, themeResId);
        this.carTypeInterface=carTypeInterface;
        this.cartypeList=cartypeList;
        this.context=context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dialog_cartype);
        ButterKnife.bind(this);

        car_type_lv.setAdapter(new CarTypeAdapter(context,cartypeList));
        car_type_lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                carTypeInterface.cartype_callback(cartypeList.get(position).getTypename(),cartypeList.get(position).getTypecode());
                dismiss();
            }
        });

    }

}
