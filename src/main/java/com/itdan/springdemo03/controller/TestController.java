package com.itdan.springdemo03.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {

    @GetMapping("/test01")
    public String test01(){
        return "test01";
    }


    @GetMapping("/admin/test")
    public String test02(){
        return "admin";
    }


    @GetMapping("/user/test")
    public String test03(){
        return "user";
    }


    @GetMapping("/db/test")
    public String test04(){
        return "db";
    }



}
