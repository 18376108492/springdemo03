package com.itdan.springdemo03.receiver;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

@Component
public class DirectReceiver {

    @RabbitListener(queues = "queue01")
    public void Listener01(String msg){
        System.out.println("Listener01:"+msg);
    }
}
