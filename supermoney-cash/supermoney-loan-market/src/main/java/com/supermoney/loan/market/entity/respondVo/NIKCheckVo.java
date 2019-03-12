package com.supermoney.loan.market.entity.respondVo;

import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.market.entity.SNikInfo;
import com.supermoney.loan.market.utils.NomalUntil;

public class NIKCheckVo {

    /**
     * NIK
     */
    private String nik;

    /**
     * 地址
     */
    private String address;

    /**
     * 出生日期
     */
    private String bod;

    /**
     * 名字
     */
    private String name;


    public NIKCheckVo(){

    }

    public static NIKCheckVo createVo(SNikInfo nikInfo){
        NIKCheckVo nikCheckVo = new NIKCheckVo();
        nikCheckVo.setNik(nikInfo.getNik());
        nikCheckVo.setName(NomalUntil.SensitiveStr(nikInfo.getName()));
        nikCheckVo.setAddress(NomalUntil.SensitiveStr(nikInfo.getAddress()));
        nikCheckVo.setBod(nikInfo.getBod());
        return nikCheckVo;
    }

//    public static NIKCheckVo createVo(JSONObject jsonObject){
//        NIKCheckVo nikCheckVo = new NIKCheckVo();
//        nikCheckVo.setNik(jsonObject.get("nik").toString());
//        nikCheckVo.setName(NomalUntil.SensitiveStr(jsonObject.get("name").toString()));
//        nikCheckVo.setAddress(NomalUntil.SensitiveStr(jsonObject.get("address").toString()));
//        nikCheckVo.setBod(jsonObject.get("bod").toString());
//        return nikCheckVo;
//    }

    public String getNik() {
        return nik;
    }

    public void setNik(String nik) {
        this.nik = nik;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getBod() {
        return bod;
    }

    public void setBod(String bod) {
        this.bod = bod;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
