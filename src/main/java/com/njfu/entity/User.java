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
    private String staff_id;
    private String phone;
    private String last_update;
    private String address;
    private String email;


    public User() {
    }

    public User(String account, String password) {
        this.account = account;
        this.password = password;
    }

    public User(String account, String password, String staff_id, String phone, String last_update, String address, String email) {
        this.account = account;
        this.password = password;
        this.staff_id = staff_id;
        this.phone = phone;
        this.last_update = last_update;
        this.address = address;
        this.email = email;
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

    public String getStaff_id() {
        return staff_id;
    }

    public void setStaff_id(String staff_id) {
        this.staff_id = staff_id;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getLast_update() {
        return last_update;
    }

    public void setLast_update(String last_update) {
        this.last_update = last_update;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", staff_id='" + staff_id + '\'' +
                ", phone='" + phone + '\'' +
                ", last_update='" + last_update + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
