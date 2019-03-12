package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SUserBindMapper;
import com.supermoney.loan.mg.entity.SUserBind;
import com.supermoney.loan.mg.entity.vo.SUserBindVo;
import com.supermoney.loan.mg.service.SUserBindService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Constants;
import org.apache.commons.lang.StringUtils;
import org.omg.CORBA.OBJ_ADAPTER;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/04.
 */
@Service
@Transactional
public class SUserBindServiceImpl extends AbstractService<SUserBind> implements SUserBindService {
    @Resource
    private SUserBindMapper sUserBindMapper;

    @Override
    public List<Map<String, Object>> getUserBindList(Map<String, Object> param) {
        return sUserBindMapper.getUserBindList(param);
    }

    /**
     * 获取用户使用的默认绑卡
     * @param userId
     * @return
     */
    @Override
    public SUserBind getUserUseCard(Integer userId){
        SUserBind param=new SUserBind();
        param.setUserId(userId);
        param.setCardStatus(Constants.STATUS_USE);
        List<SUserBind> userbindList=   sUserBindMapper.select(param);
        return  userbindList.size()==0 ? null:userbindList.get(0);
    }

    /**
     * 根据卡号获取用户绑卡银行信息
     * @param userId
     * @param cardAccount
     * @return
     */
    public SUserBindVo getUserBindByCardAccount(Integer userId, String cardAccount){
        Map<String, Object> map = new HashMap<>();
        map.put("userId", userId);
        if(StringUtils.isNotBlank(cardAccount)){
            map.put("cardAccount", cardAccount);
        }
        map.put("cardStatus",Constants.STATUS_USE);
        List<SUserBindVo> list= sUserBindMapper.getUserBindCardList(map);
        return  list.size()>0 ? list.get(0):null;
    }
}
