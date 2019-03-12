package com.supers.p2p.assets.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.supers.p2p.assets.dao.SItemInfoMapper;
import com.supers.p2p.assets.entity.SItemInfo;
import com.supers.p2p.assets.entity.vo.CurrentInfo;
import com.supers.p2p.assets.service.SItemInfoService;
import com.supers.p2p.assets.utils.AbstractService;
import com.supers.p2p.assets.utils.ExcelUtil;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.UserUtils;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/07.
 */
@Service
@Transactional
public class SItemInfoServiceImpl extends AbstractService<SItemInfo> implements SItemInfoService {
    @Resource
    private SItemInfoMapper sItemInfoMapper;

    public List<Map<String, Object>> getAssetItemPoolList(Map<String ,Object> param){
        List<Map<String, Object>> list = sItemInfoMapper.selectAssetItemPoolList(param);
        return list;
    }

    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<Map<String, Object>> getList(Map<String,Object> param)
    {
        return  sItemInfoMapper.selectList(param);
    }

    /**
     * 查询应用详情
     * @param param
     * @return
     */
    public List<Map<String, Object>> getDetailList(Map<String,Object> param)
    {
        return  sItemInfoMapper.selectDetailList(param);
    }
    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param){
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }

    public List<SItemInfo> selectLockItem(Map<String,Object> map){
        return sItemInfoMapper.selectLockItem(map);
    }

    public void unLockItem(){
        sItemInfoMapper.unLockItem();
    }

    public void exportAssetItem(HttpServletResponse repsonse, List<Map<String, Object>> list) {
        Map<String,String> deadLineMap = Maps.newHashMap();
        Map<String,String> repayWayMap = Maps.newHashMap();
        Map<String,String> assetInfoMap = Maps.newHashMap();
        Map<String,String> useStatusMap = Maps.newHashMap();
        deadLineMap.put("1","0~3个月");
        deadLineMap.put("2","0~12个月");
        deadLineMap.put("3","0~24个月");
        deadLineMap.put("4","24个月以上");
        repayWayMap.put("1","到期还本付息");
        repayWayMap.put("2","等额本息");
        repayWayMap.put("3","先息后本");
        assetInfoMap.put("10","消费分期");
        assetInfoMap.put("11","融资租赁");
        assetInfoMap.put("12","车抵贷");
        assetInfoMap.put("13","房抵贷");
        assetInfoMap.put("14","信用贷");
        assetInfoMap.put("15","供应链金融");
        assetInfoMap.put("16","其他");
        assetInfoMap.put("1001","3C消费");
        assetInfoMap.put("1002","个人日常消费");
        assetInfoMap.put("1003","装修");
        assetInfoMap.put("1004","旅游");
        assetInfoMap.put("1005","教育");
        assetInfoMap.put("1006","医疗");
        assetInfoMap.put("1007","其他");
        useStatusMap.put("0","草稿");
        useStatusMap.put("1","待审核");
        useStatusMap.put("2","审核不通过");
        useStatusMap.put("3","审核通过");
        useStatusMap.put("4","发标中");
        useStatusMap.put("5","满标");
        if(list != null){
            for(Map<String, Object> map : list){
                String deadLineKey = map.get("borrow_deadline")==null ? "":map.get("borrow_deadline").toString();
                map.put("borrow_deadline",deadLineMap.get(deadLineKey));
                String repayWayKey = map.get("repay_way")==null ? "":map.get("repay_way").toString();
                map.put("repay_way",repayWayMap.get(repayWayKey));
                String assetInfoKeys = map.get("asset_info")==null ? "":map.get("asset_info").toString();
                if(StringUtils.isNotEmpty(assetInfoKeys)){
                    String[] assetInfoKey = assetInfoKeys.split(",");
                    String assetInfos ="";
                    for(int i=0;i<assetInfoKey.length;i++){
                        assetInfos =assetInfos+assetInfoMap.get(assetInfoKey[i]+"")+",";
                    }
                    assetInfos = assetInfos.substring(0,assetInfos.length()-1);
                    map.put("asset_info",assetInfos);
                }
                String useStatusKey = map.get("use_status")==null ? "":map.get("use_status").toString();
                map.put("use_status",useStatusMap.get(useStatusKey));
            }
        }
        String[] titles = {"项目ID","借款人","金额(元)","资产期限","还款方式","利率(%)","资产类型","状态"};
        String[] datakeys ={"id","borrower_name","borrow_amount","borrow_deadline","repay_way","borrow_rate","asset_info","use_status"};
        ExcelUtil.exportXlsx(repsonse, "资产项目", titles, list,datakeys);
    }
    public List search() {
        CurrentInfo currentInfo = UserUtils.getCurrentInfo();
        Map<String,Object> param=RequestUntil.getParams();
        Map<String,Object> searchMap = (Map<String,Object>)param.get("search");
        if(searchMap == null){
            searchMap = Maps.newHashMap();
        }
        searchMap.put("company_id",currentInfo.getCompanyId());
        //资金方
        if(currentInfo.getUserType() > 1){
            searchMap.put("company_type",1);
        }else{//资产方
            searchMap.put("company_type",0);
        }
        List<Map<String, Object>> list = this.getAssetItemPoolList(searchMap);
        return list;
    }
    public List searchCar() {
        CurrentInfo currentInfo = UserUtils.getCurrentInfo();
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("userId",currentInfo.getUserId());
        List<Map<String, Object>> list = this.getAssetItemCarList(paramMap);
        return list;
    }


    public List<Map<String, Object>> getAssetItemCarList(Map<String ,Object> param){
        return sItemInfoMapper.selectAssetItemCarList(param);
    }

    public void exportCar(Map<String ,Object> param){
        sItemInfoMapper.exportCar(param);
    }

}
