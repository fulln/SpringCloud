package com.fulln.eurekafeign.service.fusingServiceImpl;

import com.fulln.eurekafeign.service.StartClientService;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;


@Service
public class fusingBasicClient implements StartClientService {


    @Override
    public String getAge(Integer age) {
        return " 服务不可用。。"+age;
    }

    @Override
    public Integer getSex() {

        return null;
    }

    

}
