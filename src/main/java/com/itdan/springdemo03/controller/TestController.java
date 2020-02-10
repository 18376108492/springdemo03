package com.itdan.springdemo03.controller;

import com.itdan.springdemo03.pojo.User;
import io.swagger.annotations.*;
import org.springframework.web.bind.annotation.*;


@Api("测试用例")
@RestController
public class TestController {

    @GetMapping("/admin/test")
    public String test01() {
        return "admin";
    }

    @GetMapping("/user/test")
    public String test02() {
        return "user";
    }

    @GetMapping("/views/test")
    public String test03() {
        return "views";
    }

    @ApiOperation(value = "查询用户", notes = "根据ID查询用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true, defaultValue = "1")
    @GetMapping("/user/getUserById")
    public User getUserById(Integer id) {
        return new User();
    }

    @ApiOperation(value = "删除用户", notes = "根据用户ID删除用户")
    @ApiImplicitParam(name = "id", value = "用户ID", required = true)
    @ApiResponses({
            @ApiResponse(code = 200, message = "删除成功"),
            @ApiResponse(code = 500, message = "删除失败")
    })
    @DeleteMapping("/user/delete/{id}")
    @ResponseBody
    public String delete(@PathVariable Integer id) {
        return "deleteID:" + id;
    }


    @ApiOperation(value = "更新用户", notes = "更新用户")
    @ApiImplicitParams({
            @ApiImplicitParam(name = "name", value = "用户名", required = true),
            @ApiImplicitParam(name = "addr", value = "用户地址", required = true)
    })
    @PutMapping("/user/update")
    @ResponseBody
    public User update(String name, String addr) {
        User user=new User();
        user.setId(1);
        user.setName(name);
        user.setAddr(addr);
        user.setAge(10);
         return user;
    }

    @ApiOperation(value = "新增用户", notes = "添加用户")
    @PostMapping("/user/addUser")
    public  User add(@RequestBody User user){
           return user;
    }

}
