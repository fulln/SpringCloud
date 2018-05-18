package com.fulln.apiyoudao.controller;

import com.fulln.apiyoudao.service.ITranslateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @AUthor: fulln
 * @Description:  对接有道云
 * @Date : Created in  12:47  2018/5/12.
 */

public class youdaoController {

    @Autowired
    private ITranslateService TranslateService;


}
