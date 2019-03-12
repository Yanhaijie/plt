package com.supermoney.loan.market.service.impl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.market.entity.SMerchantOrder;
import com.supermoney.loan.market.entity.requestVo.CreditRequestVo;
import com.supermoney.loan.market.entity.requestVo.DemandRequestVo;
import com.supermoney.loan.market.entity.requestVo.OrderRequestVo;
import com.supermoney.loan.market.entity.respondVo.CreditRespondVo;
import com.supermoney.loan.market.entity.respondVo.DemandRespondVo;
import com.supermoney.loan.market.service.SMerchantOrderService;
import com.supermoney.loan.market.service.SMerchantUserService;
import com.supermoney.loan.market.utils.ElasticsearchUtil;
import com.supermoney.loan.market.utils.Result;
import com.supermoney.loan.market.utils.ResultCode;
import com.supermoney.loan.market.utils.ResultGenerator;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import sun.rmi.runtime.Log;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class MerchantOrderBussService {

    private static final Logger logger = LoggerFactory.getLogger(MerchantOrderBussService.class);

    @Value("${conf.fastdfsHost}")
    private String fileBaseHost;

    @Resource
    private SMerchantOrderService sMerchantOrderService;

    @Resource
    private SMerchantUserService sMerchantUserService;

    @Resource
    private ElasticsearchUtil elasticsearchUtil;

    @Resource
    private RedisTemplate redisTemplate;

    /**
     * 订单获取
     * @param requestVo
     * @return
     */
    public Result getDemand(DemandRequestVo requestVo){

        Map<String,String> resultSet = redisTemplate.opsForHash().entries("merchantOrderLastPull");
        if (requestVo.getStart_time() == null && requestVo.getEnd_time() == null){
            //默认取一周前
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            calendar.set(Calendar.DATE, -7);
            Long timestamp = calendar.getTime().getTime();
            if (resultSet != null){
                timestamp = resultSet.get(requestVo.getMerchantId()) == null ?  timestamp: Long.parseLong(resultSet.get(requestVo.getMerchantId()));
            }
            Date date = new Date();
            date.setTime(timestamp);
            requestVo.setStart_time(date);
        }

        List<DemandRespondVo> list = sMerchantOrderService.selectDemandOrder(requestVo);
        if (list != null && list.size() > 0){
            sMerchantOrderService.updateDemandOrder(list);
        }
        if (resultSet == null){
            resultSet = new HashMap<String,String>();
        }
        if (resultSet.get(requestVo.getMerchantId()) == null){
            if (requestVo.getEnd_time() != null){
                resultSet.put(requestVo.getMerchantId(),requestVo.getEnd_time().getTime() + "");
            }
            else {
                resultSet.put(requestVo.getMerchantId(),new Date().getTime() + "");
            }
        }
        else {
            if (requestVo.getEnd_time() != null) {
                if (requestVo.getEnd_time().getTime() > Long.parseLong(resultSet.get(requestVo.getMerchantId()))) {
                    resultSet.put(requestVo.getMerchantId(), requestVo.getEnd_time().getTime() + "");
                }
            }
            else {
                resultSet.put(requestVo.getMerchantId(),new Date().getTime() + "");
            }
        }

        if(!requestVo.getMerchantId().equals("992bdbe4c30048bda168d773908cb83b")){
            redisTemplate.opsForHash().putAll("merchantOrderLastPull",resultSet);
        }

        return  ResultGenerator.genSuccessResult(list);
    }

    /**
     * 征信资料
     * @param requestVo
     * @return
     */
    public Result getCredit(CreditRequestVo requestVo){
        SMerchantOrder sMerchantOrder = sMerchantOrderService.selectOrderByOrderId(requestVo.getNeed_id());
        logger.info("getCredit:"+requestVo.getNeed_id());
        if(sMerchantOrder==null){
            return ResultGenerator.genResult(ResultCode.ORDER_ID_ERROR.code,ResultCode.ORDER_ID_ERROR.msg);
        }

        List<CreditRespondVo> list = sMerchantOrderService.selectUserCreditInfo(requestVo.getNeed_id());
        if(list.size()<1){
            return  ResultGenerator.genSuccessResult();
        }

        CreditRespondVo creditRespondVo = list.get(0);

        if (StringUtils.isNotBlank(creditRespondVo.getId_img_front())){
            creditRespondVo.setId_img_front(fileBaseHost + creditRespondVo.getId_img_front());
        }
        if (StringUtils.isNotBlank(creditRespondVo.getId_img_hold())){
            creditRespondVo.setId_img_hold(fileBaseHost + creditRespondVo.getId_img_hold());
        }
        //工作证明处理
        Map<String, Object> workMap = sMerchantOrderService.selectUserWorkInfo(sMerchantOrder.getUserId());
        if (workMap.get("work_card_img") != null && StringUtils.isNotBlank(workMap.get("work_card_img").toString())){
            creditRespondVo.setImg_job(fileBaseHost + workMap.get("work_card_img").toString());
        }
        else if (workMap.get("salary_cert_img") != null && StringUtils.isNotBlank(workMap.get("salary_cert_img").toString())){
            creditRespondVo.setImg_job(fileBaseHost + workMap.get("salary_cert_img").toString());
        }
        else if (workMap.get("credit_card_img") != null && StringUtils.isNotBlank(workMap.get("credit_card_img").toString())){
            creditRespondVo.setImg_job(fileBaseHost + workMap.get("credit_card_img").toString());
        }
        else if (workMap.get("bank_statement_img") != null && StringUtils.isNotBlank(workMap.get("bank_statement_img").toString())){
            creditRespondVo.setImg_job(fileBaseHost + workMap.get("bank_statement_img").toString());
        }
        else {
            creditRespondVo.setImg_job("");
        }
        //生日处理
        //todo

        logger.info("getCredit-SUCCESS");
        return ResultGenerator.genSuccessResult(creditRespondVo);
    }

    /**
     * 采集信息
     * @param requestVo
     * @return
     */
    public Result getCollection(CreditRequestVo requestVo){
        SMerchantOrder sMerchantOrder = sMerchantOrderService.selectOrderByOrderId(requestVo.getNeed_id());
        if (sMerchantOrder != null){

            try {
                Map<String, Object> resultMap = new HashMap<>();
                Map<String, Object> valueMap;

                DateFormat formatFrom1 = new SimpleDateFormat("MMM dd,yyyy KK:mm:ss aa", Locale.ENGLISH);
                DateFormat formatFrom2 = new SimpleDateFormat("MMM dd,yyyy KK:mm:ss", Locale.ENGLISH);
                DateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                String mobileJson = elasticsearchUtil.getSourceAll("device_mobile","mobile",new String[]{"username","number","name"},"username",sMerchantOrder.getUserName());
                List<Map<String, Object>> mobileJsonList = JSONObject.parseObject(mobileJson,List.class);
                List<Map<String, Object>> mobileJsonResultList = new ArrayList<>();
                for (Map map :mobileJsonList) {
                    valueMap = new HashMap<>();
                    valueMap.put("user_phone", map.get("username") == null ? "" : map.get("username"));
                    valueMap.put("book_name", map.get("name") == null ? "" : map.get("name"));
                    valueMap.put("book_number", map.get("number") == null ? "" : map.get("number"));
                    mobileJsonResultList.add(valueMap);
                }
                resultMap.put("book",mobileJsonResultList);

                String callrecordJson = elasticsearchUtil.getSourceAll("device_callrecord","callrecord",new String[]{"username","number","time","type"},"username",sMerchantOrder.getUserName());
                List<Map<String, Object>> callrecordJsonList = JSONObject.parseObject(callrecordJson,List.class);
                List<Map<String, Object>> callrecordJsonResultList = new ArrayList<>();

                for (Map map :callrecordJsonList) {
                    valueMap = new HashMap<>();
                    valueMap.put("user_phone", map.get("username") == null ? "" : map.get("username"));
                    valueMap.put("call_number", map.get("number") == null ? "" : map.get("number"));
                    valueMap.put("call_time", patterTime(map.get("time"), formatFrom1, formatFrom2, formatTo));
                    valueMap.put("call_type", map.get("type") == null ? "" : map.get("type"));
                    callrecordJsonResultList.add(valueMap);
                }
                resultMap.put("call",callrecordJsonResultList);

                String massageJson = elasticsearchUtil.getSourceAll("device_massage","massage",new String[]{"username","number","messaage","time","dateSent","seen","read"},"username",sMerchantOrder.getUserName());
                List<Map<String, Object>> massageJsonList = JSONObject.parseObject(massageJson,List.class);
                List<Map<String, Object>> massageJsonResultList = new ArrayList<>();
                for (Map map :massageJsonList) {
                    valueMap = new HashMap<>();
                    valueMap.put("user_phone", map.get("username") == null ? "" : map.get("username"));
                    valueMap.put("msg_number", map.get("number") == null ? "" : map.get("number"));
                    valueMap.put("msg_content", map.get("messaage") == null ? "" : map.get("messaage"));
                    valueMap.put("msg_got_time", patterTime(map.get("time"), formatFrom1, formatFrom2, formatTo));
                    valueMap.put("msg_send_time",patterTime(map.get("dateSent"), formatFrom1, formatFrom2, formatTo));
                    valueMap.put("msg_is_view", map.get("seen") == null ? "" : map.get("seen"));
                    valueMap.put("msg_is_read", map.get("read") == null ? "" : map.get("read"));
                    massageJsonResultList.add(valueMap);
                }
                resultMap.put("msg",massageJsonResultList);

                String locationJson = elasticsearchUtil.getSourceAll("device_location","location",new String[]{"username","longitude","latitude"},"username",sMerchantOrder.getUserName());
                List<Map<String, Object>> locationJsonList = JSONObject.parseObject(locationJson,List.class);
                List<Map<String, Object>> locationJsonResultList = new ArrayList<>();
                for (Map map :locationJsonList) {
                    valueMap = new HashMap<>();
                    valueMap.put("user_phone", map.get("username") == null ? "" : map.get("username"));
                    valueMap.put("longitude", map.get("longitude") == null ? "" : map.get("longitude"));
                    valueMap.put("latitude", map.get("latitude") == null ? "" : map.get("latitude"));
                    locationJsonResultList.add(valueMap);
                }
                resultMap.put("location",locationJsonResultList);

                String appJson = elasticsearchUtil.getSourceAll("device_app","app",new String[]{"username","packageName","label"},"username",sMerchantOrder.getUserName());
                List<Map<String, Object>> appJsonList = JSONObject.parseObject(appJson,List.class);
                List<Map<String, Object>> appJsonResultList = new ArrayList<>();
                for (Map map :appJsonList) {
                    valueMap = new HashMap<>();
                    valueMap.put("user_phone", map.get("username") == null ? "" : map.get("username"));
                    valueMap.put("app_name", map.get("packageName") == null ? "" : map.get("packageName"));
                    valueMap.put("app_package", map.get("label") == null ? "" : map.get("label"));
                    appJsonResultList.add(valueMap);
                }
                resultMap.put("app",appJsonResultList);

                return ResultGenerator.genSuccessResult(resultMap);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        else {
            return ResultGenerator.genResult(ResultCode.ORDER_ID_ERROR.code,ResultCode.ORDER_ID_ERROR.msg);
        }

        return ResultGenerator.genResult(ResultCode.INTERNAL_SERVER_ERROR.code,ResultCode.INTERNAL_SERVER_ERROR.msg);
    }

    /**
     * update order
     * @param requestVo
     * @return
     */
    public Result orderupdate(OrderRequestVo requestVo){
        if(StringUtils.isBlank(requestVo.getNeed_id())){
            return  ResultGenerator.genResult(ResultCode.ORDER_ID_EMPTY.code,ResultCode.ORDER_ID_EMPTY.msg);
        }

        Integer[] statusAry=new Integer[]{1,2,3,4,5,6,100};
        if(!Arrays.asList(statusAry).contains(requestVo.getStatus())){
           return ResultGenerator.genResult(ResultCode.ORDER_STATE_ERROR.code,ResultCode.ORDER_STATE_ERROR.msg);
        }

        SMerchantOrder merchantOrder = sMerchantOrderService.selectOrderByOrderId(requestVo.getNeed_id());
        if (merchantOrder == null){
            return ResultGenerator.genResult(ResultCode.ORDER_ID_ERROR.code,ResultCode.ORDER_ID_ERROR.msg);
        }

        merchantOrder.setOrderStatus(requestVo.getStatus());
//      merchantOrder.setLoanAmount(requestVo.getLoan_amount() == null ? merchantOrder.getLoanAmount(): requestVo.getLoan_amount());
        merchantOrder.setInterestAmount(requestVo.getInterest_amount() == null ? merchantOrder.getInterestAmount(): requestVo.getInterest_amount());
        merchantOrder.setOverdueAmount(requestVo.getOverdue_amount() == null ? merchantOrder.getOverdueAmount(): requestVo.getOverdue_amount());
        merchantOrder.setOverdueLimit(requestVo.getOverdue_day() == null ? merchantOrder.getOverdueLimit(): requestVo.getOverdue_day());
        merchantOrder.setPaycackAccountId(requestVo.getPayback_account_id());
        merchantOrder.setFeeAmount(requestVo.getFee_amount() == null ? merchantOrder.getFeeAmount(): requestVo.getFee_amount());
        merchantOrder.setGotAmount(requestVo.getGot_amount() == null ? merchantOrder.getGotAmount(): requestVo.getGot_amount());
        merchantOrder.setWaitRepayAmount(requestVo.getWait_repay_amount() == null ? merchantOrder.getWaitRepayAmount(): requestVo.getWait_repay_amount());
        merchantOrder.setRepaymentedAmount(requestVo.getRepaymented_amount() == null ? merchantOrder.getRepaymentedAmount(): requestVo.getRepaymented_amount());

        sMerchantOrderService.update(merchantOrder);

        return ResultGenerator.genSuccessResult();
    }

    /**
     *
     * @param timeString
     * @param formatFrom1
     * @param formatFrom2
     * @param formatTo
     * @return
     */
    public String patterTime(Object timeString ,DateFormat formatFrom1 ,DateFormat formatFrom2 ,DateFormat formatTo){
        Date time;
        String patterTime;

        if (timeString == null || timeString.toString().length() == 0){
            patterTime = "";
        } else {
            patterTime = (String) timeString;
            try {
                if (patterTime.contains("AM") || patterTime.contains("PM")) {
                    time = formatFrom1.parse(patterTime);
                    patterTime = formatTo.format(time);
                } else if (patterTime.contains(",")) {
                    time = formatFrom2.parse(patterTime);
                    patterTime = formatTo.format(time);
                }else  if(patterTime.matches("[0-9]+")){
                    patterTime=formatTo.format(new Date(Long.valueOf(patterTime)));
                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return patterTime;
    }
}
