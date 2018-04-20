package com.fulln.eureka_slaver2;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@EnableEurekaServer
@SpringBootApplication
public class EurekaSlaver2Application {

	public static void main(String[] args) {
		SpringApplication.run(EurekaSlaver2Application.class, args);
	}
}
