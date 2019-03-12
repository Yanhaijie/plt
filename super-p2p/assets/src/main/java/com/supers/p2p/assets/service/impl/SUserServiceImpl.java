package com.supers.p2p.assets.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.supers.p2p.assets.dao.SCompanyMapper;
import com.supers.p2p.assets.dao.SRightMapper;
import com.supers.p2p.assets.dao.SUserMapper;
import com.supers.p2p.assets.dao.SUserRoleMapper;
import com.supers.p2p.assets.entity.SCompany;
import com.supers.p2p.assets.entity.SRight;
import com.supers.p2p.assets.entity.SUser;
import com.supers.p2p.assets.entity.SUserRole;
import com.supers.p2p.assets.entity.vo.SUserVo;
import com.supers.p2p.assets.entity.vo.UserInfo;
import com.supers.p2p.assets.service.SUserService;
import com.supers.p2p.assets.utils.*;
import com.wi.cloud.oauthclient.GwtOauthClient;
import com.wi.cloud.oauthclient.vo.OauthUserInfo;
import com.wi.cloud.oauthclient.vo.RegUserInfo;
import io.swagger.models.auth.In;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/05/10.
 */
@Service
@Transactional
public class SUserServiceImpl extends AbstractService<SUser> implements SUserService {
    @Resource
    private SUserMapper sUserMapper;

    @Resource
    private SCompanyMapper sCompanyMapper;

    @Resource
    private SUserRoleMapper sUserRoleMapper;

    @Resource
    private SRightMapper sRightMapper;


    @Autowired
    private GwtOauthClient gwtOauthClient;

    /**
     * 新增用户
     * @param sUserVo
     * @return
     */
    @Override
    public Result addUser(SUserVo sUserVo){
        SUser sUser=(SUser)sUserVo;
        if(sUser.getId()!=null && sUser.getId().compareTo(0)>0)
        {//修改
              SUser gotUser=sUserMapper.selectByPrimaryKey(sUser.getId());
              if(gotUser==null){
                  return  ResultGenerator.genFailResult("用户不存在");
              }
              if(!gotUser.getUserName().equals(sUser.getUserName()) && hasUserName(sUser.getUserName())){
                  return  ResultGenerator.genFailResult("用户名已存在,请更换");
              }
              if(StringUtils.isNotEmpty(sUser.getUserPassword()) && StringUtils.isNotBlank(sUser.getUserPassword())){
                  //修改oauth密码
                  com.wi.cloud.oauthclient.vo.Result gwtResult= gwtOauthClient.editPwd(sUser.getUserName(),sUser.getUserPassword());
                  if(gwtResult.getCode()!=ResultCode.SUCCESS.code){
                      return ResultGenerator.genFailResult("oauth edit password error!");
                  }
              }else {
                  sUser.setUserPassword(null);
              }
              sUser.setId(gotUser.getId());
              sUserMapper.updateByPrimaryKeySelective(sUser);
        }else {
            //新增
            if(hasUserName(sUser.getUserName())){
                return  ResultGenerator.genFailResult("用户名已存在,请更换");
            }
            if(!hasOauthUser(sUser.getUserName())) {
                //oauth注册-授权中心增加用户
                RegUserInfo reguser = new RegUserInfo();
                reguser.setUserName(sUser.getUserName());
                reguser.setPassword(sUser.getUserPassword());
                reguser.setNickName(sUser.getUserName());
                reguser.setGender(sUser.getSex().toString());
                reguser.setUserType(1);//对内用户
                reguser.setRegSource("ROLE_P2P_USER");
                com.wi.cloud.oauthclient.vo.Result oauthRegResult = gwtOauthClient.regUser(reguser);
                if (oauthRegResult.getCode() != ResultCode.SUCCESS.code) {
                    return ResultGenerator.genFailResult("oauth reg faild:" + oauthRegResult.getMessage());
                }
            }
            //新增公司

            if( StringUtils.isNotBlank(sUserVo.getCompanyName())){
                SCompany company=new SCompany();
                company.setFullName(sUserVo.getCompanyName());
                company.setCompanyType(0);
                company.setLegalPersonName("");
                company.setAuditStatus(0);
                //公司类型:0资产方，1资金方
                //用户类型:0资产方个人，1资产方企业，2资金方个人，3资金方企业
                if(sUser.getUserType().intValue() < 2){
                    company.setCompanyType(0);
                }else{
                    company.setCompanyType(1);
                }
                sCompanyMapper.insertSelective(company);
                sUser.setCompanyId(company.getId());
            }
            //新增用户信息
            sUserMapper.insertSelective(sUser);
        }

        //角色绑定
        if(sUserVo.getRoleId()!=null && sUserVo.getRoleId().compareTo(0)>0){
            SUserRole  userRole=new SUserRole();
            userRole.setUserId(sUser.getId());
            SUserRole hasUserRole=sUserRoleMapper.selectOne(userRole);

            if(hasUserRole!=null){
               hasUserRole.setRoleId(sUserVo.getRoleId());
               sUserRoleMapper.updateByPrimaryKeySelective(hasUserRole);

            }else {
                userRole.setRoleId(sUserVo.getRoleId());
                sUserRoleMapper.insertSelective(userRole);
            }

        }

        return ResultGenerator.genSuccessResult();
    }
    /**
     * oauth用户是否存在
     * @param username
     * @return
     */
    public  boolean hasOauthUser(String username){
        return   gwtOauthClient.hasUserName(username);
    }
    /**
     * 是否存在
     * @param userName
     * @return
     */
    public  boolean hasUserName(String userName){
        return  getbyUserName(userName)!=null;
    }

