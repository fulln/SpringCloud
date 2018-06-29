package com.fulln.pips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@SpringBootApplication
public class PipsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PipsApplication.class, args);
	}
}
