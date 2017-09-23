package com.yeohe.kiosk.utils;

import android.app.Activity;
import android.content.Intent;
import android.widget.Toast;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by Administrator on 2016/9/21 0021.
 * 错误信息显示工具类
 */
public class Misidentification {
    private static StatusSQLUtils statusSQLUtils;//保存或得到登录状态
    public static final void misidentification1(Activity activity, String status, JSONObject jsonObject) {
        TokenSQLUtils tokenSQLUtils = new TokenSQLUtils(activity);
        if (TokenSQLUtils.check() != null) {//提取Token值判断是否登录过
            if (status.equals("fail")) {
                String code = "";
                try {
                    code = jsonObject.getString("code");
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                if (code.equals("10001")) {
                    statusSQLUtils = new StatusSQLUtils(activity);
                    statusSQLUtils.undateApi("no");

                    tokenSQLUtils.delete();//清除Token

                } else if (code.equals("10002")) {
                    ToastUtil.getShortToastByString(activity, "未知错误");
                } else if (code.equals("10003")) {
                    ToastUtil.getShortToastByString(activity, "输入格式错误");
                } else if (code.equals("10004")) {
                    ToastUtil.getShortToastByString(activity, "内容不能为空");
                } else if (code.equals("10005")) {
                    ToastUtil.getShortToastByString(activity, "调用类型必须是:POST");
                } else if (code.equals("10006")) {
                    ToastUtil.getShortToastByString(activity, "无法识别用户");
                } else if (code.equals("10007")) {
                    ToastUtil.getShortToastByString(activity, "手机格式错误");
                } else if (code.equals("10008")) {
                    ToastUtil.getShortToastByString(activity, "发送验证码失败");
                } else if (code.equals("10009")) {
                    ToastUtil.getShortToastByString(activity, "发送验证码频率过高");
                } else if (code.equals("20001")) {
                    ToastUtil.getShortToastByString(activity, "账号或密码错误");
                } else if (code.equals("20002")) {
                    ToastUtil.getShortToastByString(activity, "重复密码错误");
                } else if (code.equals("20003")) {
                    ToastUtil.getShortToastByString(activity, "验证码错误");
                } else if (code.equals("20004")) {
                    ToastUtil.getShortToastByString(activity, "手机号码已经注册");
                } else if (code.equals("20005")) {
                    ToastUtil.getShortToastByString(activity, "违章机已激活过");
                } else if (code.equals("20006")) {
                    ToastUtil.getShortToastByString(activity, "违章机不存在");
                } else if (code.equals("20007")) {
                    ToastUtil.getShortToastByString(activity, "验证码过期");
                } else if (code.equals("20008")) {
                    ToastUtil.getShortToastByString(activity, "不能和原密码相同");
                } else if (code.equals("20009")) {
                    ToastUtil.getShortToastByString(activity, "密码错误");
                } else if (code.equals("20010")) {
                    ToastUtil.getShortToastByString(activity, "手机号码不存在");
                } else if (code.equals("20012")) {
                    ToastUtil.getLongToastByString(activity, "修改头像失败");
                } else if (code.equals("20013")) {
                    ToastUtil.getLongToastByString(activity, "修改昵称失败");
                } else if (code.equals("20014")) {
                    ToastUtil.getLongToastByString(activity, "提交意见失败");
                } else if (code.equals("20015")) {
                    ToastUtil.getLongToastByString(activity, "服务器图片储存失败");
                } else if (code.equals("20011")) {
                    ToastUtil.getShortToastByString(activity, "修改手机号失败");
                }else if (code.equals("20019")) {
                    ToastUtil.getShortToastByString(activity, "密码必须是字母与数字组成，并且不能少于6位数");
                }
                else if (code.equals("120")) {
                    ToastUtil.getShortToastByString(activity, "系统正在维护");
                } else if (code.equals("2017")) {
                    ToastUtil.getShortToastByString(activity, "邮箱格式错误");
                } else if (code.equals("3001")) {
                    ToastUtil.getShortToastByString(activity, "找不用户信息");
                } else if (code.equals("3002")) {
                    ToastUtil.getShortToastByString(activity, "找不车辆信息");
                } else if (code.equals("30003")) {
                    ToastUtil.getShortToastByString(activity, "查询失败");
                } else if (code.equals("30004")) {
                    ToastUtil.getShortToastByString(activity, "查询失败");
                } else if (code.equals("30005")) {
                    ToastUtil.getShortToastByString(activity, "查询失败");
                } else if (code.equals("30006")) {
                    ToastUtil.getShortToastByString(activity, "查询失败");
                } else if (code.equals("30007")) {
                    ToastUtil.getShortToastByString(activity, "违章机找不到或未激活");
                } else if (code.equals("40001")) {
                    ToastUtil.getShortToastByString(activity, "找不用户信息");
                } else if (code.equals("40002")) {
                    ToastUtil.getShortToastByString(activity, "总金额和计算价格不一致");
                } else if (code.equals("40003")) {
                    ToastUtil.getShortToastByString(activity, "保存订单失败");
                } else if (code.equals("40004")) {
                    ToastUtil.getShortToastByString(activity, "订单删除失败");
                } else if (code.equals("40005")) {
                    ToastUtil.getShortToastByString(activity, "未识别的订单类型");
                } else if (code.equals("40006")) {
                    ToastUtil.getShortToastByString(activity, "至少有一条或多条违章被重复提交");
                }else if (code.equals("40007")) {
                    ToastUtil.getShortToastByString(activity, "价格异常（金额小于1）");
                }else if (code.equals("40008")) {
                    ToastUtil.getShortToastByString(activity, "支付金额与订单金额不一致");
                }else if (code.equals("40009")) {
                    ToastUtil.getShortToastByString(activity, "订单还未全部报价");
                } else if (code.equals("20016")) {
                    ToastUtil.getShortToastByString(activity, "提交加盟意向失败");
                } else if (code.equals("20017")) {
                    ToastUtil.getShortToastByString(activity, "邮箱格式错误");
                } else if (code.equals("20018")) {
                    ToastUtil.getShortToastByString(activity, "身份证格式错误");
                }else if(code.equals("60005")){
                    ToastUtil.getShortToastByString(activity, "提交金额与订单金额不一致");
                }
                else if(code.equals("110")){//后天强制更新
                }else if(code.equals("130")){
                    ToastUtil.getShortToastByString(activity, "系统出现错误");
                }

            }
        }

    }
}
