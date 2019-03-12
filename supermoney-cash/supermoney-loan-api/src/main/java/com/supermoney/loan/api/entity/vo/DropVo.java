package com.supermoney.loan.api.entity.vo;

import java.io.Serializable;

/**
 * Created by admin on 2017-12-27.
 */
public class DropVo implements Serializable {
    private  String value;
    private  String text;

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
