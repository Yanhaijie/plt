package com.supermoney.loan.market;

import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.market.utils.NIKCheckUtils;
import com.supermoney.loan.market.utils.Result;
import com.supermoney.loan.market.utils.ResultCode;
import org.apache.commons.codec.binary.Base64;
import org.junit.Test;
import org.springframework.http.*;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class MarketBussTest {
    //公钥
    private  String public_key="";
    //私钥
    private  String private_key="";
    //秘钥
    //testAPI:SuperSecret_ODZkOTE5ZmMyOWQ5NDcxYjhmYzljN2ZiNjZlNzc5MTkxNTM0MjEzMzIwMTYz
    //supeAPI: SuperSecret_YTM0ZGE4YmRmOTg0NDllODkxYTM1MTQ5ZTAyY2VhODIxNTQwMjAxMTYyODk2
    // API1 : "SuperSecret_NDhlMWE1ZWJjZDdkNDY5Yjg0Mzg3MjNkZjM1YjlkYTkxNTQwMTk3NzE3NjE3";

    private  String secret_key="SuperSecret_YTM0ZGE4YmRmOTg0NDllODkxYTM1MTQ5ZTAyY2VhODIxNTQwMjAxMTYyODk2";
   // private  String secret_key="SuperSecret_YWY5ZGU3NTY0NjY3NGI0ZjkyMjBkYjE5NzhmNmRlOTMxNTMzNzk3MzU0ODUy";

    //校验TOKEN
    private  String validationo_token="";
    //API-HOST
    private String HOST="https://www.supermoneyshop.com/spa/api/";
    //private String HOST="http://127.0.0.1:8000/spa/api/";
    /**
     * 获取借款需求订单
     */
    @Test
    public  void  getLoanOrder(){
        String URL=HOST+"demand";
        Map<String,String> reqData=new HashMap<>();
        reqData.put("start_time","");
        reqData.put("end_time","");
        reqData.put("start_amount","");
        reqData.put("end_ amount","");
        String result=apiPost(URL,reqData);
        System.out.println(result);
    }


    /**
     * 获取征信资料
     */
    @Test
    public  void  getCredit(){
        String URL=HOST+"credit";
        Map<String,String> reqData=new HashMap<>();
        reqData.put("need_id","1320180827073125467");
        String result=apiPost(URL,reqData);
        System.out.println(result);
     }

    /**
     * 获取采集数据
     */
    @Test
    public  void  getCollection(){
        String URL=HOST+"collection";
        Map<String,String> reqData=new HashMap<>();
        reqData.put("need_id","1320180827073125467");
        String result=apiPost(URL,reqData);
        System.out.println(result);
    }

    /**
     * 校验nik,获取部分个人信息
     */
    @Test
    public  void  nikcheck(){
        String URL= HOST+"nik/check";
        Map<String,String> reqData=new HashMap<>();
        reqData.put("name","Ellen Yapyteri");
        reqData.put("nik","1271205802980001");
        String result=apiPost(URL,reqData);
        System.out.println(result);
    }
    @Test
    public  void  nikcheck2(){
        Result rs1= NIKCheckUtils.checkNIK("3202160902890006");
        Result rs2= NIKCheckUtils.checkNIK("3516010311610002");
        Result rs3= NIKCheckUtils.checkNIK("1171070201920002");

    }

    /**
     * 订单更新
     */
    @Test
    public  void  updateOrder(){
        String URL=HOST+"orderupdate";
        Map<String,String> reqData=new HashMap<>();
        reqData.put("need_id","1320180827073125467");
        reqData.put("status","2");
        reqData.put("payback_account_id","");
       reqData.put("loan_amount","");
        reqData.put("got_amount","");
        reqData.put("interest_amount","");
        reqData.put("fee_amount","");
        reqData.put("overdue_amount","");
        reqData.put("overdue_day","");
        reqData.put("wait_repay_amount","");
        reqData.put("repaymented_amount","");
        String result=apiPost(URL,reqData);
        System.out.println(result);
    }

    /**
     * API 请求
     * @param url
     * @param reqData
     * @return
     */
    public String apiPost(String url, Map<String,String> reqData){
        try {
            RestTemplate client = new RestTemplate();
            HttpHeaders headers = new HttpHeaders();

            /**
             * 请求头
             */
            //Authorization
            headers.add("APISIGN", scretkeyToApiSign(secret_key));
            headers.add("S-PUNIQUE-KEY", UUID.randomUUID().toString().replace("-", ""));
            headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
            /**
             * 添加参数
             */
            MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
            for(String key:reqData.keySet()){
                params.add(key,reqData.get(key)==null? "":reqData.get(key).toString());
            }
            /**
             * 请求
             */
            //postForObject
            HttpEntity<MultiValueMap<String, String>> requestEntity = new HttpEntity<MultiValueMap<String, String>>(params, headers);
            ResponseEntity<String> response = client.exchange(url, HttpMethod.POST, requestEntity, String.class);
            return response.getBody();

        }catch (Exception ex){
            ex.printStackTrace();
        }
        return  "";
    }

    /**
     * 秘钥转换为签名请求头参数
     * @param scretkey
     * @return
     */
    public static String scretkeyToApiSign(String scretkey) {
        try {
            String str = scretkey + ":";
            byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
            String userAgent = "Basic " + new String(encodeBase64);
            return  userAgent;
        }catch (Exception ex){
            ex.printStackTrace();
            return  "";
        }
    }

    /**
     * 检查加密字项是否正确
     */
    @Test
    public  void checkItemTest(){

        String itemName1="Keep LilDewi";
        String mosaicName1="Ke******Dewi";
        System.out.println("check:"+checkItem(itemName1,mosaicName1));

        String itemName2="Kely JhonRoler Hana";
        String mosaicName2="Kely******Hana";
        System.out.println("check:"+checkItem(itemName2,mosaicName2));

        String itemAddress1="Jl.Address Holosan bar daun";
        String mosaicAddress1="Jl.**********bar daun";
        System.out.println("check:"+checkItem(itemAddress1,mosaicAddress1));

        String itemAddress2="Pool Uang urz To daluo";
        String mosaicAddress2="Pool**********To daluo";
        System.out.println("check:"+checkItem(itemAddress2,mosaicAddress2));

    }
    /**
     * 检查加密项是否正确
     * @param item
     * @param mosaicItem
     * @return
     */
    public static boolean checkItem(String item,String mosaicItem ){
        String [] ary=item.split("\\*");
        String []  splitItems= new String[]{ ary[0],ary[ary.length-1] };
        return   item.substring(0,splitItems[0].length()).equals(splitItems[0]) &&
                item.substring(item.length()-splitItems[1].length(),item.length()).equals(splitItems[1]) ;
    }

}
