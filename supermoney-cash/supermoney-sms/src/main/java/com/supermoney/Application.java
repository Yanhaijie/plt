package com.supermoney;

import com.supermoney.sms.Constants;
import com.supermoney.sms.SmsClient;

public class Application {

	public static void main(String[] args) {
			SmsClient sc=new SmsClient();
		 	boolean SC=sc.sendMsg("639668951527","【无限云】您的验证码为：16151999，请在一分钟内使用，谢谢！", Constants.PRODUCT_PAASOO);
        System.out.printf(String.valueOf(SC));
    }
}
