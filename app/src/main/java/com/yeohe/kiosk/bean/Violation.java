package com.yeohe.kiosk.bean;

import java.util.List;

/**
 * Created by Administrator on 2017/9/14.
 */

public class Violation {

    /**
     * status : ok
     * code : 0
     * msg : 处理成功
     * data : {"message":{"style":1,"content":"注意：您的车牌所在地不接受挑单处理，所以违章必须全部提交哦~"},"alert":"<p>一、订单说明：<\/p>\n<p style=\"text-align:left;\">\n\t1.付款成功后请勿在<span style=\"background-color:#FF9900;\"><span style=\"background-color:#FFFFFF;\">其他渠道<\/span><span style=\"background-color:#FFFFFF;color:#E53333;\">重复处理<\/span><\/span>，产生纠纷不作退款。\n<\/p>\n<p style=\"text-align:left;\">\n\t2.<span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\">一辆车一个年度内超过<\/span><span style=\"color:#E53333;\">三本<\/span><span style=\"color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;\"><span style=\"color:#E53333;\">驾驶证<\/span>来处理违章，即超证。<\/span> \n<\/p>\n<div style=\"text-align:left;\">\n\t<span>一、<\/span>以下情况可能会产生订单金额外的<span style=\"color:#E53333;\">补款费用<\/span>，届时客服会联系您商议补款相关事宜，敬请留意：\n<\/div>\n<p style=\"text-align:left;\">\n\t1.车辆发生超证或0分0罚款违章.\n<\/p>\n<p style=\"text-align:left;\">\n\t2.车辆在机场路段违章.\n<\/p>\n<p style=\"text-align:left;\">\n\t3.公司车0分订单.\n<\/p>\n<p style=\"text-align:left;\">\n\t4.粤X Y E字母开头车牌外省违章.\n<\/p>\n<p style=\"text-align:left;\">\n\t5.提交的违章与实际处理违章罚款金额不一致.\n<\/p>\n<p>\n\t<br />\n<\/p>\n","peccancyList":[{"id":"29929","carid":"505","searchid":"7608","time":"2017-08-02 19:52:25","location":"解放大道华新立交桥环形交叉路口至立交桥东","reason":"机动车违反禁令标志指示的","count":"200","status":"0","department":"湖南省衡阳市公安局交通警察支队公路巡逻大","degree":"3","code":"1344","archive":"4304030001333762","telephone":"","excutelocation":"","excutedepartment":"","category":"","latefine":"0","punishmentaccording":"","illegalentry":"","locationid":"4304","locationname":"湖南衡阳","datasourceid":"9992","recordtype":"实时数据","poundage":"0","cooperpoundge":"","activepoundge":"-1","canprocess":"0","canusepackage":"","secondaryuniquecode":"1852452929","uniquecode":"b64fad00f69fb51e3c5c804d3829f29f","degreepoundage":"0","canprocessmsg":"","other":"3","api_type":"1","znj":"0.00","fwf":"0.00","fwf2":"0.00","isShuaka":"0","remark":"","canOrder":"0","memo":"","uniqueNum":"","downCostPrice":"","iscommit":1,"orderstatus":"未处理","latefee":0,"price":540,"pickone":1,"quotedprice":"1"}]}
     */

    private String status;
    private int code;
    private String msg;
    private DataEntity data;

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

