package com.supers.p2p.assets.controller;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Maps;
import com.supers.p2p.assets.entity.SRight;
import com.supers.p2p.assets.entity.SUser;
import com.supers.p2p.assets.entity.vo.CurrentInfo;
import com.supers.p2p.assets.entity.vo.SUserVo;
import com.supers.p2p.assets.service.SItemInfoService;
import com.supers.p2p.assets.service.SUserService;
import com.supers.p2p.assets.utils.RequestUntil;
import com.supers.p2p.assets.utils.Result;
import com.supers.p2p.assets.utils.ResultGenerator;
import com.supers.p2p.assets.utils.UserUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/ut")
@Api(value = "/ut",description = "")
public class UtController {

    @Autowired
    private SUserService sUserService;
    @Resource
    private SItemInfoService sItemInfoService;

    @PostMapping("/login")
    @ApiOperation("登录")
    public Result login(String username, String password) {
        return  sUserService.login(username,password);
    }

   // @GetMapping("/menu")
    @PostMapping("/menu")
    @ApiOperation("菜单权限")
    public Result menu(String appSecret) {
      return    sUserService.menu(UserUtils.getCurrentInfo().getUserId());
    }

    /**
     * 资产项目池
     * @return
     */


    /**
     * 资产项目池,暂时写
     * @return
     */
    @PostMapping("/getAssetItemPoolList")
    public Result getAssetItemPoolList(@RequestParam(defaultValue = "0") Integer page, @RequestParam(defaultValue = "0") Integer size) {
        PageHelper.startPage(page, size);
        List<Map<String, Object>> list = search();
        PageInfo pageInfo = new PageInfo(changeList(list));
        return ResultGenerator.genSuccessResult(pageInfo);
    }

    private List<Map<String, Object>> changeList(List<Map<String, Object>> list){
        Map<String,String> deadLineMap = Maps.newHashMap();
        Map<String,String> repayWayMap = Maps.newHashMap();
        Map<String,String> assetInfoMap = Maps.newHashMap();
        Map<String,String> useStatusMap = Maps.newHashMap();
        Map<String,String> professionTypeMap = Maps.newHashMap();
        professionTypeMap.put("1","企业主");
        professionTypeMap.put("2","个体工商户");
        professionTypeMap.put("3","上班人群");
        professionTypeMap.put("4","学生");
        professionTypeMap.put("5","无固定职业");
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
                String professionTypeKey = map.get("profession_type")==null ? "":map.get("profession_type").toString();
                map.put("profession_type",professionTypeMap.get(professionTypeKey));
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
        return list;
    }
    private List search() {
        CurrentInfo currentInfo = UserUtils.getCurrentInfo();
        Map<String,Object> param= RequestUntil.getParams();
        Map<String,Object> searchMap = (Map<String,Object>)param.get("search");
        List<Map<String, Object>> list = sItemInfoService.getAssetItemPoolList(searchMap);
        return list;
    }
    @PostMapping("/getDetail")
    public Result getDetail(@RequestParam Integer id) {
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("id",id);
        List<Map<String,Object>> result = sItemInfoService.getDetailList(paramMap);
        result = changeList(result);
        if(result != null && result.size() > 0){
            return ResultGenerator.genSuccessResult(result.get(0));
        }
        return ResultGenerator.genFailResult("无数据！");

    }
}
