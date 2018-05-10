package com.fulln.eurekafeign.service.fusingServiceImpl;

import com.fulln.eurekafeign.service.StartClientService;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Component;


@Component
//public class fusingBasicClient implements StartClientService {
public class fusingBasicClient implements FallbackFactory<StartClientService> {
    //还有fallbackFactory的模式    通过那种模式可以catch到当前错误的原因 ，感觉还是比较重要
    //如果要使用这种模式的话  在feign那边要使用fallbackfactory属性 而且可以让当前primary的属性改为false


//    @Override
//    public String getAge(Integer age) {
//        return "fallback:the age get was "+age+" reason ";
//    }
//
//    @Override
//    public Integer getSex() {
//        return null;
//    }

    @Override
    public StartClientService create(Throwable throwable) {
        return new StartClientService(){
            @Override
            public String getAge(Integer age) {
                return "fallback:the age get was "+age+" reason was :"+throwable;
            }

            @Override
            public Integer getSex() {
                return null;
            }
        };
    }
}
