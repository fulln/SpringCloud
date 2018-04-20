package com.fulln.consumer.controller;

import com.fulln.consumer.service.ConsumerService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class consumerController {


    @Resource
    private ConsumerService consumerService;

    @RequestMapping("/getFromid")
    public String getpip(@RequestParam("Age")Integer Age){
        return consumerService.getFrompip(Age);
    }

    
}
