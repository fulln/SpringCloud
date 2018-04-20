package com.fulln.consumer.service.Impl;

import com.fulln.consumer.service.ConsumerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Map;

@Service
public class ConsumerServiceImpl implements ConsumerService {

    @Autowired
    private LoadBalancerClient loadBalancerClient;

    @Autowired
    private RestTemplate restTemplate;

    public String getFrompip(Integer Age){
        
    	ServiceInstance serviceInstance = this.loadBalancerClient.choose("eureka-provider");

        System.out.println("===" + ":" + serviceInstance.getServiceId() + ":" + serviceInstance.getHost() + ":"
                + serviceInstance.getPort());// 打印当前调用服务的信息

        return restTemplate.getForObject("http://"+serviceInstance.getServiceId()+"/getAge?age="+Age,String.class);


    }
}
