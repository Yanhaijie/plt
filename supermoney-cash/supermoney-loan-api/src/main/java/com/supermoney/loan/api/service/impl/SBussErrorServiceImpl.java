package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SBussErrorMapper;
import com.supermoney.loan.api.entity.SBussError;
import com.supermoney.loan.api.service.SBussErrorService;
import com.supermoney.loan.api.utils.AbstractService;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import sun.security.krb5.internal.PAData;

import javax.annotation.Resource;


/**
 * Created by wenyuhao on 2018/06/08.
 */
@Service
@Transactional
public class SBussErrorServiceImpl extends AbstractService<SBussError> implements SBussErrorService {
    @Resource
    private SBussErrorMapper sBussErrorMapper;

    /**
     *
     * @param bussType
     * @param relateId
     * @param remark
     * @param json
     * @return
     */
    @Override
    public Result addBussError(Integer bussType,String relateId,String remark,String json){
        SBussError error=new SBussError();
        error.setBussType(bussType);
        error.setRelateId(relateId);
        error.setRemark(remark);
        error.setJson(json);
        error.setErrorStatus(0);
        sBussErrorMapper.insertSelective(error);
        return ResultGenerator.genSuccessResult(error.getId());
    }

    /**
     * 获取错误业务
     * @param bussType
     * @param relateId
     * @return
     */
    public SBussError getByType(Integer bussType,String relateId){
        SBussError param=new SBussError();
        param.setBussType(bussType);
        param.setRelateId(relateId);
       return  sBussErrorMapper.selectOne(param);
    }
}
