package com.fulln.provider.Controller;


import com.fulln.provider.Service.ProviderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
public class ProviderController {

    @Resource
    private ProviderService providerService;

    @GetMapping("/getAge")
    private String  getAge(@RequestParam("age") Integer age){
        return providerService.getAge(age);
    }

}
