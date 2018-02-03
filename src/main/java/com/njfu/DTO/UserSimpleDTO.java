package com.njfu.DTO;

/**重新整合json格式数据
 * 将User将除account、password以外的全部删去
 */
public class UserSimpleDTO {
    private String account;
    private String password;

    public UserSimpleDTO(String account, String password) {
        this.account = account;
        this.password = password;
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
}


