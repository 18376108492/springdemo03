package com.itdan.springdemo03.service;


import com.itdan.springdemo03.mapper.MeunMapper;
import com.itdan.springdemo03.pojo.Meun;
import com.itdan.springdemo03.pojo.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MeunService {

    @Autowired
    private MeunMapper meunMapper;

    public List<Meun> getAllmeun(){
      return   meunMapper.getAllmeun();
    }
}
