package io.github.ma1uta.demo.controller;

import io.github.ma1uta.demo.dto.HelloResponseDto;
import io.github.ma1uta.demo.dto.SumRequestDto;
import io.github.ma1uta.demo.dto.SumResponseDto;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api")
@RestController
public class HelloController {

    @GetMapping
    public HelloResponseDto hello() {
        return new HelloResponseDto("Hello World");
    }

    @PostMapping("/sum")
    public ResponseEntity<SumResponseDto> sum(@Valid @RequestBody SumRequestDto request) {
        return ResponseEntity.ok(new SumResponseDto(request.x() + request.y()));
    }
}
