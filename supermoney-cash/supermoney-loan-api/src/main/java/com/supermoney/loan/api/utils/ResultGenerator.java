package com.supermoney.loan.api.utils;

/**
 * Created by admin on 2017-11-21.
 */
public class ResultGenerator {
    private static final String DEFAULT_SUCCESS_MESSAGE = "SUCCESS";

    public static Result genSuccessResult() {
        Result result=new Result();
        result.setData("");
        result.setCode(ResultCode.SUCCESS.code);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        return result;
    }

    public static Result genSuccessResult(Object data) {
        Result result=new Result();
        result.setCode(ResultCode.SUCCESS.code);
        result.setMessage(DEFAULT_SUCCESS_MESSAGE);
        result.setData(data==null?"":data);
        return result;
    }

    public static Result genFailResult(String message) {
        Result result=new Result();
        result.setCode(ResultCode.FAIL.code);
        result.setData("");
        result.setMessage(message);
        return result;
    }

}
