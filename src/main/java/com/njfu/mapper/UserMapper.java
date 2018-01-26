package com.njfu.mapper;

import java.util.List;

import com.njfu.entity.Staff;
import com.njfu.entity.User;

/**
 * TestMapper，映射SQL语句的接口，无逻辑实现
 */
public interface UserMapper {
    List<User> Query();
    void insert(User test);
    void update_pwd(User test);
    void update_info(User test);
    void delete(Integer id);
    //扩展接口//通过account查询账号密码
    User findUserByAccount(String account);

    Staff findStaffById(String staff_id);

}
