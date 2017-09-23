package com.yeohe.kiosk.bean;

import com.ccj.base.base.BaseBean;

import java.util.List;

/**
 * Created by Administrator on 2017/6/14.
 */

public class Annual extends BaseBean {


    /**
     * status : ok
     * code : 0
     * msg : 处理成功
     * data : {"provinces":[{"province":"冀","details":[{"zimu":"A","price":"122"},{"zimu":"B","price":"33"},{"zimu":"C","price":"11"},{"zimu":"D","price":"444"},{"zimu":"E","price":"222"},{"zimu":"F","price":"10"},{"zimu":"G","price":"222"},{"zimu":"H","price":"555"},{"zimu":"J","price":"2220"},{"zimu":"R","price":"222"},{"zimu":"T","price":"333"},{"zimu":"S","price":"222"}]},{"province":"粤","details":[{"zimu":"A","price":"10"},{"zimu":"F","price":"7"},{"zimu":"B","price":"9"},{"zimu":"C","price":"9"},{"zimu":"D","price":"2"},{"zimu":"E","price":"8"},{"zimu":"J","price":"9"},{"zimu":"G","price":"9"},{"zimu":"K","price":"3"},{"zimu":"H","price":"9"},{"zimu":"L","price":"9"},{"zimu":"M","price":"9"},{"zimu":"N","price":"4"},{"zimu":"P","price":"9"},{"zimu":"Q","price":"9"},{"zimu":"R","price":"9"},{"zimu":"S","price":"5"},{"zimu":"T","price":"9"},{"zimu":"U","price":"9"},{"zimu":"V","price":"9"},{"zimu":"W","price":"6"},{"zimu":"X","price":"9"},{"zimu":"Y","price":"9"},{"zimu":"Z","price":"9"}]}],"address":"镇魂街罗刹大道32号"}
     * time : 2017-09-13 11:03:31
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
         * provinces : [{"province":"冀","details":[{"zimu":"A","price":"122"},{"zimu":"B","price":"33"},{"zimu":"C","price":"11"},{"zimu":"D","price":"444"},{"zimu":"E","price":"222"},{"zimu":"F","price":"10"},{"zimu":"G","price":"222"},{"zimu":"H","price":"555"},{"zimu":"J","price":"2220"},{"zimu":"R","price":"222"},{"zimu":"T","price":"333"},{"zimu":"S","price":"222"}]},{"province":"粤","details":[{"zimu":"A","price":"10"},{"zimu":"F","price":"7"},{"zimu":"B","price":"9"},{"zimu":"C","price":"9"},{"zimu":"D","price":"2"},{"zimu":"E","price":"8"},{"zimu":"J","price":"9"},{"zimu":"G","price":"9"},{"zimu":"K","price":"3"},{"zimu":"H","price":"9"},{"zimu":"L","price":"9"},{"zimu":"M","price":"9"},{"zimu":"N","price":"4"},{"zimu":"P","price":"9"},{"zimu":"Q","price":"9"},{"zimu":"R","price":"9"},{"zimu":"S","price":"5"},{"zimu":"T","price":"9"},{"zimu":"U","price":"9"},{"zimu":"V","price":"9"},{"zimu":"W","price":"6"},{"zimu":"X","price":"9"},{"zimu":"Y","price":"9"},{"zimu":"Z","price":"9"}]}]
         * address : 镇魂街罗刹大道32号
         */

        private String address;
        private List<ProvincesEntity> provinces;

        public void setAddress(String address) {
            this.address = address;
        }

        public void setProvinces(List<ProvincesEntity> provinces) {
            this.provinces = provinces;
        }

        public String getAddress() {
            return address;
        }

        public List<ProvincesEntity> getProvinces() {
            return provinces;
        }

        public static class ProvincesEntity {
            /**
             * province : 冀
             * details : [{"zimu":"A","price":"122"},{"zimu":"B","price":"33"},{"zimu":"C","price":"11"},{"zimu":"D","price":"444"},{"zimu":"E","price":"222"},{"zimu":"F","price":"10"},{"zimu":"G","price":"222"},{"zimu":"H","price":"555"},{"zimu":"J","price":"2220"},{"zimu":"R","price":"222"},{"zimu":"T","price":"333"},{"zimu":"S","price":"222"}]
             */

            private String province;
            private List<DetailsEntity> details;

            public void setProvince(String province) {
                this.province = province;
            }

            public void setDetails(List<DetailsEntity> details) {
                this.details = details;
            }

            public String getProvince() {
                return province;
            }

            public List<DetailsEntity> getDetails() {
                return details;
            }

            public static class DetailsEntity {
                /**
                 * zimu : A
                 * price : 122
                 */

                private String zimu;
                private String price;

                public void setZimu(String zimu) {
                    this.zimu = zimu;
                }

                public void setPrice(String price) {
                    this.price = price;
                }

                public String getZimu() {
                    return zimu;
                }

                public String getPrice() {
                    return price;
                }
            }
        }
    }

    /*
    * 提交年检订单返回的数据
    * */
    public static class ResultBean{

        /**
         * status : ok
         * code : 0
         * msg : 处理成功
         * data : {"response_code":1,"response_msg":"添加订单成功","ordercode":"20170913152606750409","server_address":"gdsgbsdgbsdgsdgsdgs","createtimestr":"2017-09-13 15:26:06","status":1}
         * time : 2017-09-13 15:26:06
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
             * response_code : 1
             * response_msg : 添加订单成功
             * ordercode : 20170913152606750409
             * server_address : gdsgbsdgbsdgsdgsdgs
             * createtimestr : 2017-09-13 15:26:06
             * status : 1
             */

            private int response_code;
            private String response_msg;
            private String ordercode;
            private String server_address;
            private String createtimestr;
            private int status;

            public void setResponse_code(int response_code) {
                this.response_code = response_code;
            }

            public void setResponse_msg(String response_msg) {
                this.response_msg = response_msg;
            }

            public void setOrdercode(String ordercode) {
                this.ordercode = ordercode;
            }

            public void setServer_address(String server_address) {
                this.server_address = server_address;
            }

            public void setCreatetimestr(String createtimestr) {
                this.createtimestr = createtimestr;
            }

            public void setStatus(int status) {
                this.status = status;
            }

            public int getResponse_code() {
                return response_code;
            }

            public String getResponse_msg() {
                return response_msg;
            }

            public String getOrdercode() {
                return ordercode;
            }

            public String getServer_address() {
                return server_address;
            }

            public String getCreatetimestr() {
                return createtimestr;
            }

            public int getStatus() {
                return status;
            }
        }
    }
}
