package com.fulln.pips.Service.Impl;

import com.fulln.pips.Dao.SysLoginDao;
import com.fulln.pips.Entity.userEmpEntity;
import com.fulln.pips.Service.ISysLoginService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysLoginServiceImpl implements ISysLoginService {

    @Autowired
    private SysLoginDao sysLoginDao;

    @Override
    public PageInfo findAll(userEmpEntity u ) throws Exception {
        PageHelper.startPage(u.getPageNo(),u.getPageSize());
        List<userEmpEntity> li= sysLoginDao.select(u);
        PageInfo page = new PageInfo(li);
        return page;
    }

}
