package com.supermoney.loan.api.service;

import com.supermoney.loan.api.utils.Result;

public interface NomalBussService {

    /**
     * 获取弹窗消息
     * @param userId
     * @return
     */
    public Result getDialogMsg(Integer userId);
}
