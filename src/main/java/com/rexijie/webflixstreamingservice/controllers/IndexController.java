package com.rexijie.webflixstreamingservice.controllers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import reactor.core.publisher.Mono;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

@Controller
public class IndexController {

    @Value("${video.location}")
    private String videoLocation;

    @GetMapping("/")
    public Mono<String> index() throws Exception{
        List<String> videos = Files.list(Paths.get(videoLocation))
                .map(path -> path.getFileName().toString())
                .collect(Collectors.toList());

        System.out.println(videos.toString());
        return Mono.just("index");
    }
}
