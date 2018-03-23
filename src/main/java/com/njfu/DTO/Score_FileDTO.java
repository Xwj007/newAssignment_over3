package com.njfu.DTO;

/**
 * Introduction: 学生上传信息--文件地址信息类
 * Created by  LvXZ  on 2018/2/9.
 */
public class Score_FileDTO {
    private String c_name;
    private String c_no_hw;
    private String message;
    private String file_no;//文件存储编号
    private String get_score;

    public Score_FileDTO() {
    }

    public Score_FileDTO(String c_name, String c_no_hw, String message, String file_no, String get_score) {
        this.c_name = c_name;
        this.c_no_hw = c_no_hw;
        this.message = message;
        this.file_no = file_no;
        this.get_score = get_score;
    }

    public String getC_name() {
        return c_name;
    }

    public void setC_name(String c_name) {
        this.c_name = c_name;
    }

    public String getC_no_hw() {
        return c_no_hw;
    }

    public void setC_no_hw(String c_no_hw) {
        this.c_no_hw = c_no_hw;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getGet_score() {
        return get_score;
    }

    public void setGet_score(String get_score) {
        this.get_score = get_score;
    }

    public String getFile_no() {
        return file_no;
    }

    public void setFile_no(String file_no) {
        this.file_no = file_no;
    }
}
