package com.supermoney.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
@MapperScan("com.supermoney.loan.dao")
public class SuperMoneyScheduleApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperMoneyScheduleApplication.class, args);
	}
}
