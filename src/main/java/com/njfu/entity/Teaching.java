package com.njfu.entity;

/**
 * Introduction: 选课类
 * Created by  LvXZ  on 2018/2/4.
 */
public class Teaching {

    private Integer nid;
    private String nclass;
    private String t_id;
    private String c_no;

    public Teaching() {
    }

    public Teaching(String nclass, String t_id, String c_no) {
        this.nclass = nclass;
        this.t_id = t_id;
        this.c_no = c_no;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getNclass() {
        return nclass;
    }

    public void setNclass(String nclass) {
        this.nclass = nclass;
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

    @Override
    public String toString() {
        return "Teaching{" +
                "nid=" + nid +
                ", nclass='" + nclass + '\'' +
                ", t_id='" + t_id + '\'' +
                ", c_no='" + c_no + '\'' +
                '}';
    }
}
