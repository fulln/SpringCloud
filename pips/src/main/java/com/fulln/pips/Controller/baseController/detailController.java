package com.fulln.pips.Controller.baseController;

import com.fulln.pips.Entity.userEmpEntity;
import com.fulln.pips.Service.ISysLoginService;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RequestMapping("/detail")
@RestController
public class detailController {

    @Resource
    private ISysLoginService sysLoginService;

    @RequestMapping("/shows")
    public PageInfo findAll(){
        try {
            userEmpEntity u = new userEmpEntity();
            u.setPageSize(10);
            u.setPageNo(1);
            return sysLoginService.findAll(u);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
