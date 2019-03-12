package com.supermoney.loan.mg;

import com.supermoney.util.UploadUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {
		DataSourceAutoConfiguration.class
})
@EnableDiscoveryClient
@EnableTransactionManagement
@EnableFeignClients
public class SuperMoneyLoanManageApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperMoneyLoanManageApplication.class, args);
	}
}
