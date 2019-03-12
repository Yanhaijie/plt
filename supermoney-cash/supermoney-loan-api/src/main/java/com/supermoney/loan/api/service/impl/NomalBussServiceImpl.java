package com.supermoney.loan.api.service.impl;

import com.supermoney.loan.api.entity.vo.DialogMsgVo;
import com.supermoney.loan.api.service.NomalBussService;
import com.supermoney.loan.api.service.SLoanOrderService;
import com.supermoney.loan.api.service.SUserBindService;
import com.supermoney.loan.api.utils.Result;
import com.supermoney.loan.api.utils.ResultGenerator;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class NomalBussServiceImpl implements NomalBussService {

    @Resource
    private SLoanOrderService sLoanOrderService;

    @Resource
    private SUserBindService sUserBindService;

    /**
     * 获取弹窗消息
     * @param userId
     * @return7
     */
    @Override
    public Result getDialogMsg(Integer userId){

        List<DialogMsgVo> list=new ArrayList<>();
        if(userId.compareTo(1)<0) {
            return ResultGenerator.genSuccessResult(list);
        }
        //通知实名有订单的用户消息
        Map<String,Object> identityOrder=sLoanOrderService.hasIdentityOrder(userId);
        if(identityOrder!=null){
            Integer idNum= identityOrder.get("idNum")==null? 0:Integer.valueOf(identityOrder.get("idNum").toString());
            Integer orderNum= identityOrder.get("orderNum")==null? 0:Integer.valueOf(identityOrder.get("orderNum").toString());
            Integer bindCardNum= identityOrder.get("bindCardNum")==null? 0:Integer.valueOf(identityOrder.get("bindCardNum").toString());
            if(idNum.compareTo(3)==0 && orderNum.compareTo(0)>0 && bindCardNum.compareTo(0) == 0){
                DialogMsgVo identityVo=new DialogMsgVo();
                identityVo.setTitle("bindCard");
                identityVo.setContent("have order ,bind Card.");
                identityVo.setMsgType(1);
                list.add(identityVo);
            }
        }
        return ResultGenerator.genSuccessResult(list);
    }
}
