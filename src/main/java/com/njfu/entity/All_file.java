package com.njfu.entity;

/**
 * Introduction: 文件类
 * Created by  LvXZ  on 2018/2/4.
 */
public class All_file {

    private Integer nid;
    private String file_no;     //文件存储编号
    private String address;     //文件绝对地址
    private String name;
    private String file_url;    //文件相对地址

    public All_file() {
    }

    public All_file(String file_no, String address, String name, String file_url) {
        this.file_no = file_no;
        this.address = address;
        this.name = name;
        this.file_url = file_url;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
    }

    public String getFile_no() {
        return file_no;
    }

    public void setFile_no(String file_no) {
        this.file_no = file_no;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFile_url() {
        return file_url;
    }

    public void setFile_url(String file_url) {
        this.file_url = file_url;
    }

    @Override
    public String toString() {
        return "All_file{" +
                "nid=" + nid +
                ", file_no='" + file_no + '\'' +
                ", address='" + address + '\'' +
                ", name='" + name + '\'' +
                ", file_url='" + file_url + '\'' +
                '}';
    }
}
