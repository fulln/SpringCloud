package com.fulln.webflux.controller;

import com.fulln.webflux.pojo.User;
import com.fulln.webflux.service.UserService;
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


    public static void main(String[] args) {

//        List<String> words = Arrays.asList("aa","bb","cc","dd");
//        Flux<String> listWords = Flux.fromIterable(words);    //从集合获取
//        Flux<String> justWords = Flux.just("Hello","World");  //指定序列中包含的全部元素
//        listWords.subscribe(System.out::println);
//        justWords.subscribe(System.out::println);


//        Flux.generate(sinkpine -> {
//            sinkpine.next("Hello");   //通过 next()方法产生一个简单的值，至多调用一次
//            sinkpine.complete();      //然后通过 complete()方法来结束该序列
//        }).subscribe(System.out::println);
//        使用 create() 方法生成 Flux 序列：
//        与 generate() 方法的不同之处在于所使用的是 FluxSink 对象。FluxSink 支持同步和异步的消息产生，并且可以在一次调用中产生多个元素。
//        Flux.create(sinkpine -> {
//            for (int i = 0; i < 10; i++) {
//                sinkpine.next(i);
//            }
//            sinkpine.complete();
//        }).subscribe(System.out::println);

//        Mono.fromSupplier(() -> "Hello").subscribe(System.out::println);
//        Mono.justOrEmpty(Optional.of("Hello")).subscribe(System.out::println);
//        Mono.create(sinkpine -> sinkpine.success("Hello")).subscribe(System.out::println);

//
//        Flux.range(1, 10).filter(i -> i % 2 == 0).subscribe(System.out::println);  // 只输出满足filter条件的元素
//        Flux.just("a", "b").zipWith(Flux.just("c", "d")).subscribe(System.out::println);  // zipWith 操作符把当前流中的元素与另外一个流中的元素按照一对一的方式进行合并。
// reduce 和 reduceWith 操作符对流中包含的所有元素进行累积操作，得到一个包含计算结果的 Mono 序列。
//        Flux.range(1, 100).reduce((x, y) -> x + y).subscribe(System.out::println);
//        Flux.range(1, 100).reduceWith(() -> 0, (x, y) -> x + y).subscribe(System.out::println);

//        Flux.just(1, 2)
//                .concatWith(Mono.error(new IllegalStateException()))
//                .subscribe(System.out::println, System.err::println);

//        Flux.just(1, 2)
//                .concatWith(Mono.error(new IllegalStateException()))
//                .onErrorReturn(0)
//                .subscribe(System.out::println);

//        Flux.just(1, 2)
//                .concatWith(Mono.error(new IllegalStateException()))
//                .switchOnError(Mono.just(0))
//                .subscribe(System.out::println);

        Flux.just(1, 2)
                .concatWith(Mono.error(new IllegalStateException()))
                .retry(1)
                .subscribe(System.out::println);

    }




}
