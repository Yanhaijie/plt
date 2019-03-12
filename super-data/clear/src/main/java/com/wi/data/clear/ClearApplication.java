package com.wi.data.clear;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication
@EnableAsync
public class ClearApplication {

	public static void main(String[] args) {
		SpringApplication.run(ClearApplication.class, args);
	}
}
