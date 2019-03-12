package com.supermoney.loan.market;

import com.supermoney.loan.market.encryption.RSA;
import org.apache.commons.lang.StringUtils;

import java.util.Map;
import java.util.TreeMap;

public class RsaTest {
    public static void main(String[] args)  throws Exception {
        printTest("10086");
        printTest("10086dfd");
        printTest("9454545");
        printTest("10dfd3486");
    }

    /**
     * 生成输出
     * @param keywork
     */
    public  static void  printTest(String keywork){
        try {
            Map<String,String> map= RSA.generateKeyPair(keywork);
            System.out.println("publicKey:"+map.get("publicKey")+" privateKey:"+map.get("privateKey")+" modulus:"+map.get("modulus"));
        }catch (Exception ex){
            ex.printStackTrace();
        }
    }


}
