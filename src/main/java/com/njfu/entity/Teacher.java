package com.njfu.entity;

/**
 * Introduction: 教师类
 * Created by  LvXZ  on 2018/2/4.
 */
public class Teacher {

    private Integer nid;
    private String t_id;
    private String account;
    private String password;

    public Teacher() {
    }

    public Teacher(String t_id, String account, String password) {
        this.t_id = t_id;
        this.account = account;
        this.password = password;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getT_id() {
        return t_id;
    }

    public void setT_id(String t_id) {
        this.t_id = t_id;
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

    @Override
    public String toString() {
        return "Teacher{" +
                "nid=" + nid +
                ", t_id='" + t_id + '\'' +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
