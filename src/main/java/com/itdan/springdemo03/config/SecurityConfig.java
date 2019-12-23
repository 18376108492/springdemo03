package com.itdan.springdemo03.config;

import com.itdan.springdemo03.filter.MyFilter;
import com.itdan.springdemo03.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.ObjectPostProcessor;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.intercept.FilterSecurityInterceptor;


@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private MyFilter myFilter;

    @Autowired
    private MyAccessDecisionManager myAccessDecisionManager;

    @Autowired
    private UserService userService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userService);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
       http.authorizeRequests()
               //获取请求
               .withObjectPostProcessor(new ObjectPostProcessor<FilterSecurityInterceptor>() {
                   @Override
                   public <O extends FilterSecurityInterceptor> O postProcess(O o) {
                           o.setAccessDecisionManager(myAccessDecisionManager);
                           o.setSecurityMetadataSource(myFilter);
                           return o;
                   }
               })
               .and()
               .formLogin()
               .permitAll()
               .and()
               .csrf()
               .disable();
    }
}
