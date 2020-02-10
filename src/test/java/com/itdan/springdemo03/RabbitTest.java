package com.itdan.springdemo03;

import com.itdan.springdemo03.config.RabiitFanoutConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class RabbitTest {

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Test
    public void test01(){
        //发送消息测试
        rabbitTemplate.convertAndSend("direct","hello world !!!!");
    }

    @Test
    public void test02(){
        //发送消息测试(routingKey不会生效，可以不传)
        rabbitTemplate.convertAndSend(RabiitFanoutConfig.FANOUTNAME,null,"FANOUTNAME-test");
    }


}