    /**
     * 用户名获取
     * @param userName
     * @return
     */
    public  SUser getbyUserName(String userName){
        SUser param=new SUser();
        param.setUserName(userName);
        return sUserMapper.selectOne(param);
    }

    /**
     * 用户ID获取
     * @param userId
     * @return
     */
    public  SUser getByUserId(Integer userId){
        return  sUserMapper.selectByPrimaryKey(userId);
    }

    /**
     * 密码登录
     * @param userName
     * @param password
     * @return
     */
    public   Result login(String userName, String password){
        if(StringUtils.isBlank(userName) || StringUtils.isBlank(password)){
            return  ResultGenerator.genFailResult("number or password is empty!");
        }
        if(userName.equals("superp2p")){
            return  superManageLogin(userName,password);
        }

        SUser user=getbyUserName(userName);
        if(user==null){
            return ResultGenerator.genFailResult("login faild: have not user");
        }
        if(!user.getUserPassword().equals(password)){
            return ResultGenerator.genFailResult("login faild: username or password faild!");
        }
        OauthUserInfo oauthInfo=loginOauth(userName,password);
        if(oauthInfo==null){
            return  ResultGenerator.genFailResult("loginOuth login faild!");
        }
        //用户信息
        String appStr=UserUtils.toAppSecret(user.getId(),user.getCompanyId(),user.getUserType());
        UserInfo userInfo=new UserInfo();
        userInfo.setAccess_token(oauthInfo.getAccess_token());
        userInfo.setRefresh_token(oauthInfo.getRefresh_token());
        userInfo.setExpiresIn(oauthInfo.getExpiresIn());
        userInfo.setToken_type(oauthInfo.getToken_type());
        userInfo.setAppSecret(appStr);
        userInfo.setCompanyName("");
        userInfo.setUsername(userName);
        userInfo.setRealName(user.getRealName());
        userInfo.setUserType(user.getUserType());
        userInfo.setSex(user.getSex());
        return  ResultGenerator.genSuccessResult(userInfo);
    }

    /**
     * 超级管理员登录
     * @param userName
     * @param password
     * @return
     */
    public  Result superManageLogin(String userName, String password){
        OauthUserInfo oauthInfo=loginOauth(userName,password);
        if(oauthInfo==null){
            return  ResultGenerator.genFailResult("loginOuth login faild!");
        }
        //用户信息
        String appStr=UserUtils.toAppSecret(-1,-1,-1);
        UserInfo userInfo=new UserInfo();
        userInfo.setAccess_token(oauthInfo.getAccess_token());
        userInfo.setRefresh_token(oauthInfo.getRefresh_token());
        userInfo.setExpiresIn(oauthInfo.getExpiresIn());
        userInfo.setToken_type(oauthInfo.getToken_type());
        userInfo.setAppSecret(appStr);
        userInfo.setCompanyName("");
        userInfo.setUsername(userName);
        userInfo.setRealName("超级管理员");
        return  ResultGenerator.genSuccessResult(userInfo);

    }



    /**
     * oauth 登录
     * @param username
     * @param password
     * @return
     */
    public OauthUserInfo loginOauth(String username, String password){
        boolean hasUser=false;
        boolean login=gwtOauthClient.toLogin(username,password);
        if(login){
            hasUser=gwtOauthClient.getOauthUserInfo(gwtOauthClient.getUserInfo().getAccess_token());
        }
        return  hasUser?  gwtOauthClient.getUserInfo():null;
    }

    /**
     * 获取菜单
     * @param userId
     * @return
     */
    public  Result menu(Integer userId){
        List<SRight> list=sRightMapper.selectAll();
        if(userId.compareTo(-1)==0){
            //超级管理员账号
           return  ResultGenerator.genSuccessResult(list);
        }
        //普通用户账号
        SUser user=getByUserId(userId);
        if(user==null){
            return  ResultGenerator.genSuccessResult();
        }
        Map<String,Object> param=new HashMap<>();
        param.put("userId",userId.toString());
        param.put("visibleType","0");
        List<SRight> userRightList= sRightMapper.userRight(param);
        //找出parentId
        List<Integer> parentIds=new ArrayList<>();
        userRightList.stream().filter(t-> t.getParentId().compareTo(0)!=0 && !parentIds.contains(t.getParentId())).forEach(s-> parentIds.add(s.getParentId()));
        //找出不存在的parentId
        List<Integer> haveNotIds=new ArrayList<>();
        List<Integer> ids=new ArrayList<>();
        userRightList.stream().map(SRight::getId).forEach(s->ids.add(s));
        parentIds.stream().filter(t-> !ids.contains(t)).forEach(s-> haveNotIds.add(s));
        //添加不存在的parentId
        list.stream().filter(t-> haveNotIds.contains(t.getId())).forEach(s-> userRightList.add(s));
        return  ResultGenerator.genSuccessResult(userRightList);
    }
    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SUserVo> getList(Map<String,Object> param)
    {
        return  sUserMapper.selectList(param);
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
        List<SUserVo> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
}
