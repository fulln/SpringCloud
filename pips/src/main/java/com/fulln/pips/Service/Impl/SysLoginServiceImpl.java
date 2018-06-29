package com.fulln.pips.Service.Impl;

import com.fulln.pips.Common.BaseResult.GlobalResult;
import com.fulln.pips.Common.util.ExceptionEnum;
import com.fulln.pips.Dao.SysLoginDao;
import com.fulln.pips.Entity.userEmpEntity;
import com.fulln.pips.Service.ISysLoginService;
import com.github.pagehelper.PageHelper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SysLoginServiceImpl implements ISysLoginService {

    @Resource
    private SysLoginDao sysLoginDao;

    @Override
    @Transactional
    public GlobalResult findAll(userEmpEntity u ){
            PageHelper.startPage(u.getPageNo(),u.getPageSize());
            List<userEmpEntity> li= sysLoginDao.select(u);
            return ExceptionEnum.SUCCESS.globalResult(li);
    }

}
