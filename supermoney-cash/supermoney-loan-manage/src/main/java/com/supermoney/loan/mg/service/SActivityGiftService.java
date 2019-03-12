package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SActivityGift;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/04/21.
 */
public interface SActivityGiftService extends Service<SActivityGift> {

    public List<Map<String, Object>> selectGiftByMap(Map<String,Object> param);

    public List<Map<String, Object>> selectSearchGiftByMap(Map<String,Object> param);

    public List<Map<String, Object>> selectBackGiftByMap(Map<String,Object> param);
}
