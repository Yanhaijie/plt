package com.supermoney.loan.mg.dao;

import com.supermoney.loan.mg.entity.SUserBind;
import com.supermoney.loan.mg.entity.vo.SUserBindVo;
import com.supermoney.loan.mg.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SUserBindMapper extends Mapper<SUserBind> {

    /**
     * 获取用户绑卡列表
     *
     * @param param
     * @return
     */
    public List<Map<String, Object>> getUserBindList(Map<String, Object> param);
    /**
     * 获取当前用户绑定的卡列表
     *
     * @param maps
     * @return
     */
    List<SUserBindVo> getUserBindCardList(Map<String, Object> maps);
}