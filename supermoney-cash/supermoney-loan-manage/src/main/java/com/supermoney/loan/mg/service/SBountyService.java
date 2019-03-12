package com.supermoney.loan.mg.service;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SBounty;
import com.supermoney.loan.mg.entity.vo.DropVo;
import com.supermoney.loan.mg.utils.Service;

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
    public List<DropVo> getDrop(String isApi);

}
