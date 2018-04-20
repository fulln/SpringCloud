package com.fulln.pips.Controller.baseController;

import com.fulln.pips.Entity.userEmpEntity;
import com.fulln.pips.Service.ISysLoginService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


@RestController
public class detailController {

    @Resource
    private ISysLoginService sysLoginService;

    @RequestMapping("/shows")
    public PageInfo findAll(@RequestParam("Size") Integer Size){
        try {
            userEmpEntity u = new userEmpEntity();
            u.setPageSize(Size);
            u.setPageNo(1);
            return sysLoginService.findAll(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/getAge")
    private String  getAge(@RequestParam("age") Integer age){
        return age+30+"";
    }

}
