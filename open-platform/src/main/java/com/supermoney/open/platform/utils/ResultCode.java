package com.supermoney.open.platform.utils;

/**
 * Created by admin on 2017-11-21.
 */
public enum ResultCode {
    SUCCESS(200,"成功"),
    FAIL(400,"失败"),
    UNAUTHORIZED(401,"未认证（签名错误）"),
    NOT_FOUND(404,"接口不存在"),
    INTERNAL_SERVER_ERROR(500,"服务器内部错误"),

    PARAM_EMPTY(11000,"缺少参数"),
    FILE_ERROR(11001,"无效链接"),
    FILE_ERROR_NOT_FACE(11002,"图片模糊或者非图片格式，无法识别人脸"),
    FILE_ERROR_EMPTY(11006,"未上传图片"),
    FILE_ERROR_UPLOAD(11007,"图片上传失败"),
    PARAM_EMPTY_NAME(11003,"用户姓名不能为空"),
    PARAM_EMPTY_ID(11004,"ID号不能为空"),
    PARAM_EMPTY_PHONE(11005,"手机号不能为空"),
    PARAM_EMPTY_NIK(11006,"NIK不能为空"),
    ;

    public int code;

    public String msg;

    ResultCode(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
