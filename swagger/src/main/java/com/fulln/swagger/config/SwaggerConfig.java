package com.fulln.swagger.config;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import springfox.documentation.RequestHandler;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.ResponseMessageBuilder;
import springfox.documentation.schema.ModelRef;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.ResponseMessage;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

import java.util.ArrayList;
import java.util.Objects;
import java.util.function.Predicate;

/**
 * @program: SpringCloud
 * @description: 当前的swagger配置类
 * @author: fulln
 * @create: 2018-08-13 11:13
 * @Version： 0.0.1
 **/
@Configuration
@EnableSwagger2
public class SwaggerConfig {

    @Bean
    public Docket createRestApi() {

        ArrayList<ResponseMessage> responseMessages = new ArrayList<ResponseMessage>() {

            private static final long serialVersionUID = 5468514403433127836L;

            {
                add(new ResponseMessageBuilder().code(200).message("成功").build());
                add(new ResponseMessageBuilder().code(400).message("请求参数错误").responseModel(new ModelRef("Error")).build());
                add(new ResponseMessageBuilder().code(401).message("权限认证失败").responseModel(new ModelRef("Error")).build());
                add(new ResponseMessageBuilder().code(403).message("请求资源不可用").responseModel(new ModelRef("Error")).build());
                add(new ResponseMessageBuilder().code(404).message("请求资源不存在").responseModel(new ModelRef("Error")).build());
                add(new ResponseMessageBuilder().code(409).message("请求资源冲突").responseModel(new ModelRef("Error")).build());
                add(new ResponseMessageBuilder().code(415).message("请求格式错误").responseModel(new ModelRef("Error")).build());
                add(new ResponseMessageBuilder().code(423).message("请求资源被锁定").responseModel(new ModelRef("Error")).build());
                add(new ResponseMessageBuilder().code(500).message("服务器内部错误").responseModel(new ModelRef("Error")).build());
                add(new ResponseMessageBuilder().code(501).message("请求方法不存在").responseModel(new ModelRef("Error")).build());
                add(new ResponseMessageBuilder().code(503).message("服务暂时不可用").responseModel(new ModelRef("Error")).build());
                add(new ResponseMessageBuilder().code(-1).message("未知异常").responseModel(new ModelRef("Error")).build());
            }
        };
        Predicate<RequestHandler> predicate = (input) -> {
            Class<?> declaringClass = Objects.requireNonNull(input).getClass();
            // 被注解的类
            if (declaringClass.isAnnotationPresent(RestController.class)) return true;
            // 被注解的方法
            if (input.isAnnotatedWith(ResponseBody.class)) return true;

            return false;
        };

        return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo())

                .useDefaultResponseMessages(false)

                .select().apis(predicate::test)

                .build()
                .useDefaultResponseMessages(false)
                .globalResponseMessage(RequestMethod.GET, responseMessages)
                .globalResponseMessage(RequestMethod.POST, responseMessages)
                .globalResponseMessage(RequestMethod.PUT, responseMessages)
                .globalResponseMessage(RequestMethod.DELETE, responseMessages);
    }

    private ApiInfo apiInfo() {
        // 大标题
        return new ApiInfoBuilder().title("API接口服务")
                // 版本
                .version("1.0")
                .build();
    }

}