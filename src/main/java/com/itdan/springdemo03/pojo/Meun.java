package com.itdan.springdemo03.pojo;


import java.util.List;

public class Meun {

    private Integer id;
    private String patten;//允许访问路径
    private List<Role> roleList;

    @Override
    public String toString() {
        return "Meun{" +
                "id=" + id +
                ", patten='" + patten + '\'' +
                ", roleList=" + roleList +
                '}';
    }

    public List<Role> getRoleList() {
        return roleList;
    }

    public void setRoleList(List<Role> roleList) {
        this.roleList = roleList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPatten() {
        return patten;
    }

    public void setPatten(String patten) {
        this.patten = patten;
    }
}
