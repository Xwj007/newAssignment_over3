package com.njfu.DTO;

import com.njfu.entity.All_file;
import com.njfu.entity.Score;

import java.util.List;

/**
 * Introduction:
 * Created by  LvXZ  on 2018/2/11.
 */
public class Score_File extends Score {

    private List<All_file>  all_files;

    public Score_File() {
    }

    public Score_File(List<All_file> all_files) {
        this.all_files = all_files;
    }

    public Score_File(String s_id, String c_no_hw) {
        super(s_id, c_no_hw);
    }

    public Score_File(String s_id, String c_no_hw, List<All_file> all_files) {
        super(s_id, c_no_hw);
        this.all_files = all_files;
    }

    public Score_File(String s_id, String c_no_hw, String get_score, String file_no) {
        super(s_id, c_no_hw, get_score, file_no);
    }

    public Score_File(String s_id, String c_no_hw, String get_score, String file_no, List<All_file> all_files) {
        super(s_id, c_no_hw, get_score, file_no);
        this.all_files = all_files;
    }

    public List<All_file> getAll_files() {
        return all_files;
    }

    public void setAll_files(List<All_file> all_files) {
        this.all_files = all_files;
    }
}
