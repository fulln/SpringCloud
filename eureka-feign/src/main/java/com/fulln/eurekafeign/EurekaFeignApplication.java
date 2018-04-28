package com.fulln.eurekafeign;

import com.fulln.eurekafeign.service.StartClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication(exclude =StartClientService.class)
@EnableFeignClients
@EnableEurekaClient
public class EurekaFeignApplication {

	public static void main(String[] args) {

		SpringApplication.run(EurekaFeignApplication.class, args);
	}
}
