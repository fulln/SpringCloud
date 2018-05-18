package com.fulln.apiyoudao;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class ApiYoudaoApplication {


    public static void main(String[] args) {
        SpringApplication.run(ApiYoudaoApplication.class, args);
    }


}
