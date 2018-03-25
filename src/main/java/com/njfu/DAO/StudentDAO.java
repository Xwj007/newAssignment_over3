package com.njfu.DAO;

import com.njfu.entity.Student;

import java.util.List;

/**
 * Introduction:
 * Created by  LvXZ  on 2018/3/25.
 */
public class StudentDAO {
    private String id;
    private String account;

    private List<Student> students;

    public StudentDAO() {
    }

    public StudentDAO(String id, String account, List<Student> students) {
        this.id = id;
        this.account = account;
        this.students = students;
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

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}
