package com.njfu.entity;

public class Hw_over {

    private Integer id;
    private String s_account;
    private String t_account;
    private String code;
    private String filename;
    private String address;
    private String score;

    public Hw_over() {
    }

    public Hw_over(String s_account, String t_account, String code, String filename, String address) {
        this.s_account = s_account;
        this.t_account = t_account;
        this.code = code;
        this.filename = filename;
        this.address = address;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getS_account() {
        return s_account;
    }

    public void setS_account(String s_account) {
        this.s_account = s_account;
    }

    public String getT_account() {
        return t_account;
    }

    public void setT_account(String t_account) {
        this.t_account = t_account;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    @Override
    public String toString() {
        return "Hw_over{" +
                "id=" + id +
                ", s_account='" + s_account + '\'' +
                ", t_account='" + t_account + '\'' +
                ", code='" + code + '\'' +
                ", filename='" + filename + '\'' +
                ", address='" + address + '\'' +
                ", score='" + score + '\'' +
                '}';
    }
}
