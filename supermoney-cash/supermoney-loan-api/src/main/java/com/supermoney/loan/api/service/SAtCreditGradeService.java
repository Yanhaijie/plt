package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SAtCreditGrade;
import com.supermoney.loan.api.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/06/11.
 */
public interface SAtCreditGradeService extends Service<SAtCreditGrade> {

    public Map<String, Object> creditGradeListToMap(List<SAtCreditGrade> list);
}
