package com.supermoney.loan.market;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.supermoney.loan.market.encryption.AES;
import com.supermoney.loan.market.encryption.EncryUtil;
import com.supermoney.loan.market.encryption.RSA;
import com.supermoney.loan.market.encryption.RandomUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import java.util.TreeMap;

@SpringBootApplication
@EnableDiscoveryClient
public class SuperMoneyLoanMarketApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperMoneyLoanMarketApplication.class, args);
	}
}
