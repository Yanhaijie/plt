package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.configurer.DataSourceContextHolder;
import com.supermoney.loan.mg.interfaces.DS;
import com.supermoney.loan.mg.dao.CAppUserMapper;
import com.supermoney.loan.mg.entity.CAppUser;
import com.supermoney.loan.mg.service.CAppUserService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Constants;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by wenyuhao on 2018/06/27.
 */
@Service
public class CAppUserServiceImpl extends AbstractService<CAppUser> implements CAppUserService {
    @Resource
    private CAppUserMapper cAppUserMapper;

    @Override
    @DS(Constants.Database.CLIENT)
    public List<CAppUser> selectList() {
        return cAppUserMapper.selectAll();
    }

    
}
