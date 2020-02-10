package com.itdan.springdemo03.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabiitFanoutConfig {

    public  final static  String  FANOUTNAME="rabbitmq-fanout";

    @Bean
    Queue queue_one(){
        return new Queue("queue_one");
    }

    @Bean
    Queue queue_two(){
        return new Queue("queue_two");
    }

    @Bean
    FanoutExchange fanoutExchange(){
        return new FanoutExchange(FANOUTNAME,true,false);
    }
    @Bean
    Binding bindingOne(){
        return BindingBuilder.bind(queue_one()).to(fanoutExchange());
    }
    @Bean
    Binding bindingTwo(){
        return BindingBuilder.bind(queue_two()).to(fanoutExchange());
    }
}
