package com.supermoney.loan.mg.service.impl;

import com.supermoney.loan.mg.dao.SAtBlackListMapper;
import com.supermoney.loan.mg.entity.SAtBlackList;
import com.supermoney.loan.mg.service.SAtBlackListService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.AdvanceAiUtil;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultCode;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/06/01.
 */
@Service
@Transactional
public class SAtBlackListServiceImpl extends AbstractService<SAtBlackList> implements SAtBlackListService {
    @Resource
    private SAtBlackListMapper sAtBlackListMapper;

    @Value("${advance.ai.accessKey}")
    private String accessKey;

    @Value("${advance.ai.secretKey}")
    private String secretKey;

    @Override
    public int blackListCheck2(Integer userId, String name, String idNumber, String phoneNumber) {
        Boolean resultBool;

        //本地库匹配
        Map<String, Object> param = new HashMap<>();
        param.put("idNumber",idNumber);
        param.put("phoneNumber",phoneNumber);
        List<SAtBlackList> blackListList = sAtBlackListMapper.selectHitBlackListByParam(param);

        if (blackListList != null && blackListList.size() > 0){
            return 1;
        }

        //Advance.ai 匹配
        Result result = AdvanceAiUtil.blacklistCheck(name,idNumber,phoneNumber,accessKey,secretKey);
        if (result.getCode() == ResultCode.SUCCESS.code) {
            Map<String, Object> tempMap = (Map<String, Object>) result.getData();

            resultBool = (Boolean) tempMap.get("result");
            if (resultBool == true) {
                SAtBlackList blackList = new SAtBlackList();
                blackList.setUserId(userId);
                blackList.setName(name);
                blackList.setIdNumber(idNumber);
                blackList.setPhoneNumber(phoneNumber);
                blackList.setRecommendation(tempMap.get("recommendation").toString());
                blackList.setEventTime(tempMap.get("eventTime") == null ? null : tempMap.get("eventTime").toString());
                blackList.setHitReason(tempMap.get("hitReason") == null ? null : tempMap.get("hitReason").toString());
                blackList.setProductType(tempMap.get("productType") == null ? null : tempMap.get("productType").toString());
                blackList.setReasonCode(tempMap.get("reasonCode") == null ? null : tempMap.get("reasonCode").toString());
                this.save(blackList);
                return 1;
            } else {
                return 0;
            }
        }
        else if (result.getCode() == 201) {
            return -2;
        }

        return -1;
    }

    @Override
    public List<SAtBlackList> selectBlackListByParam(Map<String, Object> param) {
        return sAtBlackListMapper.selectBlackListByParam(param);
    }
}
