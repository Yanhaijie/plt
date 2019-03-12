package com.supermoney.loan.mg.service;
import com.github.pagehelper.PageInfo;
import com.supermoney.loan.mg.entity.SMessage;
import com.supermoney.loan.mg.entity.vo.SMessageVo;
import com.supermoney.loan.mg.utils.Result;
import com.supermoney.loan.mg.utils.Service;

import java.util.List;
import java.util.Map;


/**
 * Created by wenyuhao on 2018/04/04.
 */
public interface SMessageService extends Service<SMessage> {
    /**
     * 查询
     * @param param
     * @return
     */
    public List<SMessageVo> getList(Map<String,Object> param);
    /**
     * 分页获取
     * @param page
     * @param size
     * @param param
     * @return
     */
    public PageInfo getByPage(int page, int size, Map<String,Object> param);

    /**
     * 推送消息
     * @param messageId
     * @return
     */
    public Result sendMessage(Integer messageId);

    /**
     * 发送普通站内消息
     * @param userId
     * @param title
     * @param content
     */
    public void  sendNomalMessage(Integer userId,String title,String content);

}
