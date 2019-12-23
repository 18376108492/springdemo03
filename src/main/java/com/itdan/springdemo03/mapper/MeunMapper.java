package com.itdan.springdemo03.mapper;

import com.itdan.springdemo03.pojo.Meun;
import com.itdan.springdemo03.pojo.Role;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@Mapper
public interface MeunMapper {


//    @Select("SELECT e.id ,e.patten,r.id as rid,r.name,r.nameZh\n" +
//            "from meun e,meun_role mr,role r\n" +
//            "where e.id=mr.mid and r.id=mr.rid")

    /**
     * 查询所有路径菜单
     * @return
     */
    List<Meun> getAllmeun();
    //[Meun{id=1, patten='/db/**', roleList=null}, Meun{id=2, patten='/admin/**', roleList=null}, Meun{id=3, patten='/user/**', roleList=null}]
    //查询结果没达到想要的效果
}