    public static class DataEntity {
        /**
         * message : {"style":1,"content":"注意：您的车牌所在地不接受挑单处理，所以违章必须全部提交哦~"}
         * alert : <p>一、订单说明：</p>
         <p style="text-align:left;">
         1.付款成功后请勿在<span style="background-color:#FF9900;"><span style="background-color:#FFFFFF;">其他渠道</span><span style="background-color:#FFFFFF;color:#E53333;">重复处理</span></span>，产生纠纷不作退款。
         </p>
         <p style="text-align:left;">
         2.<span style="color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;">一辆车一个年度内超过</span><span style="color:#E53333;">三本</span><span style="color:#333333;font-family:&quot;font-size:14px;background-color:#FFFFFF;"><span style="color:#E53333;">驾驶证</span>来处理违章，即超证。</span>
         </p>
         <div style="text-align:left;">
         <span>一、</span>以下情况可能会产生订单金额外的<span style="color:#E53333;">补款费用</span>，届时客服会联系您商议补款相关事宜，敬请留意：
         </div>
         <p style="text-align:left;">
         1.车辆发生超证或0分0罚款违章.
         </p>
         <p style="text-align:left;">
         2.车辆在机场路段违章.
         </p>
         <p style="text-align:left;">
         3.公司车0分订单.
         </p>
         <p style="text-align:left;">
         4.粤X Y E字母开头车牌外省违章.
         </p>
         <p style="text-align:left;">
         5.提交的违章与实际处理违章罚款金额不一致.
         </p>
         <p>
         <br />
         </p>

         * peccancyList : [{"id":"29929","carid":"505","searchid":"7608","time":"2017-08-02 19:52:25","location":"解放大道华新立交桥环形交叉路口至立交桥东","reason":"机动车违反禁令标志指示的","count":"200","status":"0","department":"湖南省衡阳市公安局交通警察支队公路巡逻大","degree":"3","code":"1344","archive":"4304030001333762","telephone":"","excutelocation":"","excutedepartment":"","category":"","latefine":"0","punishmentaccording":"","illegalentry":"","locationid":"4304","locationname":"湖南衡阳","datasourceid":"9992","recordtype":"实时数据","poundage":"0","cooperpoundge":"","activepoundge":"-1","canprocess":"0","canusepackage":"","secondaryuniquecode":"1852452929","uniquecode":"b64fad00f69fb51e3c5c804d3829f29f","degreepoundage":"0","canprocessmsg":"","other":"3","api_type":"1","znj":"0.00","fwf":"0.00","fwf2":"0.00","isShuaka":"0","remark":"","canOrder":"0","memo":"","uniqueNum":"","downCostPrice":"","iscommit":1,"orderstatus":"未处理","latefee":0,"price":540,"pickone":1,"quotedprice":"1"}]
         */

        private MessageEntity message;
        private String alert;
        private List<PeccancyListEntity> peccancyList;

        public void setMessage(MessageEntity message) {
            this.message = message;
        }

        public void setAlert(String alert) {
            this.alert = alert;
        }

        public void setPeccancyList(List<PeccancyListEntity> peccancyList) {
            this.peccancyList = peccancyList;
        }

        public MessageEntity getMessage() {
            return message;
        }

        public String getAlert() {
            return alert;
        }

        public List<PeccancyListEntity> getPeccancyList() {
            return peccancyList;
        }

        public static class MessageEntity {
            /**
             * style : 1
             * content : 注意：您的车牌所在地不接受挑单处理，所以违章必须全部提交哦~
             */

            private int style;
            private String content;

            public void setStyle(int style) {
                this.style = style;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public int getStyle() {
                return style;
            }

            public String getContent() {
                return content;
            }
        }

        public static class PeccancyListEntity {
            /**
             * id : 29929
             * carid : 505
             * searchid : 7608
             * time : 2017-08-02 19:52:25
             * location : 解放大道华新立交桥环形交叉路口至立交桥东
             * reason : 机动车违反禁令标志指示的
             * count : 200
             * status : 0
             * department : 湖南省衡阳市公安局交通警察支队公路巡逻大
             * degree : 3
             * code : 1344
             * archive : 4304030001333762
             * telephone :
             * excutelocation :
             * excutedepartment :
             * category :
             * latefine : 0
             * punishmentaccording :
             * illegalentry :
             * locationid : 4304
             * locationname : 湖南衡阳
             * datasourceid : 9992
             * recordtype : 实时数据
             * poundage : 0
             * cooperpoundge :
             * activepoundge : -1
             * canprocess : 0
             * canusepackage :
             * secondaryuniquecode : 1852452929
             * uniquecode : b64fad00f69fb51e3c5c804d3829f29f
             * degreepoundage : 0
             * canprocessmsg :
             * other : 3
             * api_type : 1
             * znj : 0.00
             * fwf : 0.00
             * fwf2 : 0.00
             * isShuaka : 0
             * remark :
             * canOrder : 0
             * memo :
             * uniqueNum :
             * downCostPrice :
             * iscommit : 1
             * orderstatus : 未处理
             * latefee : 0
             * price : 540
             * pickone : 1
             * quotedprice : 1
             */

