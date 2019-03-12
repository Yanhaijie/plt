package com.supermoney.loan.market.service;
import com.supermoney.loan.market.entity.SBussLabel;
import com.supermoney.loan.market.utils.Result;
import com.supermoney.loan.market.utils.Service;


/**
 * Created by bear on 2018/11/13.
 */
public interface SBussLabelService extends Service<SBussLabel> {
    public Result saveLable(String pwd , String labelName, String lableVal);

    public  Result getByName(String lableName);

    /**
     * 获取业务标签
     * @param labelName
     * @return
     */
    public  Result getByNameNotSplit(String labelName);
    /**
     *
     * @param labelName
     * @return
     */
    public  Result getByLabelToJson(String labelName);
    /**
     * 获取数据
     * @param labelName
     * @return
     */
    public  SBussLabel getByLabelName(String labelName);

}
