package com.supermoney.loan.service.impl;

import com.supermoney.loan.dao.SUserAccountBookMapper;
import com.supermoney.loan.entity.SUserAccountBook;
import com.supermoney.loan.service.SUserAccountBookService;
import com.supermoney.loan.vo.WaitkeOutBalanceVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by xionghuifeng on 2018/01/18.
 */
@Service
@Transactional
public class SUserAccountBookServiceImpl implements SUserAccountBookService {

    @Resource
    private SUserAccountBookMapper sUserAccountBookMapper;

    @Override
    public List<WaitkeOutBalanceVo> getAllUserAccountBook() {
        return sUserAccountBookMapper.getAllUserAccountBook();
    }

    @Override
    public int getCount() {
        return sUserAccountBookMapper.getCount();
    }
}
