package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SUserAccountBookMapper;
import com.supermoney.loan.api.entity.SUserAccountBook;
import com.supermoney.loan.api.service.SUserAccountBookService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by xionghuifeng on 2018/01/18.
 */
@Service
@Transactional
public class SUserAccountBookServiceImpl extends AbstractService<SUserAccountBook> implements SUserAccountBookService {
    @Resource
    private SUserAccountBookMapper sUserAccountBookMapper;

}
