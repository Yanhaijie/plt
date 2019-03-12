package com.wi.data.clear.utils;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(value = "result", description = "返回数据")
public class ResultT<T>   {

    @ApiModelProperty(value = "操作编号")
    private int code;
    @ApiModelProperty(value = "消息说明")
    private String message;
    @ApiModelProperty(value = "返回数据")
    private T data;

   public ResultT(){

    }

   public ResultT(Result result){
        this.code=result.getCode();
        this.message=result.getMessage();
        if(result.getData()!=null){
            this.data=(T)result.getData();
        }
    }

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

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
