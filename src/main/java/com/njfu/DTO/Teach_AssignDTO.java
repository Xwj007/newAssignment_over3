package com.njfu.DTO;

import com.njfu.entity.Assign;

import java.util.List;

/**
 * Introduction: 教师课程--布置作业信息类
 * Created by  LvXZ  on 2018/2/9.
 */
public class Teach_AssignDTO {
    private String c_name;
    private String t_account;
    private List<Assign> assigns;

    public Teach_AssignDTO() {
    }

    public Teach_AssignDTO(String c_name, String t_account, List<Assign> assigns) {
        this.c_name = c_name;
        this.t_account = t_account;
        this.assigns = assigns;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getT_account() {
        return t_account;
    }

    public void setT_account(String t_account) {
        this.t_account = t_account;
    }

    public List<Assign> getAssigns() {
        return assigns;
    }

    public void setAssigns(List<Assign> assigns) {
        this.assigns = assigns;
    }

    @Override
    public String toString() {
        return "Teach_AssignDTO{" +
                "c_name='" + c_name + '\'' +
                ", t_account='" + t_account + '\'' +
                ", assigns=" + assigns +
                '}';
    }
}
