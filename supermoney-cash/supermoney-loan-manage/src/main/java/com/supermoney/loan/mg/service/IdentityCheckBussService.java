package com.supermoney.loan.mg.service;


import com.supermoney.loan.mg.entity.SAtCreditInformation;
import com.supermoney.loan.mg.entity.SAtIdentity;

import java.util.List;

/**
 * Created by bear on 2018/1/14.
 */
public interface IdentityCheckBussService {

    public void addObject(List<SAtIdentity> obj);

    public void addObject(SAtIdentity obj);

    public void doCheck();

    public void doBlackListCheck();
}
