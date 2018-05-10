package com.fulln.eurekafeign;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
@EnableEurekaClient
@EnableHystrix// 启动断路器，如果要监控hystrix的流必须开启此注解，即使fegin已经通过属性
@EnableHystrixDashboard//启动dashboard
public class EurekaFeignApplication {

	public static void main(String[] args) {

		SpringApplication.run(EurekaFeignApplication.class, args);
	}
}
