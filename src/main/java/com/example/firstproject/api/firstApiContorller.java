package com.example.firstproject.api;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //REST API용 컨트롤러 json 반응
public class firstApiContorller {

    @GetMapping("/api/hello")
    public String hello() { return "hello world!";}
}
