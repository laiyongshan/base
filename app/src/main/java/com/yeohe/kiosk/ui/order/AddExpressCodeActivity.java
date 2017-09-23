package com.yeohe.kiosk.ui.order;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.yeohe.kiosk.R;
import com.yeohe.kiosk.http.URLs;
import com.yeohe.kiosk.utils.EncryptUtil;
import com.yeohe.kiosk.utils.TokenSQLUtils;
import com.yeohe.kiosk.utils.UIHelper;
import com.yeohe.kiosk.vollery.VolleyInterface;
import com.yeohe.kiosk.vollery.VolleyUtil;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

/**
 * Created by Administrator on 2017/6/22.
 */

public class AddExpressCodeActivity extends Activity {
    private EditText express_code_et;
    private Button makesure_add_btn;
    private Context context;

    private ImageView back_iv;

    private String ordercoder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.dialog_add_express_number);

        context=AddExpressCodeActivity.this;

        ordercoder=getIntent().getStringExtra("ordercoder");

        initViews();
    }


    /*
    * 初始化控件
    * */
    private void initViews(){
        express_code_et= (EditText) findViewById(R.id.express_code_et);
        makesure_add_btn= (Button) findViewById(R.id.makesure_add_btn);
        makesure_add_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(express_code_et.getText().toString().trim().equals("")){
                    Toast.makeText(context,"请填写快递单号", Toast.LENGTH_SHORT).show();
                }else{//确定添加快递单号
                    addExpressNum();
                }
            }
        });


    }


    /*
    * 客户添加年检快递单号
    * */
    private void addExpressNum(){
        UIHelper.showPd(context);
        HashMap map=new HashMap();
        map.put("token", TokenSQLUtils.check());
        map.put("ordercode",ordercoder==null?"":ordercoder);//订单号
        map.put("clientexpress",express_code_et.getText().toString().trim());//快递单号

        VolleyUtil.getVolleyUtil(context).StringRequestPostVolley(URLs.ADD_EXPRESSE_NUMBER, EncryptUtil.encrypt(map), new VolleyInterface() {
            @Override
            public void ResponseResult(Object jsonObject) {
                try {
                    JSONObject obj=new JSONObject(EncryptUtil.decryptJson(jsonObject.toString(),context));
                    JSONObject dataObj=obj.optJSONObject("data");
                    int response_code=dataObj.optInt("response_code");
                    String response_msg=dataObj.optString("response_msg");
                    String ordercode=dataObj.optString("ordercode");
                    if(response_code==1){//添加成功
                        Intent intent=new Intent();
                        intent.putExtra("ordercode",ordercode);
                        AddExpressCodeActivity.this.setResult(123,intent);
                        AddExpressCodeActivity.this.finish();
                    }else{//添加失败
                        Toast.makeText(context,""+response_msg, Toast.LENGTH_SHORT).show();
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }finally {
                    UIHelper.dismissPd();
                }

            }

            @Override
            public void ResponError(VolleyError volleyError) {

            }
        });
    }
}
