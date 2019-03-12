package com.supermoney.loan.api.service;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.api.entity.SBountySignIn;
import com.supermoney.loan.api.utils.Service;
import com.supermoney.loan.api.utils.UserUtils;

import java.util.List;


/**
 * Created by @author on 2018/10/31.
 */
public interface SBountySignInService extends Service<SBountySignIn> {

    List<SBountySignIn> find(SBountySignIn sBountySignIn);

    public JSONObject getUserInfoAndSign(String userAccessToken,Integer bountyId,String appSecret);

    public JSONObject getAccessToken(String code);

}
