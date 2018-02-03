package com.njfu.mapper;

import java.util.List;

import com.njfu.entity.Homework;
import com.njfu.entity.User;

/**
 * TestMapper，映射SQL语句的接口，无逻辑实现
 */
public interface UserMapper {
    List<User> Query();
    void update_pwd(User test);
    void insert(User test);
    void delete(int id);
    //扩展接口//动态查询
    User findUserByOther(User test);
    User findUserByAccount(String account);
    List<String> getTeacherList(User test);


    Homework findHomeworkByOther(Homework test);
    void insertHomework(Homework homework);
    List<String> getHomeworkList(String t_account);

}
