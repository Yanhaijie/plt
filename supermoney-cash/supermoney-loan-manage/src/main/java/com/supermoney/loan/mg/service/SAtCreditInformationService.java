package com.supermoney.loan.mg.service;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SAtCreditInformation;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/28.
 */
public interface SAtCreditInformationService extends Service<SAtCreditInformation> {
    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);
    /**
     * 批量审核
     * @param status
     * @param ids
     * @return
     */
    public Result audits(Integer status, String[] ids);

    /**
     * 获取未通审核的用户
     * @return
     */
    public List<SAtCreditInformation> selectUnCheckCreditList();
    /**
     * 获取详情
     * @param id
     * @return
     */
    public  Result getInfo(Integer id);

}
