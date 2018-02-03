package com.njfu.entity;


/**映射数据库usersql,类对象属性
 * id       :流水号
 * account  :用户名
 * password :密码
 */

public class User {
    private Integer id;
    private String account;
    private String password;
    private String work;
    private String nclass;


    public User() {
    }

    public User( String account, String password, String work, String nclass) {
        this.account = account;
        this.password = password;
        this.work = work;
        this.nclass = nclass;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getWork() {
        return work;
    }

    public void setWork(String work) {
        this.work = work;
    }

    public String getNclass() {
        return nclass;
    }

    public void setNclass(String nclass) {
        this.nclass = nclass;
    }
}
