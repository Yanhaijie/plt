package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SMessageMapper;
import com.supermoney.loan.api.entity.SMessage;
import com.supermoney.loan.api.service.SMessageService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by wenyuhao on 2018/04/10.
 */
@Service
@Transactional
public class SMessageServiceImpl extends AbstractService<SMessage> implements SMessageService {
    @Resource
    private SMessageMapper sMessageMapper;

}
