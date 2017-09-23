package com.yeohe.kiosk.bean;

/**
 * Created by Administrator on 2017/9/21.
 */

public class Condition {


    /**
     * status : ok
     * code : 0
     * msg : 处理成功
     * data : {"carcodelen":99,"cardrivelen":99}
     * time : 2017-09-21 15:06:31
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
         * carcodelen : 99
         * cardrivelen : 99
         */

        private int carcodelen;
        private int cardrivelen;

        public void setCarcodelen(int carcodelen) {
            this.carcodelen = carcodelen;
        }

        public void setCardrivelen(int cardrivelen) {
            this.cardrivelen = cardrivelen;
        }

        public int getCarcodelen() {
            return carcodelen;
        }

        public int getCardrivelen() {
            return cardrivelen;
        }
    }
}
