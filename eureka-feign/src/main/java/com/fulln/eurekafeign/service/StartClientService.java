package com.fulln.eurekafeign.service;

import com.fulln.eurekafeign.service.fusingServiceImpl.fusingBasicClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;


@FeignClient(value = "eureka-provider",primary = false,fallback = fusingBasicClient.class)
//短路回退 Feign Hystrix回退
public interface StartClientService {

    @GetMapping("/getAge?age={age}")
    //consumes = "application/json"  用来传递json实例
    String getAge(@PathVariable("age")Integer age);

    @GetMapping("/getsum")
    Integer getSex();

}
