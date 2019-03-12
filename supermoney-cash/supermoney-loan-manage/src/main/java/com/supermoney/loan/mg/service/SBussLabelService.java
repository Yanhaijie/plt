package com.supermoney.loan.mg.service;
import com.supermoney.loan.mg.entity.SBussLabel;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;


/**
 * Created by xionghuifeng on 2018/02/11.
 */
public interface SBussLabelService extends Service<SBussLabel> {
    /**
     * 保存
     * @param sBussLabel
     * @return
     */
    public Result saveBussLable(SBussLabel sBussLabel);

    /**
     * 获取banner
     * @return
     */
    public SBussLabel getByName(String name);

    /**
     * 获取banner
     * @return
     */
    public  Result getBanner();
    /**
     * 获取数据
     * @param labelName
     * @return
     */
    public  SBussLabel getByLabelName(String labelName);
}
