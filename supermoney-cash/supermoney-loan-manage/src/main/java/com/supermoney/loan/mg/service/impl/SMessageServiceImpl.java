package com.supermoney.loan.mg.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.supermoney.loan.mg.dao.SMessageMapper;
import com.supermoney.loan.mg.dao.SMessageUserMapper;
import com.supermoney.loan.mg.entity.SMessage;
import com.supermoney.loan.mg.entity.SMessageUser;
import com.supermoney.loan.mg.entity.vo.SMessageVo;
import com.supermoney.loan.mg.service.SMessageService;
import com.supermoney.loan.mg.service.SReportUtilService;
import com.supermoney.loan.mg.utils.AbstractService;
import com.supermoney.loan.mg.utils.Constants;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.ResultGenerator;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/04.
 */
@Service
@Transactional
public class SMessageServiceImpl extends AbstractService<SMessage> implements SMessageService {
    @Resource
    private SMessageMapper sMessageMapper;
    @Resource
    private SMessageUserMapper sMessageUserMapper;
    @Resource
    private SReportUtilService sReportUtilService;

    /**
     * 查询应用
     * @param param
     * @return
     */
    public List<SMessageVo> getList(Map<String,Object> param)
    {
        return  sMessageMapper.selectList(param);
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
        List<SMessageVo> list =getList(param);
        PageInfo pageInfo = new PageInfo(list);
        return pageInfo;
    }
    public Result sendMessage(Integer messageId){
        Result result = null;
        //要推送的消息相关属性值
        SMessage sMessage = this.findById(messageId);
        Integer messageTypeId = sMessage.getMessageTypeId();
        String messageTitle = sMessage.getTitle();
        String messageContent = sMessage.getContent();
        String targetSql  = sMessage.getTargetSql();
        if(StringUtils.isEmpty(targetSql)){
            result = ResultGenerator.genFailResult("推送群体SQL为空，请刷新重新编辑！");
        }
        Map<String,Object> paramMap = Maps.newHashMap();
        paramMap.put("sql",targetSql);
        List<Map<String,Object>> resultList = sReportUtilService.executeSelect(paramMap);
        if(resultList == null || resultList.size() < 1){
            result = ResultGenerator.genFailResult("推送群体SQL关联的用户不存在！请检查其SQL语句！");
        }
        //批量插入推送记录
        List<SMessageUser> messageUserList = Lists.newArrayList();
        for(Map<String,Object> userMap : resultList){
            SMessageUser messageUser = new SMessageUser();
            messageUser.setUserId((Integer) userMap.get("id"));
            messageUser.setMessageId(messageId);
            messageUser.setMessageTitle(messageTitle);
            messageUser.setMessageContent(messageContent);
            messageUser.setMessageTypeId(messageTypeId);
            messageUser.setReadStatus(0);
            messageUser.setDeleteStatus(0);
            //TODO:待设置当前操作人
          //  messageUser.setOpt("");
            messageUserList.add(messageUser);
        }
        sMessageUserMapper.batchInsert(messageUserList);
        return ResultGenerator.genSuccessResult();
    }

    /**
     * 发送普通站内消息
     * @param userId
     * @param title
     * @param content
     */
    public void  sendNomalMessage(Integer userId,String title,String content){
        SMessageUser messageUser = new SMessageUser();
        messageUser.setUserId(userId);
        messageUser.setMessageTypeId(Constants.App.MESSAGE_NOMAL_TYPEID);
        messageUser.setMessageId(Constants.App.MESSAGE_NOMAL_ID);
        messageUser.setMessageTitle(title);
        messageUser.setMessageContent(content);
        messageUser.setReadStatus(0);
        messageUser.setDeleteStatus(0);
        sMessageUserMapper.insert(messageUser);
    }


}
