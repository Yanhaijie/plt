package com.supermoney.loan.api.service;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.api.entity.SBounty;
import com.supermoney.loan.api.entity.SBountyLoan;
import com.supermoney.loan.api.entity.vo.DropVo;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/04.
 */
public interface SBountyService extends Service<SBounty> {
    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SBounty> getList(Map<String,Object> param);
    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);
    /**
     * 获取下拉数据
     * @return
     */
    public List<DropVo> getDrop();
    /**
     * 获取赏金任务列表
     * @param page
     * @param size
     * @param userId
     * @return
     */
    public Result getBountyList(int page, int size,Integer userId);

    /**
     * 获取H5赏金任务介绍
     * @param userId
     * @param bountyId
     * @return
     */
    public  Result getSharesms(String userId, String bountyId);

    /**
     * 分享superMoney
     * @param userId
     * @return
     */
    public  Result sharSuperMoney(Integer userId);

    /**
     * 计算还款明细
     * @param bountyId
     * @param money
     * @param limit
     * @param limitUnit
     * @return
     */
    public Result calculation(Integer bountyId, Integer money,Integer limit, Integer limitUnit);
    /**
     * 获取superMoney任务
     * @return
     */
    public SBounty getSuperMoneyBounty();
    /**
     * 获取现金贷产品
     * @param bountyId
     * @return
     */
    public SBountyLoan getBountyCashLoan(Integer bountyId);

}
