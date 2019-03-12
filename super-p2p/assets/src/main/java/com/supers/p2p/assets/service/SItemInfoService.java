package com.supers.p2p.assets.service;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.supers.p2p.assets.entity.SItemInfo;
import com.supers.p2p.assets.entity.vo.CurrentInfo;
import com.supers.p2p.assets.utils.ExcelUtil;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.Service;
import com.supers.p2p.assets.utils.UserUtils;
import org.apache.commons.lang.StringUtils;

import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/07.
 */
public interface SItemInfoService extends Service<SItemInfo> {

    public List<Map<String, Object>> getAssetItemPoolList(Map<String ,Object> param);

    public List<Map<String, Object>> getAssetItemCarList(Map<String ,Object> param);

    /**
     * 查询
     * @param param
     * @return
     */
    public List<Map<String, Object>> getList(Map<String,Object> param);

    public List<Map<String, Object>> getDetailList(Map<String,Object> param);
    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);

    /**
     * 查询一个小时之前锁定的项目
     * @param map
     * @return
     */
    public List<SItemInfo> selectLockItem(Map<String,Object> map);

    /**
     * 解锁锁定一个已锁定1个小时左右的项目
     * @return
     */
    public void unLockItem();

    /**
     * 导出资产项目
     * @param repsonse
     * @param list
     */
    public void exportAssetItem(HttpServletResponse repsonse, List<Map<String, Object>> list);

    /**
     * 搜索资产项目
     * @return
     */
    public List search();

    /**
     * 搜索购物车中的资产项目
     * @return
     */
    public List searchCar();

    public void exportCar(Map<String ,Object> param);

}
