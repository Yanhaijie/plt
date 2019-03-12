package com.supermoney.loan.mg.service;

import com.supermoney.loan.mg.entity.SReportUtil;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/13.
 */
public interface SReportUtilService extends Service<SReportUtil> {

    public List<Map<String,Object>> executeSelect(Map<String,Object> map);
}
