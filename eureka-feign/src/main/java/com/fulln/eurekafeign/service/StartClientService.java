package com.fulln.eurekafeign.service;

import com.fulln.eurekafeign.service.fusingServiceImpl.fusingBasicClient;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//primary = true  当使用fallbackfactory属性的时候可以使用。 捕获provide传递过来的的错误
@FeignClient(value = "eureka-provider",fallback= fusingBasicClient.class)
public interface StartClientService {

    @GetMapping("/getAge?age={age}")
    //consumes = "application/json"  用来传递json实例
    String getAge(@PathVariable("age")Integer age);

    @GetMapping("/getsum")
    Integer getSex();

}
