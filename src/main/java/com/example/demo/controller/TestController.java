package com.example.demo.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/*
hello 控制器测试
*/

@RestController
public class TestController {


    @GetMapping("/hello1")
    public String sayHello1() {

        return "Hello, Spring Boot is running!";
    }

}