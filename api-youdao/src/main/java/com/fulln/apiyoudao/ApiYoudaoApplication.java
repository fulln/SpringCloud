package com.fulln.apiyoudao;

import com.fulln.apiyoudao.config.ReadPorpertis;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class ApiYoudaoApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiYoudaoApplication.class, args);

        ReadPorpertis r= new ReadPorpertis();
        r.writeToFile();

    }

}
