package com.fulln.webflux.controller;

import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ZeroCopyHttpOutputMessage;
import org.springframework.http.codec.multipart.FilePart;

import org.springframework.http.server.reactive.ServerHttpResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import reactor.core.publisher.Mono;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @Author: fulln
 * @Description:
 * @Date: Created in 2018/6/5 0005
 */
@RequestMapping("/upAndDown")
public class uploadAndDownController {

    @PostMapping(value = "/upload",consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Mono<String> requestBodyFlux(@RequestPart("file") FilePart filePart) {
        System.out.println(filePart.filename());
        try {
            Path tmpFile = Files.createTempFile("dict", filePart.filename());
            filePart.transferTo(tmpFile.toFile());

            return Mono.just(filePart.filename());

        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    @GetMapping("/down")
    public Mono<Void> download(ServerHttpResponse response){
        ZeroCopyHttpOutputMessage outputMessage = (ZeroCopyHttpOutputMessage) response;
        response.getHeaders().set(HttpHeaders.CONTENT_DISPOSITION,"attachment;filename = parallel.png");
        response.getHeaders().setContentType(MediaType.IMAGE_PNG);
        Resource resource = new ClassPathResource("parallel.png");
        try {
            File file =  resource.getFile();
            return outputMessage.writeWith(file,0,file.length());
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


}
