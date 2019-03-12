package com.supermoney.loan.api.service;

import com.supermoney.loan.api.entity.SMessageType;
import com.supermoney.loan.api.entity.SMessageUser;
import com.supermoney.loan.api.entity.vo.SMessageTypeVo;
import com.supermoney.loan.api.utils.Service;

import java.util.List;


/**
 * Created by wenyuhao on 2018/04/10.
 */
public interface SMessageTypeService extends Service<SMessageType> {
    /**
     * 查询所用带排序
     * @return
     */
    public List<SMessageType> findAllOrder();

    /**
     * 获取消息类别列表带是否未读信息
     * @param sMessageUser
     * @return
     * @throws Exception
     */
    public List<SMessageTypeVo> findMessageType(SMessageUser sMessageUser)throws Exception;

}
