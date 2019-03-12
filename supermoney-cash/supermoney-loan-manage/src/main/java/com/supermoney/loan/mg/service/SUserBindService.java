package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SUserBind;
import com.supermoney.loan.mg.entity.vo.SUserBindVo;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/04.
 */
public interface SUserBindService extends Service<SUserBind> {

    public List<Map<String, Object>> getUserBindList(Map<String, Object> param);
    /**
     * 获取用户使用的默认绑卡
     * @param userId
     * @return
     */
    public SUserBind getUserUseCard(Integer userId);

    public SUserBindVo getUserBindByCardAccount(Integer userId, String cardAccount);

}
