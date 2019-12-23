package com.itdan.springdemo03.filter;

import com.itdan.springdemo03.pojo.Meun;
import com.itdan.springdemo03.pojo.Role;
import com.itdan.springdemo03.service.MeunService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import java.util.Collection;
import java.util.List;

/**
 * 自定义路径过滤器(权限管理的核心)
 */
@Component
public class MyFilter implements FilterInvocationSecurityMetadataSource {

    @Autowired
    private MeunService meunService;

    //使用antPathMatcher去比较路径是否一致
            AntPathMatcher antPathMatcher = new AntPathMatcher();

    @Override
    public Collection<ConfigAttribute> getAttributes(Object o) throws IllegalArgumentException {
        //获取用户的请求路径
        String reqUrl = ((FilterInvocation) o).getRequestUrl();
        //获取所有路径菜单（可以使用redis做缓存）
        List<Meun> meuns= meunService.getAllmeun();
        for (Meun meun:meuns){
            //比较路径是否相同
            if(antPathMatcher.match(meun.getPatten(),reqUrl)){
                List<Role>rolelist= meun.getRoleList();
                String [] roles=new String[rolelist.size()];
                for (int i = 0; i <rolelist.size() ; i++) {
                     roles[i]="ROLE_"+rolelist.get(i).getName();
                }
                //返回先对路径的角色
                return SecurityConfig.createList(roles);
            }
        }
        //表示为登入，返回的登入角色
        return SecurityConfig.createList("ROLE_login");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return null;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return false;
    }
}
