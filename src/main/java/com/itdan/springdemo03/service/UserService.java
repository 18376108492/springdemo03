package com.itdan.springdemo03.service;

import com.itdan.springdemo03.mapper.UserMapper;
import com.itdan.springdemo03.pojo.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserService implements UserDetailsService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 检测登入用户是否存在
     * @param username
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user= userMapper.loadUserByUsername(username);
        if(null==user){
            throw new UsernameNotFoundException("用户不存在");
        }

        //添加用户权限
     user.setRoleList (userMapper.getRoleById(user.getId()));

        return user;
    }
}
