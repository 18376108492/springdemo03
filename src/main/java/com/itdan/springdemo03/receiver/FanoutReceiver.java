package com.itdan.springdemo03.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;


@Component
public class FanoutReceiver {

    @RabbitListener(queues = "queue_one")
    public void handle01(String msg){
        System.out.println("handle01"+msg);
    }

    @RabbitListener(queues = "queue_two")
    public void handle02(String msg){
        System.out.println("handle02"+msg);
    }
}
