package com.itdan.springdemo03.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/admin/test")
    public String test01(){
        return "admin";
    }

    @GetMapping("/user/test")
    public String test02(){
        return "user";
    }

    @GetMapping("/views/test")
    public String test03(){
        return "views";
    }

}
