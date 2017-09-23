package com.yeohe.kiosk.bean;

import com.ccj.base.base.BaseBean;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2017/4/12 0012.
 */

public class CarType  extends BaseBean{

    /**
     * status : ok
     * code : 0
     * msg : 处理成功
     * data : {"carTypeList":[{"typecode":"02","typename":"小型汽车"}]}
     * time : 2017-09-12 14:51:58
     */

    private String status;
    private int code;
    private String msg;
    private DataEntity data;
    private String time;

    public void setStatus(String status) {
        this.status = status;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void setData(DataEntity data) {
        this.data = data;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getStatus() {
        return status;
    }

    public int getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public DataEntity getData() {
        return data;
    }

    public String getTime() {
        return time;
    }

    public static class DataEntity {
        /**
         * carTypeList : [{"typecode":"02","typename":"小型汽车"}]
         */

        private ArrayList<CarTypeListEntity> carTypeList;

        public void setCarTypeList(ArrayList<CarTypeListEntity> carTypeList) {
            this.carTypeList = carTypeList;
        }

        public ArrayList<CarTypeListEntity> getCarTypeList() {
            return carTypeList;
        }

        public static class CarTypeListEntity {
            /**
             * typecode : 02
             * typename : 小型汽车
             */

            private String typecode;
            private String typename;

            public void setTypecode(String typecode) {
                this.typecode = typecode;
            }

            public void setTypename(String typename) {
                this.typename = typename;
            }

            public String getTypecode() {
                return typecode;
            }

            public String getTypename() {
                return typename;
            }
        }
    }
}
