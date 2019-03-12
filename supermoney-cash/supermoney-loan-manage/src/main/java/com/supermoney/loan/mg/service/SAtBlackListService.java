package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SAtBlackList;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/06/01.
 */
public interface SAtBlackListService extends Service<SAtBlackList> {

    List<SAtBlackList> selectBlackListByParam(Map<String, Object> param);

    public int blackListCheck2(Integer userId, String name, String idNumber, String phoneNumber);

}
