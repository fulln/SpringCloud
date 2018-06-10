package com.fulln.pips.Service.Impl;

import com.fulln.config.api.SelectThreadPool;
import com.fulln.config.entity.refletMapEntity;
import com.fulln.pips.Common.BaseResult.GlobalResult;
import com.fulln.pips.Entity.userEmpEntity;
import com.fulln.pips.Service.ISysLoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


/**
 * @AUthor: fulln
 * @Description:
 * @Date : Created in  16:15  2018/6/10.
 */
@Slf4j
@Service
public class TestReflectAll implements SelectThreadPool {

    @Autowired
    private ISysLoginService sysLoginService;


    @Override
    public List<refletMapEntity> setRefList() {
        List<refletMapEntity> li = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            refletMapEntity ref = new refletMapEntity();
            ref.setMethodName("findAll");
                List<Object> list = new ArrayList<>();
                list.add(new userEmpEntity(1));
            ref.setParams(list);
            li.add(ref);
        }
        return li;
    }

    public GlobalResult SelectAllFromGroup(){
          List li =  getCheckedClass(this.getClass());









        return null;
    }


}
