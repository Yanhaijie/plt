package com.supermoney.loan.api.dao;

import com.supermoney.loan.api.entity.SUser;
import com.supermoney.loan.api.entity.vo.UserCountVo;
import com.supermoney.loan.api.entity.vo.UserInfoVo;
import com.supermoney.loan.api.utils.Mapper;

import java.util.List;
import java.util.Map;

public interface SUserMapper extends Mapper<SUser> {
    /**
     * 用户信息
     * @param map
     * @return
     */
    public UserInfoVo getUserInfo(Map<String,Object> map);
    /**
     * 获取用户统计信息
     * @param map
     * @return
     */
    public UserCountVo getUserCount(Map<String,Object> map);

    /**
     * 根据OPENID获取用户
     * @param map
     * @return
     */
    public  SUser getUserByOpenId(Map<String,Object> map);

    /**
     * 保存用户信用信息
     * @param param
     * @return
     */
    public void updateCreditInfo(Map<String, Object> param);

    /**
     * 获取征信信息改变的用户
     * @return
     */
    public List<Map<String, Object>> selectCreditModifyUser();

    /**
     * 根据用户名获取
     * @param userName
     * @return
     */
    public  SUser getUserByMobile(String userName);

    /**
     * 修改用户最后登录时间
     * @param userId
     */
    public void updatelastLoginTime(int userId);
}