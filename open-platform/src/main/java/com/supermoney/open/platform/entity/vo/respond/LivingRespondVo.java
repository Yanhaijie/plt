package com.supermoney.open.platform.entity.vo.respond;

import java.math.BigDecimal;
import java.util.Map;

public class LivingRespondVo {

    private Map<String, Object> thresholds;

    private BigDecimal faceLiveness;

    public LivingRespondVo(Map<String, Object> param){
        this.thresholds = (Map<String, Object>) param.get("thresholds");
        this.faceLiveness = new BigDecimal(param.get("face_liveness").toString());
    }

    public Map<String, Object> getThresholds() {
        return thresholds;
    }

    public void setThresholds(Map<String, Object> thresholds) {
        this.thresholds = thresholds;
    }

    public BigDecimal getFaceLiveness() {
        return faceLiveness;
    }

    public void setFaceLiveness(BigDecimal faceLiveness) {
        this.faceLiveness = faceLiveness;
    }
}
