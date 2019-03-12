package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SActivityWhiteList;
import com.supermoney.loan.api.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/22.
 */
public interface SActivityWhiteListService extends Service<SActivityWhiteList> {

    public List<SActivityWhiteList> selectWhiteListByParam(Map<String, Object> param);

}
