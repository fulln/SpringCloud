package com.fulln.webflux.controller;

import com.fulln.webflux.POJO.User;
import com.fulln.webflux.Service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.util.Objects;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/6/4 0004
 */
@RestController
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(final UserService userService) {
        this.userService = userService;
    }

    @ResponseStatus(value = HttpStatus.NOT_FOUND,reason = "Resouce not found")
    @ExceptionHandler(Exception.class)
    public void notFound(){}

    @GetMapping("")
    public Flux<User> list(){
        return this.userService.list();
    }

    @GetMapping("/{id}")
    public Mono<User> getById(@PathVariable("id")final String id){
        return this.userService.getById(id);
    }

    @PostMapping("")
    public Flux<User> create(@RequestBody final Flux<User>  users) {
        return this.userService.createOrUpdate(users);
    }

    @PutMapping("/{id}")
    public Mono<User>  update(@PathVariable("id") final String id, @RequestBody final User user) {
        Objects.requireNonNull(user);
        user.setId(id);
        return this.userService.createOrUpdate(user);
    }

    @DeleteMapping("/{id}")
    public Mono<User>  delete(@PathVariable("id") final String id) {
        return this.userService.delete(id);
    }

    @GetMapping("/randomInt")
    public Flux<ServerSentEvent<Integer>> randomNumber(){
        return Flux.interval(Duration.ofSeconds(10))
                .map(seq -> Tuples.of(seq, ThreadLocalRandom.current().nextInt()))
                .map(data -> ServerSentEvent.<Integer>builder()
                        .event("random")
                        .id(Long.toString(data.getT1()))
                        .data(data.getT2())
                        .build());
    }




}
