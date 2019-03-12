package com.supermoney.loan.market;

import com.supermoney.loan.market.SuperMoneyLoanMarketApplication;
import org.apache.commons.codec.binary.Base64;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;


public class NomalTest {
    /**
     *
     * @param args
     */
    public static void main(String[] args) {

        int[] ary=new int []{4,3,5,9,11,6,2,1};

    }

    public static boolean stoneGame(int[] piles){
        return true;
    }

    public  static  void  timeTest(){
        DateFormat formatTo = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String patterTime="1526963726000";
        if(patterTime.matches("[0-9]+")){
            patterTime=formatTo.format(new Date(Long.valueOf(patterTime)));
        }
    }

    public  static  void  basicTest(){
        String scretKey="xnd_development_PYCCfL0hhOOmxsFteeBOEmKTZtX29YZ5lSbm+Rxi+GzW+7GhDwB1gg==";
        String sign=enSign(scretKey);
        System.out.println("sign:"+sign);
        String deScretKey=deSign(sign);
        System.out.println("scretkey:"+deScretKey);
    }

    public static String enSign(String scretkey) {
        try {
            String str = scretkey + ":";
            byte[] encodeBase64 = Base64.encodeBase64(str.getBytes("UTF-8"));
            String userAgent = "Basic " + new String(encodeBase64);
            return  userAgent;
        }catch (Exception ex){
            return  "";
        }
    }

    public  static  String deSign(String sign){
        String  authorization=sign.replace("Basic","").replaceAll(" ","");
        String decodeStr=new String(Base64.decodeBase64(authorization));
        decodeStr=decodeStr.substring(0,decodeStr.length()-1);
        return decodeStr;
    }

    public static  String token(String pre){
        try {
            Thread.sleep(30);
            String token= UUID.randomUUID().toString().replaceAll("-","");
            String dateString =Long.toString(System.currentTimeMillis());
            token=token+dateString;
            System.out.println("b:"+token);
            byte[] encodeBase64 =Base64.encodeBase64(token.getBytes("UTF-8"));
            String result = pre+"_" + new String(encodeBase64);
            System.out.println("a:"+result);
            return result;
        }catch (Exception ex){
             ex.printStackTrace();
             throw new RuntimeException(ex);
        }
    }

}
