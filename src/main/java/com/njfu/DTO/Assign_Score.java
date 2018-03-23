package com.njfu.DTO;

import com.njfu.entity.Assign;
import com.njfu.entity.Score;

import java.util.List;

/**
 * Introduction: 记录布置作业（继承作业信息Assign类）--多条文件信息类
 * Created by  LvXZ  on 2018/2/11.
 */
public class Assign_Score extends Assign{

    private List<Score> scores;

    public Assign_Score() {
    }


    public Assign_Score(List<Score> scores) {
        this.scores = scores;
    }

    public Assign_Score(String t_id, String c_no, String c_no_hw, String time, String file_no, String message, String c_time, List<Score> scores) {
        super(t_id, c_no, c_no_hw, time, file_no, message, c_time);
        this.scores = scores;
    }

    public List<Score> getScores() {
        return scores;
    }

    public void setScores(List<Score> scores) {
        this.scores = scores;
    }
}
