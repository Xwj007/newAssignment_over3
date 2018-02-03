package com.njfu.entity;

public class Homework {

    private Integer id;
    private String t_account;
    private String code;
    private String filename;
    private String address;

    public Homework() {
    }

    public Homework(String t_account, String code, String filename, String address) {
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
}
