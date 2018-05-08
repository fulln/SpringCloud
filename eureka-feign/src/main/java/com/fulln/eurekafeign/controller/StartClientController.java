package com.fulln.eurekafeign.controller;

import com.fulln.eurekafeign.service.StartClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StartClientController {

    @Autowired
    private StartClientService startClientService;

//    @HystrixCommand(fallbackMethod = "defaultStores")
    @GetMapping("/Counts")
    public String getAge(@RequestParam("age") Integer age){
        return startClientService.getAge(age);
    }

}
