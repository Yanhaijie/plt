package com.supermoney.loan.mg.entity.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.std.ToStringSerializer;
import com.supermoney.loan.mg.entity.SMessage;

import javax.persistence.Column;
import java.io.Serializable;
import java.util.Date;

/**
 * Created by wenyuhao on 2018-04-09.
 */
public class SMessageVo extends SMessage implements Serializable {


    /**
     * 消息类别名称
     */
    private String messageTypeName;

    public String getMessageTypeName() {
        return messageTypeName;
    }

    public void setMessageTypeName(String messageTypeName) {
        this.messageTypeName = messageTypeName;
    }

}
