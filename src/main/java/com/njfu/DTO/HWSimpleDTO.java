package com.njfu.DTO;

import java.util.List;

public class HWSimpleDTO {
    private String account;
    private List<String> filename;

    public HWSimpleDTO() {
    }

    public HWSimpleDTO(String account) {
        this.account = account;
    }

    public HWSimpleDTO(String account, List<String> fielname) {
        this.account = account;
        this.filename = fielname;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<String> getFilename() {
        return filename;
    }

    public void setFielname(List<String> filename) {
        this.filename = filename;
    }

    @Override
    public String toString() {
        return "HWSimpleDTO{" +
                "account='" + account + '\'' +
                ", fielname=" + filename +
                '}';
    }
}
