package com.supers.p2p.assets.entity.vo;

import com.supers.p2p.assets.entity.SUser;

public class SUserVo extends SUser {

    private  String CompanyName;

    private  Integer roleId;

    public Integer getRoleId() {
        return roleId;
    }

    public void setRoleId(Integer roleId) {
        this.roleId = roleId;
    }

    public String getCompanyName() {
        return CompanyName;
    }

    public void setCompanyName(String companyName) {
        CompanyName = companyName;
    }
}
