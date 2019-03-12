package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SBountyMapper;
import com.supermoney.loan.mg.entity.SBounty;
import com.supermoney.loan.mg.entity.vo.DropVo;
import com.supermoney.loan.mg.service.SBountyService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/04.
 */
@Service
@Transactional
public class SBountyServiceImpl extends AbstractService<SBounty> implements SBountyService {
    @Resource
    private SBountyMapper sBountyMapper;

    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SBounty> getList(Map<String,Object> param)
    {
        return  sBountyMapper.selectList(param);
    }

    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param){
        PageHelper.startPage(page, size);
        List<SBounty> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    /**
     * 获取下拉数据
     * @return
     */
    @Override
    public  List<DropVo> getDrop(String isApi){
        List<DropVo> result=new ArrayList<>();
        Map<String,Object> map =new HashMap<>();
        if(StringUtils.isNotBlank(isApi)){
            map.put("isApi",isApi);
        }

        List<SBounty> list =getList(map);
        for(SBounty app:list){
            DropVo item=new DropVo();
            item.setText(app.getBountyName());
            item.setValue(app.getId().toString());
            result.add(item);
        }
        return  result;
    }

}
