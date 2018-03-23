package com.njfu.entity;

import java.util.List;

/**
 * Introduction: 作业事件发布类
 * Created by  LvXZ  on 2018/2/4.
 */
public class Assign {

    private Integer nid;
    private String t_id;
    private String c_no;
    private String c_no_hw;     //开课编号
    private String time;        //开课次数
    private String file_no;     //文件存储编号
    private String message;     //作业信息介绍
    private String c_time;      //创建时间

    public Assign() {
    }

    public Assign(String t_id, String c_no_hw) {
        this.t_id = t_id;
        this.c_no_hw = c_no_hw;
    }

    public Assign(String t_id, String c_no, String c_no_hw, String time, String file_no, String message, String c_time) {
        this.t_id = t_id;
        this.c_no = c_no;
        this.c_no_hw = c_no_hw;
        this.time = time;
        this.file_no = file_no;
        this.message = message;
        this.c_time = c_time;
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

    public String getC_no() {
        return c_no;
    }

    public void setC_no(String c_no) {
        this.c_no = c_no;
    }

    public String getC_no_hw() {
        return c_no_hw;
    }

    public void setC_no_hw(String c_no_hw) {
        this.c_no_hw = c_no_hw;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getFile_no() {
        return file_no;
    }

    public void setFile_no(String file_no) {
        this.file_no = file_no;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getC_time() {
        return c_time;
    }

    public void setC_time(String c_time) {
        this.c_time = c_time;
    }


    @Override
    public String toString() {
        return "Assign{" +
                "nid=" + nid +
                ", t_id='" + t_id + '\'' +
                ", c_no='" + c_no + '\'' +
                ", c_no_hw='" + c_no_hw + '\'' +
                ", time='" + time + '\'' +
                ", file_no='" + file_no + '\'' +
                ", message='" + message + '\'' +
                ", c_time='" + c_time + '\'' +
                '}';
    }
}

