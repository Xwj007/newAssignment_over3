package com.njfu.DTO;

import com.njfu.entity.Staff;

/**重新整合User 和 Staff 的json格式数据
 * 将User的流水号nid删去
 */
public class UserInfoDTO extends Staff {

    private String account;
    private String password;
    private String phone;
    private String last_update;
    private String address;
    private String email;

    public UserInfoDTO() {
    }

    public UserInfoDTO(String staff_id, String power, String work) {
        super(staff_id, power, work);
    }

    public UserInfoDTO(String account, String password, String phone, String last_update, String address, String email) {
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.last_update = last_update;
        this.address = address;
        this.email = email;
    }

    public UserInfoDTO(String staff_id, String power, String work, String account, String password, String phone, String last_update, String address, String email) {
        super(staff_id, power, work);
        this.account = account;
        this.password = password;
        this.phone = phone;
        this.last_update = last_update;
        this.address = address;
        this.email = email;
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
        return "UserInfoDTO{" +
                "account='" + account + '\'' +
                ", password='" + password + '\'' +
                ", phone='" + phone + '\'' +
                ", last_update='" + last_update + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
