package com.njfu.DAO;

import com.njfu.entity.Teacher;

import java.util.List;

/**
 * Introduction:
 * Created by  LvXZ  on 2018/3/25.
 */
public class TeacherDAO {

    private String id;
    private String account;

    private List<Teacher> teachers;

    public TeacherDAO() {
    }

    public TeacherDAO(String id, String account, List<Teacher> teachers) {
        this.id = id;
        this.account = account;
        this.teachers = teachers;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public List<Teacher> getTeachers() {
        return teachers;
    }

    public void setTeachers(List<Teacher> teachers) {
        this.teachers = teachers;
    }
}
