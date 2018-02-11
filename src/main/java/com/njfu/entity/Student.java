package com.njfu.entity;

/**
 * Introduction: 学生类
 * Created by  LvXZ  on 2018/2/4.
 */
public class Student {

    private Integer nid;
    private String s_id;
    private String account;
    private String password;
    private String nclass;

    public Student() {
    }

    public Student(String s_id, String account, String password, String nclass) {
        this.s_id = s_id;
        this.account = account;
        this.password = password;
        this.nclass = nclass;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getS_id() {
        return s_id;
    }

    public void setS_id(String s_id) {
        this.s_id = s_id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNclass() {
        return nclass;
    }

    public void setNclass(String nclass) {
        this.nclass = nclass;
    }

    @Override
    public String toString() {
        return "Student{" +
                "nid=" + nid +
                ", s_id='" + s_id + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", nclass='" + nclass + '\'' +
                '}';
    }
}
