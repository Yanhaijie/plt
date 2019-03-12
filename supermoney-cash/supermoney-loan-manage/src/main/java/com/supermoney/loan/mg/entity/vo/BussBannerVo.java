package com.supermoney.loan.mg.entity.vo;

import java.io.Serializable;

/**
 * Created by admin on 2018-03-01.
 */
public class BussBannerVo implements Serializable {

    private Integer id;

    private  String refId;

    private  String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getRefId() {
        return refId;
    }

    public void setRefId(String refId) {
        this.refId = refId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
