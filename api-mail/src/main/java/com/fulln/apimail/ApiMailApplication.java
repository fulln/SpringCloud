package com.fulln.apimail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


/**
 * @Author: fulln
 * @Descriptio: 启动
 * @Date: 2018/6/29 0029
 */
@EnableSwagger2
@SpringBootApplication
public class ApiMailApplication {

	public static void main(String[] args) {
		SpringApplication.run(ApiMailApplication.class, args);
	}

}
