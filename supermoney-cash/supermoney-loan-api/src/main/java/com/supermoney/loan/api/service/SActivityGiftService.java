package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SActivityGift;
import com.supermoney.loan.api.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/22.
 */
public interface SActivityGiftService extends Service<SActivityGift> {

    public List<SActivityGift> selectCommonGiftByMap(Map<String, Object> param);

    public List<Map<String, Object>> selectGiftListByMap(Map<String, Object> param);

    public void subRemainCount(Integer id);

    public void addRemainCount(Integer id);
}
