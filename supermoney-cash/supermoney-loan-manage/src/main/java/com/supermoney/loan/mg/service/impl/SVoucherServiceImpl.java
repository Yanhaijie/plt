package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SVoucherMapper;
import com.supermoney.loan.mg.entity.SVoucher;
import com.supermoney.loan.mg.entity.vo.DropVo;
import com.supermoney.loan.mg.service.SVoucherService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/15.
 */
@Service
@Transactional
public class SVoucherServiceImpl extends AbstractService<SVoucher> implements SVoucherService {
    @Resource
    private SVoucherMapper sVoucherMapper;
    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SVoucher> getList(Map<String,Object> param)
    {
        return  sVoucherMapper.selectList(param);
    }

    /**
     * 获取可用抵用券下拉框
     * @return
     */
    public Result getDrop(){
        List<DropVo> result=new ArrayList<>();
        Map<String,Object> param=new HashMap();
        param.put("voucherStatus",1);
        List<SVoucher> list =getList(param);
        for(SVoucher app:list){
            DropVo item=new DropVo();
            item.setText(app.getVoucherName());
            item.setValue(app.getId().toString());
            result.add(item);
        }
        return ResultGenerator.genSuccessResult(result);
    }

    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    @Override
    public PageInfo getByPage(int page, int size, Map<String,Object> param){
        PageHelper.startPage(page, size);
        List<SVoucher> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
