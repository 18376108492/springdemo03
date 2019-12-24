package com.itdan.springdemo03.service;


import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class TestService {

    @Scheduled(fixedDelay = 2000)//前面的任务和后面的任务的开始时间间隔为两秒(带前面的任务执行完)
    public void test01(){
        System.out.println("time"+new Date());
    }

    @Scheduled(fixedRate = 2000)//前面的任务开启时间于后面任务开启时间间隔两秒（不要管前面任务执行完没）
    public void test02(){
        System.out.println("time"+new Date());
    }

    //corn表达式
}
