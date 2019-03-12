package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.STransfertoKeyMapper;
import com.supermoney.loan.api.entity.STransfertoKey;
import com.supermoney.loan.api.service.STransfertoKeyService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by @author on 2018/07/05.
 */
@Service
@Transactional
public class STransfertoKeyServiceImpl extends AbstractService<STransfertoKey> implements STransfertoKeyService {
    @Resource
    private STransfertoKeyMapper sTransfertoKeyMapper;

}
