package com.fulln.eurekafeign.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("eureka-provider-pip")
public interface StartClientService {

    @GetMapping("/getAge")
    String getAge(Integer age);

}
