package com.supermoney.loan.market.utils;

/**
 * Created by admin on 2017-11-21.
 */
public enum ResultCode {
    SUCCESS(200,"成功"),
    FAIL(400,"失败"),
    UNAUTHORIZED(401,"未认证（签名错误）"),
    NOT_FOUND(404,"接口不存在"),
    INTERNAL_SERVER_ERROR(500,"服务器内部错误"),

    ORDER_ID_ERROR(10002,"查询不到该订单"),
    ORDER_ID_EMPTY(10003,"订单id为空"),
    ORDER_STATE_ERROR(10004,"订单状态错误"),


    NIK_PARAM_ERROR(10005,"Parameter should not be empty"),
    NIK_ERROR(10006,"The person can not be found in our system"),
    ;


    public int code;
    public String msg;

    ResultCode(int code,String msg) {
        this.code = code;
        this.msg = msg;
    }
}
