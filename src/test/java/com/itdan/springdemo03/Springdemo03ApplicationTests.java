package com.itdan.springdemo03;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

@SpringBootTest
@RunWith(SpringRunner.class)
public class Springdemo03ApplicationTests {


    @Autowired
    private MeunService meunService;

    @Autowired
    private UserService userService;


    @Test
    public void test01() throws Exception{
        List<Meun> meuns= meunService.getAllmeun();
        System.out.println(meuns.toString());
    }

   @Test
   public void testtest02() throws Exception{
       UserDetails user= userService.loadUserByUsername("admin");
       System.out.println(user.getAuthorities());
       //System.out.println(user.toString());
    }

}