            private String id;
            private String carid;
            private String searchid;
            private String time;
            private String location;
            private String reason;
            private String count;
            private String status;
            private String department;
            private String degree;
            private String code;
            private String archive;
            private String telephone;
            private String excutelocation;
            private String excutedepartment;
            private String category;
            private String latefine;
            private String punishmentaccording;
            private String illegalentry;
            private String locationid;
            private String locationname;
            private String datasourceid;
            private String recordtype;
            private String poundage;
            private String cooperpoundge;
            private String activepoundge;
            private String canprocess;
            private String canusepackage;
            private String secondaryuniquecode;
            private String uniquecode;
            private String degreepoundage;
            private String canprocessmsg;
            private String other;
            private String api_type;
            private String znj;
            private String fwf;
            private String fwf2;
            private String isShuaka;
            private String remark;
            private String canOrder;
            private String memo;
            private String uniqueNum;
            private String downCostPrice;
            private int iscommit;
            private String orderstatus;
            private int latefee;
            private int price;
            private int pickone;
            private String quotedprice;


            public void setId(String id) {
                this.id = id;
            }

            public void setCarid(String carid) {
                this.carid = carid;
            }

            public void setSearchid(String searchid) {
                this.searchid = searchid;
            }

            public void setTime(String time) {
                this.time = time;
            }

            public void setLocation(String location) {
                this.location = location;
            }

            public void setReason(String reason) {
                this.reason = reason;
            }

            public void setCount(String count) {
                this.count = count;
            }

            public void setStatus(String status) {
                this.status = status;
            }

            public void setDepartment(String department) {
                this.department = department;
            }

            public void setDegree(String degree) {
                this.degree = degree;
            }

            public void setCode(String code) {
                this.code = code;
            }

            public void setArchive(String archive) {
                this.archive = archive;
            }

            public void setTelephone(String telephone) {
                this.telephone = telephone;
            }

            public void setExcutelocation(String excutelocation) {
                this.excutelocation = excutelocation;
            }

            public void setExcutedepartment(String excutedepartment) {
                this.excutedepartment = excutedepartment;
            }

            public void setCategory(String category) {
                this.category = category;
            }

            public void setLatefine(String latefine) {
                this.latefine = latefine;
            }

            public void setPunishmentaccording(String punishmentaccording) {
                this.punishmentaccording = punishmentaccording;
            }

            public void setIllegalentry(String illegalentry) {
                this.illegalentry = illegalentry;
            }

            public void setLocationid(String locationid) {
                this.locationid = locationid;
            }

            public void setLocationname(String locationname) {
                this.locationname = locationname;
            }

            public void setDatasourceid(String datasourceid) {
                this.datasourceid = datasourceid;
            }

            public void setRecordtype(String recordtype) {
                this.recordtype = recordtype;
            }

            public void setPoundage(String poundage) {
                this.poundage = poundage;
            }

            public void setCooperpoundge(String cooperpoundge) {
                this.cooperpoundge = cooperpoundge;
            }

            public void setActivepoundge(String activepoundge) {
                this.activepoundge = activepoundge;
            }

            public void setCanprocess(String canprocess) {
                this.canprocess = canprocess;
            }

            public void setCanusepackage(String canusepackage) {
                this.canusepackage = canusepackage;
            }

            public void setSecondaryuniquecode(String secondaryuniquecode) {
                this.secondaryuniquecode = secondaryuniquecode;
            }

            public void setUniquecode(String uniquecode) {
                this.uniquecode = uniquecode;
            }

            public void setDegreepoundage(String degreepoundage) {
                this.degreepoundage = degreepoundage;
            }

            public void setCanprocessmsg(String canprocessmsg) {
                this.canprocessmsg = canprocessmsg;
            }

            public void setOther(String other) {
                this.other = other;
            }

