package com.supermoney.open.platform.service;
import com.supermoney.open.platform.entity.SOcr;
import com.supermoney.open.platform.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by @author on 2018/10/22.
 */
public interface SOcrService extends Service<SOcr> {
    public List<SOcr> selectOcrListByParam(Map<String, Object> param);
}
