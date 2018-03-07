package com.njfu.DTO;

import com.njfu.entity.Teaching;

import java.util.List;

/**
 * Introduction: 记录课程--作业任务信息类
 * Created by  LvXZ  on 2018/2/11.
 */
public class Teach_Student_FileDTO{

    private String c_name;

    private List<Assign_Score> assign_scores;

    public Teach_Student_FileDTO() {
    }

    public Teach_Student_FileDTO(String c_name, List<Assign_Score> assign_scores) {
        this.c_name = c_name;
        this.assign_scores = assign_scores;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public List<Assign_Score> getAssign_scores() {
        return assign_scores;
    }

    public void setAssign_scores(List<Assign_Score> assign_scores) {
        this.assign_scores = assign_scores;
    }

}
