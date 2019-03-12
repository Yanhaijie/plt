package com.supermoney.open.platform.entity.vo.respond;


import com.supermoney.open.platform.entity.SNikInfo;

public class NikInfoRespondVo {

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

    /**
     * 三方创建时间
     */
    private String createdAt;

    /**
     * 三方最后修改时间
     */
    private String updatedAt;

    public NikInfoRespondVo(SNikInfo sNikInfo){
        this.setNik(sNikInfo.getNik());
        this.setBod(sNikInfo.getBod());
        this.setName(sNikInfo.getName());
        this.setAddress(sNikInfo.getAddress());
        this.setCreatedAt(sNikInfo.getCreatedAt());
        this.setUpdatedAt(sNikInfo.getUpdatedAt());
    }

    /**
    /**
     * 获取NIK
     *
     * @return nik - NIK
     */
    public String getNik() {
        return nik;
    }

    /**
     * 设置NIK
     *
     * @param nik NIK
     */
    public void setNik(String nik) {
        this.nik = nik;
    }

    /**
     * 获取地址
     *
     * @return address - 地址
     */
    public String getAddress() {
        return address;
    }

    /**
     * 设置地址
     *
     * @param address 地址
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * 获取出生日期
     *
     * @return bod - 出生日期
     */
    public String getBod() {
        return bod;
    }

    /**
     * 设置出生日期
     *
     * @param bod 出生日期
     */
    public void setBod(String bod) {
        this.bod = bod;
    }

    /**
     * 获取名字
     *
     * @return name - 名字
     */
    public String getName() {
        return name;
    }

    /**
     * 设置名字
     *
     * @param name 名字
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取三方创建时间
     *
     * @return created_at - 三方创建时间
     */
    public String getCreatedAt() {
        return createdAt;
    }

    /**
     * 设置三方创建时间
     *
     * @param createdAt 三方创建时间
     */
    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    /**
     * 获取三方最后修改时间
     *
     * @return updated_at - 三方最后修改时间
     */
    public String getUpdatedAt() {
        return updatedAt;
    }

    /**
     * 设置三方最后修改时间
     *
     * @param updatedAt 三方最后修改时间
     */
    public void setUpdatedAt(String updatedAt) {
        this.updatedAt = updatedAt;
    }
}