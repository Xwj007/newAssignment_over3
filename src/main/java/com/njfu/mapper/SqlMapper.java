package com.njfu.mapper;

import com.njfu.DTO.Assign_Score;
import com.njfu.entity.*;

import java.util.List;

/**
 * Introduction:
 * Created by  LvXZ  on 2018/2/4.
 */
public interface SqlMapper {

    List<Object> QueryStudent();
    List<Object> QueryTeacher();
    List<Object> QueryCourse();
    List<Object> QueryTeaching();
    List<Object> QueryAssign();
    List<Object> QueryScore();
    List<Object> QueryAll_file();

    String findStudentByS_id(String s_id);
    String findTeacherByT_id(String T_id);

    void addStudent(Student test);
    void addTeacher(Teacher test);

    void update_Student_pwd(Student test);
    void update_Teacher_pwd(Teacher test);

    Student findStudentByOther(Student test);
    Teacher findTeacherByOther(Teacher test);

    void deleteStudent(Integer nid);
    void deleteTeacher(Integer nid);

    List<Assign> findAssignByID(String t_id);
    Assign findAssignByHW(String c_no_hw);
    void deleteAssignHW(Integer nid);
    void deleteTeacherAll_file(String file_no);
    List<Course> findCourseByT_id(String t_id);

    void addAssignHW(Assign test);
    void addTeacherAll_file(All_file test);

    List<Teaching> findTeachingByS_id(String s_id);
    List<Assign> findAssignByIDAndC_no(Teaching teaching);
    List<Assign_Score> findAssignByIDAndC_no2(Teaching teaching);
    String findCourseNameByC_no(String c_no);
    String findTeacherNameByT_id(String t_id);

    List<String> findAll_fileByFile_no(String file_no);
    List<All_file> findAll_fileByFile_no2(String file_no);
    All_file findAll_fileByFile_no_name(All_file test);

    List<Score> findScoreByS_id(String s_id);
    List<Score> findScoreByC_no_hw(String c_no_hw);
    String findCourseNameByOther(String c_no_hw);

    Score findScoreByClass(Score test);
    void deleteStudentAll_file(String file_no);

    void addStudentAll_file(All_file test);
    void addScoreHW(Score test);

    List<Teaching> findTeachingALLByT_id(String t_id);
    void setScoreGet_score(Score score);

    Assign_Score findAssignByT_idAndC_no_hw(Assign test);
    List<Student> findStudentTeachingByAssign(Assign_Score test);
}
