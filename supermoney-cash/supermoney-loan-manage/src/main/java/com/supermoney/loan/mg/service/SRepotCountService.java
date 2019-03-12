package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SRepotCount;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/18.
 */
public interface SRepotCountService extends Service<SRepotCount> {
    /**
     * 获取统计信息
     * @param param
     * @return
     */
    public Result getCountInfo(Map<String,Object> param);

}
