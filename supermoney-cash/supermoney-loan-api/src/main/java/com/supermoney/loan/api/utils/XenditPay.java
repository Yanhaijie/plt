package com.supermoney.loan.api.utils;

import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import sun.misc.BASE64Encoder;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

/**
 * Created by tangwenchi on 2018/1/14.
 */
public class XenditPay {

    @Autowired
    private  AppProperties appProperties;

    private String STRUAL = "https://api.xendit.co/disbursements";

    private Integer connectTimeoutMs = 8000;

    private Integer readTimeoutMs = 10000;

    public   Map<String,String> disbursementErrors=getDisbursementErrors();

    public   Map<String,String> nameErrors=getDisbursementErrors();

    /**
     * 打款错误信息
     * 400
     * @return
     */
    public  Map<String,String> getDisbursementErrors(){
        Map<String,String> errors=new HashMap<>(Constants.App.MAP_MIN_SIZE);
        errors.put("API_VALIDATION_ERROR","nputs are failing validation. The errors field contains details about which fields are violating validation.");
        errors.put("INVALID_JSON_FORMAT ","The request body is not a valid JSON format. ");
        errors.put("DISBURSEMENT _DESCRIPTION_NOT _FOUND_ERROR ","Disbursement description is not set in Dashboard > Configuration > Disbursement. Add a default description before retrying");
        errors.put("DIRECT_DISBURSEMENT _BALANCE_INSUFFICIENT _ERROR ","Not enough balance to disburse. Add more balance before retrying. ");
        errors.put("DUPLICATE_TRANSACTION _ERROR ","Idempotency key has been used before. Use a unique idempotency key and try again. ");
        errors.put("BANK_CODE_NOT _SUPPORTED_ERROR","Destination bank code is not supported. ");
        return  errors;
    }

    /**
     * 名称校验错误
     * 400
     * @return
     */
    public Map<String,String> getNameErrors(){
        Map<String,String> errors=new HashMap<>(Constants.App.MAP_MIN_SIZE);
        errors.put("MAX_RETRY_TIMES _EXCEED_ERROR ","Our system has retried the request several times but still failed because of an unknown error response from the bank. ");
        errors.put("RECIPIENT_NOT_FOUND _ERROR ","There’s no bank recipient with this account number. ");
        errors.put("UNSUPPORTED_BANK _CODE_ERROR ","Destination bank is not supported, request is using the wrong bank code. ");
        return errors;
    }


    /**
     * 请求不需要证书
     *
     * @param
     * @param reqData Map<String,Object> map=new HashMap();
     *                map.put("external_id",唯一订单号)
     *                map.put("order_id","");//
     *                map.put("bank_code","目的银行的代码");
     *                map.put("account_holder_name"," 目标银行帐户所有者的名称");
     *                map.put("account_number","string 目标银行账户号码");
     *                map.put("description","说明与支付一起发送");
     *                map.put("amount","金额支付")
     *                map.put("amount","金额支付")
     * @return
     * @throws Exception
     */
    public String requestWithoutCert(Map<String, String> reqData) throws Exception {
        String UTF8 = "UTF-8";
        String reqBody = mapToString(reqData);
        URL httpUrl = new URL(STRUAL);
        HttpURLConnection httpURLConnection = (HttpURLConnection) httpUrl.openConnection();
        httpURLConnection.setDoOutput(true);
        httpURLConnection.setRequestMethod("POST");
        httpURLConnection.setConnectTimeout(connectTimeoutMs);
        httpURLConnection.setReadTimeout(readTimeoutMs);
        /**添加头文件**/
        httpURLConnection.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
        /**设置user-agent秘钥**/
        httpURLConnection.setRequestProperty("user-agent", jdkBase64Encoder(appProperties.getXenditSecretKey()));
        httpURLConnection.setRequestProperty("X-IDEMPOTENCY-KEY", reqData.get("order_id"));

        httpURLConnection.connect();
        OutputStream outputStream = httpURLConnection.getOutputStream();
        outputStream.write(reqBody.getBytes(UTF8));


        //获取内容
        InputStream inputStream = httpURLConnection.getInputStream();
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream, UTF8));
        final StringBuffer stringBuffer = new StringBuffer();
        String line = null;
        while ((line = bufferedReader.readLine()) != null) {
            stringBuffer.append(line).append("\n");
        }
        String resp = stringBuffer.toString();
        if (stringBuffer != null) {
            try {
                bufferedReader.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        if (outputStream != null) {
            try {
                outputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return resp;
    }


    public static String jdkBase64Encoder(String str) {
        BASE64Encoder encoder = new BASE64Encoder();
        String result;
        try {
            result = encoder.encodeBuffer(str.getBytes("UTF-8"));
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * map转post值
     *
     * @param reqData
     * @return
     */
    private static String mapToString(Map<String, String> reqData) {
        Set<String> keySet = reqData.keySet();
        String[] keyArray = keySet.toArray(new String[keySet.size()]);
        Arrays.sort(keyArray);
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String k : keyArray) {
            if (reqData.get(k).trim().length() > 0) // 参数值为空，则不参与签名
                sb.append('"' + k + '"').append(":").append('"' + reqData.get(k).trim() + '"').append(",");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append("}");
        return sb.toString();
    }


    /**
     * 生成订单号,年月日+会员id后4位+6位随机数
     *
     * @return
     */
    String getSn(int memberId) {
        DateTime now = new DateTime().dayOfWeek().roundFloorCopy();
        String idString = String.format("%04d", memberId);
        idString = idString.substring(idString.length() - 4);
        String snPrefix = now.toString("yyMMdd") + idString;
        Map<String, Object> map = new HashMap<>();
        map.put("startDate", now.toDate());
        map.put("endDate", now.plusDays(1).toDate());
        map.put("sn", snPrefix + "%");
//        List<String> dbSnList = orderMapper.selects("selectSn", map);
        Random random = new Random();
        String sn = snPrefix + String.format("%06d", random.nextInt(999999));
//        if (dbSnList.isEmpty()) {
        return sn;
//        }
//        while (true) {
//            Boolean isExists = false;
//            sn = snPrefix + String.format("%06d", random.nextInt(999999));
//            for (String dbSn : dbSnList) {
//                if (dbSn.equals(sn)) {
//                    isExists = true;
//                    break;
//                }
//            }
//            if (!isExists) {
//                return sn;
//            }
//        }
    }


    // 支付完成后需要编写一个接口接收回调的参数  如下

    //Map<String,Object> obj=new HashMap<>();
    // obj.put("is_instant",true);//指示是否立即支付
    // obj.put("user_id",userId);//订单下完成后,会返回一个userId回来
    // obj.put("external_id","getsn");//下订单的时候产生的订单号
    // obj.put("amount","");// 支付金额
    // obj.out("bank_code",""); // 银行编号
    // obj.put("account_holder_name","");//账户持有人真实姓名
    // obj.put("disbursement_description","");// 支付描述
    // obj.put("status","支付状态");// COMPLETED  资金充足可以支付
    //                              // FAILED   支付失败

    // 可选参数
    //obj.put("failure_code","") //  INSUFFICIENT_BALANCE : 余额不够支付  INVALID_DESTINATION 目标账户复存在  DIRECT_DISBURSEMENT_FAILED 银行拒绝支付未明确原因
    //obj.put("ID","") //唯一支付Id


}
