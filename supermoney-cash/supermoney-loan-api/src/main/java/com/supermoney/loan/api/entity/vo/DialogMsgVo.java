package com.supermoney.loan.api.entity.vo;


import java.io.Serializable;

public class DialogMsgVo  implements Serializable{

    private  String title;

    private  String content;

    private  Integer msgType;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getMsgType() {
        return msgType;
    }

    public void setMsgType(Integer msgType) {
        this.msgType = msgType;
    }
}
