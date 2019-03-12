package com.supermoney.open.platform.entity.vo.respond;

import com.supermoney.open.platform.entity.SBlackList;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BlacklistRespondVo {

    private String recommendation;

    private List defaultListResult;

    public BlacklistRespondVo(List<SBlackList> lists){
        List<Map<String,Object>> resultList = new ArrayList<>();
        for (SBlackList blackList : lists) {
            this.recommendation = blackList.getRecommendation();
            Map<String, Object> map = new HashMap<>();
            map.put("eventTime",blackList.getEventTime());
            map.put("hitReason",blackList.getHitReason());
            map.put("productType",blackList.getProductType());
            map.put("reasonCode",blackList.getReasonCode());
            resultList.add(map);
        }
        this.defaultListResult = resultList;
    }

    public String getRecommendation() {
        return recommendation;
    }

    public void setRecommendation(String recommendation) {
        this.recommendation = recommendation;
    }


    public List getDefaultListResult() {
        return defaultListResult;
    }

    public void setDefaultListResult(List defaultListResult) {
        this.defaultListResult = defaultListResult;
    }
}
