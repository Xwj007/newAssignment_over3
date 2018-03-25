package com.njfu.entity;

/**
 * Introduction: 上交作业成绩类
 * Created by  LvXZ  on 2018/2/4.
 */
public class Score {

    private Integer nid;
    private String s_id;
    private String c_no_hw;     //开课编号
    private String get_score;   //成绩
    private String reason;   //评语
    private String file_no;     //文件存储编号

    public Score() {
    }

    public Score(String s_id, String c_no_hw){
        this.s_id = s_id;
        this.c_no_hw = c_no_hw;
    }

    public Score(String s_id, String c_no_hw, String get_score, String file_no) {
        this.s_id = s_id;
        this.c_no_hw = c_no_hw;
        this.get_score = get_score;
        this.file_no = file_no;
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

    public String getC_no_hw() {
        return c_no_hw;
    }

    public void setC_no_hw(String c_no_hw) {
        this.c_no_hw = c_no_hw;
    }

    public String getGet_score() {
        return get_score;
    }

    public void setGet_score(String get_score) {
        this.get_score = get_score;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public String getFile_no() {
        return file_no;
    }

    public void setFile_no(String file_no) {
        this.file_no = file_no;
    }

    @Override
    public String toString() {
        return "Score{" +
                "nid=" + nid +
                ", s_id='" + s_id + '\'' +
                ", c_no_hw='" + c_no_hw + '\'' +
                ", get_score='" + get_score + '\'' +
                ", file_no='" + file_no + '\'' +
                '}';
    }
}
