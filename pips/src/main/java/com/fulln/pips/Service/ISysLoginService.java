package com.fulln.pips.Service;

import com.fulln.pips.Common.BaseResult.GlobalResult;
import com.fulln.pips.Entity.userEmpEntity;

public interface ISysLoginService {

    GlobalResult findAll(userEmpEntity u);

}
