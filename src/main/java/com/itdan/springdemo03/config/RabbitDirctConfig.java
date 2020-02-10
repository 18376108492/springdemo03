package com.itdan.springdemo03.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


@Configuration
public class RabbitDirctConfig {

    public final  static String  DIRECTNAME="rabbitmq-direct";

    /**
     * 配置消息队列
     * @return
     */
    @Bean
    Queue queue01(){
        return new Queue("queue01");
    }

    /**
     * 创建缓存区
     * 第一个参数：缓存区名
     * 第二个参数：长期是否有效
     * 第三个参数：重启是否有效
     * @return
     */
    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(DIRECTNAME,true,false);
    }

    /**
     * 将队列和缓存区进行绑定
     * @return
     */
    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue01()).to(directExchange()).with("direct");
    }



}
