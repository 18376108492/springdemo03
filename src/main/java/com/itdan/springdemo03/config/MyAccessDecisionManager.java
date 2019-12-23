package com.itdan.springdemo03.config;

import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Component;

import java.util.Collection;

@Component
public class MyAccessDecisionManager implements AccessDecisionManager {
    @Override
    public void decide(Authentication authentication, Object o, Collection<ConfigAttribute> collection) throws AccessDeniedException, InsufficientAuthenticationException {
        //authentication存储的用户登入的信息
        //collection是我们拦截器中所返回的角色信息
        //遍历拦截器中返回的角色信息
        for (ConfigAttribute attribute:collection){
            //判断是否为为登入的用户
            if("ROLE_login".equals(attribute.getAttribute())){
                if(authentication instanceof AnonymousAuthenticationToken){
                    //检测用户是否为匿名用户，如果匿名用户表示为非法登入，则抛出异常
                    throw new AccessDeniedException("非法请求");
                }else {
                    return;//表示已经登入
                }
            }
            //获取登入用户所具备的角色
           Collection<? extends GrantedAuthority> login_roles = authentication.getAuthorities();
            //判断登入用户是否具备访问该路径的权限
             for (GrantedAuthority authority:login_roles){
                 if(authority.getAuthority().equals(attribute.getAttribute())){
                    return;
                 }
             }
        }
        throw new AccessDeniedException("非法请求");
    }

    @Override
    public boolean supports(ConfigAttribute configAttribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return true;
    }
}
