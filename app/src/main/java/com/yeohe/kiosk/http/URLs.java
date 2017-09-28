package com.yeohe.kiosk.http;

/**
 * Created by Administrator on 2017/9/11.
 */

public class URLs {

    public final static String HOST="http://qr.yeohe.com/testfeigoucar/index.php/API2";//测试接口
//    public final static String HOST="http://che.yeohe.com/youhe/index.php/API2";//正式接口


    public final static String GET_OPEN_PROVINCE=HOST+"/Peccancy/getOpenProvince.html";//获取快速查询开放省份
    public final static String GET_CAR_TYPE_LIST=HOST+"/Car/getCarTypeList.html";//获取车辆类型列表
    public final static String ADD_CAR =HOST+ "/Car/add.html";//添加车辆
    public final static String GET_QUERY_CONDITIONS=HOST+"/Peccancy/getQueryCondition.html";//获取车辆违章查询条件

    public final static String ANNUAL_ADD_ORDER=HOST+"/AnnualInspectiont/add_order.html";//提交年检订单
    public final static String GET_SUPPORTED_PROVINCES=HOST+"/AnnualInspectiont/get_supported_provinces.html";//// 获取年检支持的省份城市及价格及证件邮寄地址

    public final static String CLIENT_QUERY_POST=HOST+"/Peccancy/clientQueryPost.html";//车辆违章查询

    public final static String VIOLATION_QUERY = HOST+"/Peccancy/carQueryCheck.html";//添加车辆的违章查询校验

    //订单查询1地址
    public final static String ORDER_QUERY = HOST+"/Order/getOrderList ";

    //订单详情删除地址
    public final static String DELETEORDE = HOST+"/Order/del";
    //订单详情
    public final static String GET_ORDER_DETAILS = HOST+"/Order/getOrderDetails.html";


    public final static String ADD_ANNUAL_ORDER=HOST+"/AnnualInspectiont/add_order.html";//添加年检订单

    public final static String ANNUAL_ORDER_LIST=HOST+"/AnnualInspectiont/order_list.html";//年检订单列表

    public final static String ANNUAL_ORDER_DETAIL=HOST+"/AnnualInspectiont/order_details.html";//年检订单详情

    public final static String GET_EXPRESS_INFO=HOST+"/AnnualInspectiont/get_express_info.html";//查询物流信息

    public final static String ADD_EXPRESSE_NUMBER=HOST+"/AnnualInspectiont/add_send_tracking_number.html";//添加年检快递单号

    public final static String ANNUAL_DEL=HOST+"/AnnualInspectiont/del.html";//删除年检订单


    public final static String COMMIT_ORDER = HOST+"/Order/add.html";//提交普通订单
    public final static String COMMIT_QUOTED_PRICE_ORDER=HOST+"/Order/addSpecialOrder.html";//提交待报价订单

    public final static String GET_PAY_TYPE=HOST+"/Pay/getPayType.html";//获取支付通道信息
    public final static String ORDER_CHECK=HOST+"/Order/orderCheck.html";//违章订单校验
    public final static String ANNUAL_ORDER_CHECK=HOST+"/AnnualInspectiont/orderCheck.html";//检测年检订单
    public final static String GET_QR_CODE_URL=HOST+"/MJPay/getQRcodeUrl.html";//订单支付(茂捷线上二维码通道)
    public final static String GET_PAY_STATUS=HOST+"/MJPay/getPayStatus.html";//获取订单支付结果(茂捷线上二维码通道)

    public final static String LOGIN_URL=HOST+"";//登录接口
    public final static String GET_AUTH_CODE=HOST+"";//获取手机验证码



    public final static String HTTP = "http://";
    public final static String HTTPS = "https://";

}
