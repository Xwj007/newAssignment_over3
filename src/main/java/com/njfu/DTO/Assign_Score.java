package com.njfu.DTO;

import com.njfu.entity.Assign;

import java.util.List;

/**
 * Introduction: 记录布置作业（继承作业信息Assign类）--多条文件信息类
 * Created by  LvXZ  on 2018/2/11.
 */
public class Assign_Score extends Assign{

    private List<Score_File> score_files;

    public Assign_Score() {
    }

    public Assign_Score(List<Score_File> score_files) {
        this.score_files = score_files;
    }

    public Assign_Score(String t_id, String c_no, String c_no_hw, String time, String file_no, String message, String c_time) {
        super(t_id, c_no, c_no_hw, time, file_no, message, c_time);
    }

    public Assign_Score(String t_id, String c_no, String c_no_hw, String time, String file_no, String message, String c_time, List<Score_File> score_files) {
        super(t_id, c_no, c_no_hw, time, file_no, message, c_time);
        this.score_files = score_files;
    }

    public List<Score_File> getScore_files() {
        return score_files;
    }

    public void setScore_files(List<Score_File> score_files) {
        this.score_files = score_files;
    }
}
