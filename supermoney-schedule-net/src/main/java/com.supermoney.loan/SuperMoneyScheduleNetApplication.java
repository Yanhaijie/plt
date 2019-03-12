package com.supermoney.loan;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.mybatis.spring.annotation.MapperScan;
@SpringBootApplication
//@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class,HibernateJpaAutoConfiguration.class})
@MapperScan("com.supermoney.loan.dao")
//@MapperScan("com.winter.mapper")
public class SuperMoneyScheduleNetApplication {

	public static void main(String[] args) {
		SpringApplication.run(SuperMoneyScheduleNetApplication.class, args);
	}
}
