package com.supermoney.loan.market.utils;


import java.io.Serializable;

/**
 * api 返回结果
 * Created by XiongHuiFeng on 2017-09-04.
 */
public class Result implements Serializable {

    private int code;
    private String message;
    private Object data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}