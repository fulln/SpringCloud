package com.fulln.webflux.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/6/4 0004
 */
@RestController
public class HelloController {


    @GetMapping("/hello")
    public Mono<String> saySomeThing(){
        return Mono.just("hello");
    }

}
