package com.fulln.pips.Controller.baseController;

import com.fulln.pips.Common.BaseResult.GlobalResult;
import com.fulln.pips.Entity.userEmpEntity;
import com.fulln.pips.Service.ISysLoginService;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;


@RestController
public class detailController {

    @Resource
    private ISysLoginService sysLoginService;


    @RequestMapping("/shows")
    public GlobalResult findAll(@RequestParam("Size") Integer Size){
            userEmpEntity u = new userEmpEntity();
            u.setPageSize(Size);
            u.setPageNo(1);
            return sysLoginService.findAll(u);
    }

    @GetMapping("/getAge")
    private String  getAge(@RequestParam("age") Integer age){
        return age+30+"";
    }

}
