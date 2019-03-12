package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SBountyRecord;
import com.supermoney.loan.api.entity.SBountySignIn;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;

public interface SBountySignInMapper extends Mapper<SBountySignIn> {

    List<SBountySignIn> find(SBountySignIn sBountySignIn);
}