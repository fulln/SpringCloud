package com.fulln.pips.Service;

import com.fulln.pips.Entity.userEmpEntity;
import com.github.pagehelper.PageInfo;

public interface ISysLoginService {

    public PageInfo findAll(userEmpEntity u) throws Exception;

}
