package com.supermoney.loan.api.service;
import com.supermoney.loan.api.entity.SAtCreditInformation;
import com.supermoney.loan.api.entity.vo.CreditVo;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;

import java.util.List;


/**
 * Created by wenyuhao on 2018/05/25.
 */
public interface SAtCreditInformationService extends Service<SAtCreditInformation> {

    /**
     * 提交征信资料
     * @param vo
     * @return
     */
    public Result comitCredit(CreditVo vo, Integer userId);

    /**
     * 提交征信资料(工作证明和家庭信息)
     * @param vo
     * @return
     */
    public Result comitWorkCredit(SAtCreditInformation information);

    /**
     * 获取用户征信资料
     * @param userId
     * @return
     */
    public Result  CreditInfo(Integer userId);

    /**
     * 是否征信通过
     * @param userId
     * @return
     */
    public Result  hasCredit(Integer userId);

    /**
     *  用户评级
     * @param userId
     * @return
     */
    public void gradeForUser(Integer userId);

    /**
     *  获取用户征信资料
     * @param userId
     * @return
     */
    public SAtCreditInformation getByUserId(Integer userId);

    /**
     * 获取等级认证信息
     * @param userId
     * @return
     */
    public Result getUserGrade(Integer userId);


    /**
     * 认证资料修改
     * @param userId
     * @return
     */
    public void creditInfoModify(Integer userId);


    /**
     * 获取未通审核的用户
     * @return
     */
    public List<SAtCreditInformation> selectUnCheckCreditList();
}
