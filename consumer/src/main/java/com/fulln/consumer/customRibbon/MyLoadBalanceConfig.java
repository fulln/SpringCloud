package com.fulln.consumer.customRibbon;

import com.netflix.loadbalancer.IRule;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

//@Configuration
//@ExcludeFromComponentScan
////使用@RibbonClient注解来实现更细粒度的客户端配置， 比如下
//// 面的代码实现了为ribbon-service-provider服务使用MyLoadBalanceConfig中的配 置。
//@RibbonClient(name = "eureka-provider", configuration = MyLoadBalanceConfig.class)
//public class MyLoadBalanceConfig {
//
//    @Bean
//    public IRule ribbonRule() { // 其中IRule就是所有规则的标准
//        return new com.netflix.loadbalancer.RetryRule(); // 随机的访问策略
//    }
//}