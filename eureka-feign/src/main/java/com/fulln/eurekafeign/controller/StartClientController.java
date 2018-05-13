package com.fulln.eurekafeign.controller;

import com.fulln.eurekafeign.service.StartClientService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class StartClientController {

    @Resource
    private StartClientService startClientService;

//    @HystrixCommand(fallbackMethod = "defaultStores")
    @GetMapping("/Counts")
    public String getAge(@RequestParam("age") Integer age){
        return startClientService.getAge(age);
    }

}
