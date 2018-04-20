package com.fulln.pips;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class PipsApplication {

	public static void main(String[] args) {
		SpringApplication.run(PipsApplication.class, args);
	}
}