            public void setApi_type(String api_type) {
                this.api_type = api_type;
            }

            public void setZnj(String znj) {
                this.znj = znj;
            }

            public void setFwf(String fwf) {
                this.fwf = fwf;
            }

            public void setFwf2(String fwf2) {
                this.fwf2 = fwf2;
            }

            public void setIsShuaka(String isShuaka) {
                this.isShuaka = isShuaka;
            }

            public void setRemark(String remark) {
                this.remark = remark;
            }

            public void setCanOrder(String canOrder) {
                this.canOrder = canOrder;
            }

            public void setMemo(String memo) {
                this.memo = memo;
            }

            public void setUniqueNum(String uniqueNum) {
                this.uniqueNum = uniqueNum;
            }

            public void setDownCostPrice(String downCostPrice) {
                this.downCostPrice = downCostPrice;
            }

            public void setIscommit(int iscommit) {
                this.iscommit = iscommit;
            }

            public void setOrderstatus(String orderstatus) {
                this.orderstatus = orderstatus;
            }

            public void setLatefee(int latefee) {
                this.latefee = latefee;
            }

            public void setPrice(int price) {
                this.price = price;
            }

            public void setPickone(int pickone) {
                this.pickone = pickone;
            }

            public void setQuotedprice(String quotedprice) {
                this.quotedprice = quotedprice;
            }

            public String getId() {
                return id;
            }

            public String getCarid() {
                return carid;
            }

            public String getSearchid() {
                return searchid;
            }

            public String getTime() {
                return time;
            }

            public String getLocation() {
                return location;
            }

            public String getReason() {
                return reason;
            }

            public String getCount() {
                return count;
            }

            public String getStatus() {
                return status;
            }

            public String getDepartment() {
                return department;
            }

            public String getDegree() {
                return degree;
            }

            public String getCode() {
                return code;
            }

            public String getArchive() {
                return archive;
            }

            public String getTelephone() {
                return telephone;
            }

            public String getExcutelocation() {
                return excutelocation;
            }

            public String getExcutedepartment() {
                return excutedepartment;
            }

            public String getCategory() {
                return category;
            }

            public String getLatefine() {
                return latefine;
            }

            public String getPunishmentaccording() {
                return punishmentaccording;
            }

            public String getIllegalentry() {
                return illegalentry;
            }

            public String getLocationid() {
                return locationid;
            }

            public String getLocationname() {
                return locationname;
            }

            public String getDatasourceid() {
                return datasourceid;
            }

            public String getRecordtype() {
                return recordtype;
            }

            public String getPoundage() {
                return poundage;
            }

            public String getCooperpoundge() {
                return cooperpoundge;
            }

            public String getActivepoundge() {
                return activepoundge;
            }

            public String getCanprocess() {
                return canprocess;
            }

            public String getCanusepackage() {
                return canusepackage;
            }

            public String getSecondaryuniquecode() {
                return secondaryuniquecode;
            }

            public String getUniquecode() {
                return uniquecode;
            }

            public String getDegreepoundage() {
                return degreepoundage;
            }

            public String getCanprocessmsg() {
                return canprocessmsg;
            }

            public String getOther() {
                return other;
            }

            public String getApi_type() {
                return api_type;
            }

            public String getZnj() {
                return znj;
            }

            public String getFwf() {
                return fwf;
            }

            public String getFwf2() {
                return fwf2;
            }

            public String getIsShuaka() {
                return isShuaka;
            }

            public String getRemark() {
                return remark;
            }

            public String getCanOrder() {
                return canOrder;
            }

            public String getMemo() {
                return memo;
            }

            public String getUniqueNum() {
                return uniqueNum;
            }

            public String getDownCostPrice() {
                return downCostPrice;
            }

            public int getIscommit() {
                return iscommit;
            }

            public String getOrderstatus() {
                return orderstatus;
            }

            public int getLatefee() {
                return latefee;
            }

            public int getPrice() {
                return price;
            }

            public int getPickone() {
                return pickone;
            }

            public String getQuotedprice() {
                return quotedprice;
            }
        }
    }
}