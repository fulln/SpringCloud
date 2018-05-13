package com.fulln.apiyoudao.controller;

import com.fulln.apiyoudao.service.TranslateService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @AUthor: fulln
 * @Description:  对接有道云
 * @Date : Created in  12:47  2018/5/12.
 */
@RestController
public class youdaoController {

    @Resource
    private TranslateService translateService;

    @GetMapping("/youdao")
    public String WriteTo(){
        return translateService.getTransback("你的名字");
    }


}
