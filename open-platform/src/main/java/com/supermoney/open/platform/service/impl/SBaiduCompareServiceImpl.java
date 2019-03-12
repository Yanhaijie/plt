package com.supermoney.open.platform.service.impl;

import com.supermoney.open.platform.dao.SBaiduCompareMapper;
import com.supermoney.open.platform.entity.SBaiduCompare;
import com.supermoney.open.platform.service.SBaiduCompareService;
import com.supermoney.open.platform.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;


/**
 * Created by @author on 2018/10/22.
 */
@Service
@Transactional
public class SBaiduCompareServiceImpl extends AbstractService<SBaiduCompare> implements SBaiduCompareService {
    @Resource
    private SBaiduCompareMapper sBaiduCompareMapper;

}
