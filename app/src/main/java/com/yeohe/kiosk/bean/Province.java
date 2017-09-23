package com.yeohe.kiosk.bean;

/**
 * Created by Administrator on 2016/9/12 0012.
 */


import com.ccj.base.base.BaseBean;

import java.io.Serializable;
import java.util.List;

public class Province extends BaseBean implements Serializable {
    private static final long serialVersionUID = -1436898785973108723L;


    /**
     * status : ok
     * code : 0
     * msg : 处理成功
     * data : {"provinceList":[{"provinceprefix":"粤"},{"provinceprefix":"冀"},{"provinceprefix":"皖"},{"provinceprefix":"渝"},{"provinceprefix":"陕"},{"provinceprefix":"藏"},{"provinceprefix":"鄂"},{"provinceprefix":"川"},{"provinceprefix":"吉"},{"provinceprefix":"云"},{"provinceprefix":"蒙"},{"provinceprefix":"桂"},{"provinceprefix":"苏"},{"provinceprefix":"鲁"},{"provinceprefix":"宁"},{"provinceprefix":"甘"},{"provinceprefix":"京"},{"provinceprefix":"豫"},{"provinceprefix":"贵"},{"provinceprefix":"闽"},{"provinceprefix":"琼"},{"provinceprefix":"新"},{"provinceprefix":"沪"},{"provinceprefix":"浙"},{"provinceprefix":"辽"},{"provinceprefix":"青"},{"provinceprefix":"黑"}]}
     * time : 2017-09-12 11:16:50
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
         * provinceList : [{"provinceprefix":"粤"},{"provinceprefix":"冀"},{"provinceprefix":"皖"},{"provinceprefix":"渝"},{"provinceprefix":"陕"},{"provinceprefix":"藏"},{"provinceprefix":"鄂"},{"provinceprefix":"川"},{"provinceprefix":"吉"},{"provinceprefix":"云"},{"provinceprefix":"蒙"},{"provinceprefix":"桂"},{"provinceprefix":"苏"},{"provinceprefix":"鲁"},{"provinceprefix":"宁"},{"provinceprefix":"甘"},{"provinceprefix":"京"},{"provinceprefix":"豫"},{"provinceprefix":"贵"},{"provinceprefix":"闽"},{"provinceprefix":"琼"},{"provinceprefix":"新"},{"provinceprefix":"沪"},{"provinceprefix":"浙"},{"provinceprefix":"辽"},{"provinceprefix":"青"},{"provinceprefix":"黑"}]
         */

        private List<ProvinceListEntity> provinceList;

        public void setProvinceList(List<ProvinceListEntity> provinceList) {
            this.provinceList = provinceList;
        }

        public List<ProvinceListEntity> getProvinceList() {
            return provinceList;
        }

        public static class ProvinceListEntity {
            /**
             * provinceprefix : 粤
             */

            private String provinceprefix;

            public void setProvinceprefix(String provinceprefix) {
                this.provinceprefix = provinceprefix;
            }

            public String getProvinceprefix() {
                return provinceprefix;
            }
        }
    }
}
