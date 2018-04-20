package com.fulln.eureka_slaver1;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaSlaver1Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaSlaver1Application.class, args);
	}
}
