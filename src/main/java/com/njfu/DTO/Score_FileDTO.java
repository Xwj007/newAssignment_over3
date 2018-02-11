package com.njfu.DTO;

import java.util.List;

/**
 * Introduction:
 * Created by  LvXZ  on 2018/2/9.
 */
public class Score_FileDTO {
    private String c_name;
    private String c_no_hw;
    private String message;
    private List<String> files;

    public Score_FileDTO() {
    }

    public Score_FileDTO(String c_name, String c_no_hw, String message, List<String> files) {
        this.c_name = c_name;
        this.c_no_hw = c_no_hw;
        this.message = message;
        this.files = files;
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

    public List<String> getFiles() {
        return files;
    }

    public void setFiles(List<String> files) {
        this.files = files;
    }
}
