package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SMessage;
import com.supermoney.loan.mg.entity.vo.SMessageVo;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SMessageMapper extends Mapper<SMessage> {

    public List<SMessageVo> selectList(Map<String,Object> map);

    /**
     * 执行查询sql
     * @param param
     * @return
     */
    public List<Map<String,Object>> executeSelectSql(Map<String,Object> param);


}