package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SExchangeRateMapper;
import com.supermoney.loan.mg.dao.SMessageTypeMapper;
import com.supermoney.loan.mg.entity.SExchangeRate;
import com.supermoney.loan.mg.entity.SMessageType;
import com.supermoney.loan.mg.entity.vo.DropVo;
import com.supermoney.loan.mg.service.SMessageTypeService;
import com.supermoney.loan.mg.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/04.
 */
@Service
@Transactional
public class SMessageTypeServiceImpl extends AbstractService<SMessageType> implements SMessageTypeService {
    @Resource
    private SMessageTypeMapper sMessageTypeMapper;


    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SMessageType> getList(Map<String,Object> param)
    {
        return  sMessageTypeMapper.selectList(param);
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
        List<SMessageType> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
    /**
     * 获取消息类型下拉数据
     * @return
     */
    public  List<DropVo> getDrop() {
        List<DropVo> result = new ArrayList<>();
        List<SMessageType> list = getList(null);
        for (SMessageType app : list) {
            DropVo item = new DropVo();
            item.setText(app.getName());
            item.setValue(app.getId().toString());
            result.add(item);
        }
        return result;
    }

}
