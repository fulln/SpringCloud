package com.fulln.pips.Service.Impl;

import com.fulln.pips.Common.BaseResult.GlobalResult;
import com.fulln.pips.Dao.SysLoginDao;
import com.fulln.pips.Entity.userEmpEntity;
import com.fulln.pips.Service.ISysLoginService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

import static com.fulln.pips.Common.BaseResult.GlobalResult.getSuccessResult;
import static com.fulln.pips.Common.BaseResult.GlobalResult.getfaultResult;

@Service
public class SysLoginServiceImpl implements ISysLoginService {

    @Resource
    private SysLoginDao sysLoginDao;

    @Override
    public GlobalResult findAll(userEmpEntity u ){
//        try{
            int i=5/0;
            PageHelper.startPage(u.getPageNo(),u.getPageSize());
            List<userEmpEntity> li= sysLoginDao.select(u);
            return getSuccessResult(new PageInfo<>(li),"查询成功");
//        }catch(Exception e){
//            e.printStackTrace();
//            return getfaultResult("查询失败");
//        }
    }

}
