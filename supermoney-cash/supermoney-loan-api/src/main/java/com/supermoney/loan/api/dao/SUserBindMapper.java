package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SUserBind;
import com.supermoney.loan.api.entity.vo.SUserBindVo;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SUserBindMapper extends Mapper<SUserBind> {

    Integer updateUserCardStatus(Map<String, Object> maps);


    Integer updateUserCard(Map<String, Object> maps);

    /**
     * 用户绑卡数量
     *
     * @param userId
     * @return
     */
    public Integer userBindTotal(Integer userId);


    Integer addUserCardBind(SUserBind entity);


    /**
     * 获取当前用户绑定的卡列表
     *
     * @param maps
     * @return
     */
    List<SUserBindVo> getUserBindCardList(Map<String, Object> maps);
}
