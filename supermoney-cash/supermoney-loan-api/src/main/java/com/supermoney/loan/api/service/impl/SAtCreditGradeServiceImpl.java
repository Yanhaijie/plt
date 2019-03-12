package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.dao.SAtCreditGradeMapper;
import com.supermoney.loan.api.entity.SAtCreditGrade;
import com.supermoney.loan.api.service.SAtCreditGradeService;
import com.supermoney.loan.api.utils.AbstractService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/06/11.
 */
@Service
@Transactional
public class SAtCreditGradeServiceImpl extends AbstractService<SAtCreditGrade> implements SAtCreditGradeService {
    @Resource
    private SAtCreditGradeMapper sAtCreditGradeMapper;

    @Override
    public Map<String, Object> creditGradeListToMap(List<SAtCreditGrade> list){
        Map<String, Object> tempMap = new HashMap<>();

        for (SAtCreditGrade creditGrade: list) {
            tempMap.put("id_" + creditGrade.getGradeName(),creditGrade.getId());
            tempMap.put("gradeName_" + creditGrade.getGradeName(),creditGrade.getGradeName());
            tempMap.put("minCreditScore_" + creditGrade.getGradeName(),creditGrade.getMinCreditScore());
            tempMap.put("limitAmout_" + creditGrade.getGradeName(),creditGrade.getLimitAmout());
        }

        return tempMap;
    }


}
