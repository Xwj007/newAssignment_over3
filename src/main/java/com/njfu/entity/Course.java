package com.njfu.entity;

/**
 * Introduction: 课程类
 * Created by  LvXZ  on 2018/2/4.
 */
public class Course {

    private Integer nid;
    private String c_no;
    private String name;

    public Course() {
    }

    public Course(String c_no, String name) {
        this.c_no = c_no;
        this.name = name;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getC_no() {
        return c_no;
    }

    public void setC_no(String c_no) {
        this.c_no = c_no;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Course{" +
                "nid=" + nid +
                ", c_no='" + c_no + '\'' +
                ", name='" + name + '\'' +
                '}';
    }
}
