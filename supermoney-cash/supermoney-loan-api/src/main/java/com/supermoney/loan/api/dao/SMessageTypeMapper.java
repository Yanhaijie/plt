package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SMessageType;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;

public interface SMessageTypeMapper extends Mapper<SMessageType> {
    /**
     * 查询所用带排序
     * @return
     */
    public List<SMessageType> findAllOrder();
}