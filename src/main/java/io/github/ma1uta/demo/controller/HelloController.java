package io.github.ma1uta.demo.controller;

import io.github.ma1uta.demo.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HelloController {

    @GetMapping
    public HelloResponseDto hello() {
        return new HelloResponseDto("Hello World");
    }
}
