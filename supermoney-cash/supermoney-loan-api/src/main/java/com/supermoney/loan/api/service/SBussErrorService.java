package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SBussError;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;


/**
 * Created by wenyuhao on 2018/06/08.
 */
public interface SBussErrorService extends Service<SBussError> {

    /**
     *业务异常记录增加
     * @param bussType
     * @param relateId
     * @param remark
     * @param json
     * @return
     */
    public Result addBussError(Integer bussType, String relateId, String remark, String json);

    /**
     * 获取错误业务
     * @param bussType
     * @param relateId
     * @return
     */
    public SBussError getByType(Integer bussType,String relateId);

}
