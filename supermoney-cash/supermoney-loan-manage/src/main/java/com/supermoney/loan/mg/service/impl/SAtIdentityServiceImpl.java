package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.dao.SAtIdentityMapper;
import com.supermoney.loan.mg.dao.SUserMapper;
import com.supermoney.loan.mg.entity.SAtIdentity;
import com.supermoney.loan.mg.entity.SUser;
import com.supermoney.loan.mg.service.SAtIdentityService;
import com.supermoney.loan.mg.service.SCertRecordService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.AppProperties;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by xionghuifeng on 2018/01/22.
 */
@Service
@Transactional
public class SAtIdentityServiceImpl extends AbstractService<SAtIdentity> implements SAtIdentityService {
    @Resource
    private SAtIdentityMapper sAtIdentityMapper;

    @Resource
    private SUserMapper sUserMapper;

    @Resource
    private AppProperties appProperties;

    @Resource
    private SCertRecordService certRecordService;

    /**
     * 查询应用
     * @param param
     * @return
     */
    @Override
    public List<SAtIdentity> getList(Map<String,Object> param)
    {
        return  sAtIdentityMapper.selectList(param);
    }

    @Override
    public List<SAtIdentity> selectHaveOrderIdentity() {
        return sAtIdentityMapper.selectHaveOrderIdentity();
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
        List<SAtIdentity> list =getList(param);
        for(SAtIdentity item :list){
            item.setImgBack(item.getImgBack());
            item.setImgHold(item.getImgHold());
            item.setImgFront(item.getImgFront());
        }
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
    /**
     * 更新信息
     * @param sAtIdentity
     * @return
     */
    public Result updateInfo(SAtIdentity sAtIdentity){
        if(sAtIdentity.getUserId()==null || sAtIdentity.getUserId().compareTo(1)==-1){
            return  ResultGenerator.genFailResult("userId is null");
        }
        if(StringUtils.isBlank(sAtIdentity.getIdNumber()) || StringUtils.isBlank(sAtIdentity.getRealName())){
            return  ResultGenerator.genFailResult("realName or idNumber is null");
        }
        SUser user=sUserMapper.selectByPrimaryKey(sAtIdentity.getUserId());
        if(user==null){
            return  ResultGenerator.genFailResult("user is null");
        }
        //set realname for pass
        if(sAtIdentity.getIdentityStatus().compareTo(3)==0){
            user.setRealName(sAtIdentity.getRealName());
        }
        else if(sAtIdentity.getIdentityStatus().compareTo(2)==0){
            user.setRealName("");
        }
        else if(sAtIdentity.getIdentityStatus().compareTo(1)==0) {
            user.setRealName("");
        }
        else if(sAtIdentity.getIdentityStatus().compareTo(4)==0) {
            user.setRealName("");
        }else {
            user.setRealName(null);
        }
        //上传图片不更新
        sAtIdentity.setImgFront(null);
        sAtIdentity.setImgHold(null);
        sAtIdentity.setImgBack(null);

        //审核通过 添加认证记录表记录
        if (sAtIdentity.getIdentityStatus() != null && sAtIdentity.getIdentityStatus() == 3){
            int certStatus = certRecordService.getIdentityCertStatus(user.getId());
            if (certStatus != 1){
                certRecordService.safeIdentityCert(user.getId(),1);
            }
        }
        //审核不通过 添加认证记录表记录
        else if (sAtIdentity.getIdentityStatus() != null && (sAtIdentity.getIdentityStatus() == 4 || sAtIdentity.getIdentityStatus() == 1 || sAtIdentity.getIdentityStatus() == 2)){
            int certStatus = certRecordService.getIdentityCertStatus(user.getId());
            if (certStatus != 0) {
                certRecordService.safeIdentityCert(user.getId(), 0);
            }
        }
        
        sUserMapper.updateByPrimaryKeySelective(user);
        sAtIdentityMapper.updateByPrimaryKeySelective(sAtIdentity);
        return ResultGenerator.genSuccessResult();
    }

    @Override
    public List<SAtIdentity> selectUncheckIdentity() {
        return sAtIdentityMapper.selectUncheckIdentity();
    }

    @Override
    public List<SAtIdentity> selectUncheck() {
        return sAtIdentityMapper.selectUncheck();
    }


    /**
     * 批量审核
     * @param status
     * @param ids
     * @return
     */
    @Transactional
    public  Result audits(Integer status,String[] ids){

        for(String id:ids){
            SAtIdentity identity=sAtIdentityMapper.selectByPrimaryKey(Integer.valueOf(id));
            if(identity==null){
                continue;
            }
            if(status.compareTo(0)==0){
                continue;
            }
            if (status == 3 || status == 4){
                //征信资料修改标记位
                Map<String, Object> param = new HashMap<>();
                param.put("userId",identity.getUserId());
                param.put("creditModify",1);
                sUserMapper.updateCreditInfo(param);
            }
            identity.setIdentityStatus(status);
            updateInfo(identity);
        }
        Result result=  ResultGenerator.genSuccessResult();
        result.setMessage("审核成功!");
        return  result;
    }

    /**
     * 获取审核成功的实名认真
     * @param userId
     * @return
     */
    public  SAtIdentity getSuccessIdentity(Integer userId){
        Map<String, Object> param = new HashMap<>();
        param.put("userId",userId);
        return  sAtIdentityMapper.selectUserIdentity(param);
    }
}
