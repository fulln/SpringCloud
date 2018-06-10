package com.fulln.pips.Service.Impl;

import com.fulln.pips.Common.BaseResult.GlobalResult;
import com.fulln.pips.Dao.SysLoginDao;
import com.fulln.pips.Entity.userEmpEntity;
import com.fulln.pips.Service.ISysLoginService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

import static com.fulln.pips.Common.BaseResult.GlobalResult.getSuccessResult;

@Service
public class SysLoginServiceImpl implements ISysLoginService {

    @Resource
    private SysLoginDao sysLoginDao;

    @Override
    public GlobalResult findAll(userEmpEntity u ){
//        try{
            PageHelper.startPage(u.getPageNo(),u.getPageSize());
            List<userEmpEntity> li= sysLoginDao.select(u);
            return getSuccessResult(new PageInfo<>(li),"查询成功");
    }

}
