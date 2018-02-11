package com.njfu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njfu.DTO.*;
import com.njfu.entity.*;
import com.njfu.mapper.SqlMapper;
import com.njfu.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.net.URLEncoder;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;


@RestController
public class UserController {
    private final static Logger logger = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private SqlMapper sqlMapper;//引入MyBatis接口

    /**********************************管理员操作**********************************/
    /**查询所有用户信息列表**/
    @PostMapping(value = "/all",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public List<Object> allResult(HttpServletResponse rsp) {

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        List<Object> newUser = sqlMapper.QueryStudent();
        newUser.add(sqlMapper.QueryTeacher());
        return newUser;
    }

    /**添加用户信息**/
    @PostMapping(value = "/insert",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> insertResult(@RequestBody String params, HttpServletRequest request,
                                                       HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String id = paramJson.get("id").textValue();
        String account = paramJson.get("account").textValue();
        String password = paramJson.get("password").textValue();
        String nclass = paramJson.get("nclass").textValue();
        String radio = paramJson.get("radio").textValue();

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(radio.equals("学生")){
            Student student = new Student(id, account, password, nclass);
            if( sqlMapper.findStudentByAccount(student.getAccount()) == null ) {
                sqlMapper.addStudent(student);//添加

                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("add.success.code")), PropertiesUtil.getProperty("add.success.msg"),student);
                return responseInfoDTO;
            }

        }else{
            Teacher teacher = new Teacher(id, account, password);
            if( sqlMapper.findTeacherByAccount(teacher.getAccount()) == null ) {
                sqlMapper.addTeacher(teacher);//添加

                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("add.success.code")), PropertiesUtil.getProperty("add.success.msg"),teacher);
                return responseInfoDTO;

            }
        }

        responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("add.failure.code")), PropertiesUtil.getProperty("add.failure.msg"),null);
        return responseInfoDTO;
    }

    /**更新用户信息--修改密码**/
    @PostMapping(value = "/update_pwd",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> update_pwd_Result(@RequestBody String params, HttpServletRequest request,
                                                          HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String account = paramJson.get("account").textValue();
        String old_password = paramJson.get("old_password").textValue();
        String new_Password = paramJson.get("new_password").textValue();
        String radio = paramJson.get("radio").textValue();

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        if(radio.equals("学生")){//学生修改密码
            UserSimpleDTO newStudent = sqlMapper.findStudentByAccount(account);
            if( newStudent != null){
                if(newStudent.getPassword().equals(old_password)){
                    newStudent.setPassword(new_Password);
                    sqlMapper.update_Student_pwd(newStudent);
                    //消息提示工具类获取key// 正确码,字符型转为整型
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("update.pwd.success.code")), PropertiesUtil.getProperty("update.pwd.success.msg"),newStudent);
                    return responseInfoDTO;
                }
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("update.pwd.failure_2.code")), PropertiesUtil.getProperty("update.pwd.failure_2.msg"),null);
                return responseInfoDTO;
            }
        }else{//老师修改密码
            UserSimpleDTO newTeacher = sqlMapper.findTeacherByAccount(account);
            if( newTeacher != null){
                if(newTeacher.getPassword().equals(old_password)){
                    newTeacher.setPassword(new_Password);
                    sqlMapper.update_Teacher_pwd(newTeacher);

                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("update.pwd.success.code")), PropertiesUtil.getProperty("update.pwd.success.msg"),newTeacher);
                    return responseInfoDTO;
                }
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("update.pwd.failure_2.code")), PropertiesUtil.getProperty("update.pwd.failure_2.msg"),null);
                return responseInfoDTO;
            }
        }
        responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("update.pwd.failure_1.code")), PropertiesUtil.getProperty("update.pwd.failure_1.msg"),null);
        return responseInfoDTO;
    }


    /**删除用户信息**/
    @PostMapping(value = "/delete",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> deleteResult(@RequestBody String params, HttpServletRequest request,
                                                       HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String id = paramJson.get("id").textValue();
        String account = paramJson.get("account").textValue();
        String radio = paramJson.get("radio").textValue();

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        if(radio.equals("学生")) {//删除学生
            Student student = new Student(id,account,null,null);
            Student newStudent = sqlMapper.findStudentByOther(student);
            if(newStudent == null){
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("delete.failure.code")), PropertiesUtil.getProperty("delete.failure.msg"),null);
                return responseInfoDTO;
            }else {
                Integer nid = newStudent.getNid();
                sqlMapper.deleteStudent(nid);//直接将数据库数据删除

                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("delete.success.code")), PropertiesUtil.getProperty("delete.success.msg"),null);
                return responseInfoDTO;
            }

        }else{//删除老师
            Teacher teacher = new Teacher(id,account,null);
            Teacher newTeacher = sqlMapper.findTeacherByOther(teacher);
            if(newTeacher == null){
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("delete.failure.code")), PropertiesUtil.getProperty("delete.failure.msg"),null);
                return responseInfoDTO;
            }else {
                Integer nid = newTeacher.getNid();
                sqlMapper.deleteTeacher(nid);//直接将数据库数据删除

                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("delete.success.code")), PropertiesUtil.getProperty("delete.success.msg"),null);
                return responseInfoDTO;
            }
        }
    }


    /**老师文件上传前，提示老师已经上传的作业，以便删除以前的上传作业**/
    @PostMapping(value = "/teacher_help_upload",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Assign> helpT_Upload(@RequestBody String params, HttpServletRequest request,
                                                 HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String account = paramJson.get("account").textValue();

        Teacher teacher = new Teacher(null,account,null);
        teacher = sqlMapper.findTeacherByOther(teacher);//通过查找赋予对象新的属性

        List<Assign> assigns = sqlMapper.findAssignByID(teacher.getT_id());//老师所有发布而且未删除的作业

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(assigns == null ){
            responseInfoDTO = new ResponseInfoDTO(-10, "老师没有作业",null);
            return responseInfoDTO;
        }else{
            responseInfoDTO = new ResponseInfoDTO(10, "老师作业已接收",assigns);
            return responseInfoDTO;
        }

    }

    /**老师删除文件**/
    @PostMapping(value = "/teacher_delete_file",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> helpT_Delete(@RequestBody String params, HttpServletRequest request,
                                  HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String c_no_hw = paramJson.get("c_no_hw").textValue();//获取开课作业编号//这里可以直接传递nid

        Assign assign = sqlMapper.findAssignByHW(c_no_hw);

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(assign == null){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("delete.file.failure.code")),
                    PropertiesUtil.getProperty("delete.file.failure.msg"),null);
            return responseInfoDTO;
        }else{
            Integer nid = assign.getNid();
            sqlMapper.deleteAssignHW(nid);//删除assign存储的事件信息
            sqlMapper.deleteTeacherAll_file(assign.getFile_no());//删除assign中另存的文件

            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("delete.file.success.code")),
                    PropertiesUtil.getProperty("delete.file.success.msg"),null);
            return responseInfoDTO;
        }
    }

    /**老师文件上传前，获取老师教授课程，以便选择课程上传文件**/
    @PostMapping(value = "/teacher_get_teaching",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<Assign> teacher_get_teaching(@RequestBody String params, HttpServletRequest request,
                                                       HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String t_id = paramJson.get("t_id").textValue();

        List<String> course_name = sqlMapper.findTeachingByT_id(t_id);
        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(course_name == null ){
            responseInfoDTO = new ResponseInfoDTO(-11, "老师没有授课",null);
            return responseInfoDTO;
        }else{
            responseInfoDTO = new ResponseInfoDTO(11, "老师授课信息已接收",course_name);
            return responseInfoDTO;
        }
    }
    /**老师文件上传**/
    @PostMapping(value = "/teacher_upload",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    @ResponseBody
    public ResponseInfoDTO<Assign> teacher_upload(HttpServletRequest request, HttpServletResponse rsp)  {

        String t_id = request.getParameter("t_id");
        String c_no = request.getParameter("c_no");
        String time = request.getParameter("time");
        String message = request.getParameter("message");
        String c_no_hw = c_no +"-"+ t_id +"-"+ time;//课程编号+教师编号+开课作业次数
        String file_no = c_no_hw;
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");//获取文件

        Date date=new Date();
        DateFormat format= new SimpleDateFormat("yyyyMMdd");
        String create_time = format.format(date);
        Assign assign = new Assign( t_id, c_no, c_no_hw, time, file_no, message, create_time);//初始化对象

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        MultipartFile file = null;
        BufferedOutputStream stream = null;
        //设置保存路径D:/aim/课程/老师/课程开课/老师作业文件
        String filePath = "D://aim//" +c_no+ "//" +t_id+ "//" +c_no_hw+ "//";
        File file_isExists = new File(filePath);
        if( !file_isExists.exists()){//不存在该文件夹时，就创建
            file_isExists.mkdirs();
        }

        for (int i = 0; i < files.size(); ++i) {
            file = files.get(i);

            if (!file.isEmpty()) {
                try {
                    byte[] bytes = file.getBytes();
                    stream = new BufferedOutputStream(new FileOutputStream(
                            new File(filePath + file.getOriginalFilename())));//设置文件路径及名字

                    All_file all_file = new All_file(file_no,filePath + file.getOriginalFilename(),file.getOriginalFilename());
                    sqlMapper.addTeacherAll_file(all_file);

                    stream.write(bytes);// 写入
                    stream.close();
                } catch (Exception e) {
                    stream = null;
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                            PropertiesUtil.getProperty("upload.file.failure_1.code")),
                            "第 " + (i+1) + PropertiesUtil.getProperty("upload.file.failure_1.msg") + e.getMessage(),null);
                    return responseInfoDTO;
                }
            } else {
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                        PropertiesUtil.getProperty("upload.file.failure_2.code")),
                        "第 " + (i+1) + PropertiesUtil.getProperty("upload.file.failure_2.msg"),null);
                return responseInfoDTO;
            }
        }
        sqlMapper.addAssignHW(assign);

        responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                PropertiesUtil.getProperty("upload.file.success.code")),
                PropertiesUtil.getProperty("upload.file.success.msg"),assign);
        return responseInfoDTO;
    }


    /**获取老师作业信息**/
    @PostMapping(value = "/getT_Homework",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public List<Teach_AssignDTO> showTeacherHomework (@RequestBody String params, HttpServletRequest request,
                                                     HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String account = paramJson.get("account").textValue();
        //学生s_id --> class --> c_no --> t_id
        Student student = new Student(null,account,null,null);
        student = sqlMapper.findStudentByOther(student);

        List<Teaching> teachings = sqlMapper.findTeachingByS_id(student.getS_id());

        rsp.setHeader("Access-Control-Allow-Methods", "POST");

        List<Teach_AssignDTO> teach_assignDTOS = new ArrayList<>();
        for(Teaching teach: teachings) {         // teach就是遍历的每一个Teaching对象
            List<Assign> assigns = sqlMapper.findAssignByIDAndC_no(teach);
            for(Assign as : assigns){
                as.setFiles(sqlMapper.findAll_fileByFile_no(as.getFile_no()));
            }
            Teach_AssignDTO teach_assignDTO = new Teach_AssignDTO(sqlMapper.findCourseNameByC_no(teach.getC_no()), sqlMapper.findTeacherNameByT_id(teach.getT_id()),assigns);
            teach_assignDTOS.add(teach_assignDTO);
        }
        return teach_assignDTOS;
    }



    /**
     * 文件地址服务器下载函数
     * @param file 文件地址
     * @param fileName 文件名
     * @param rsp 返回协议
     */
    public void helpDownload (File file, String fileName, HttpServletResponse rsp){

        if (file.exists()) {
            rsp.setContentType("application/force-download");// 设置强制下载不打开
            System.out.println("存在");
            try {// 设置文件名,避免中文文件下载乱码
                rsp.addHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode(fileName, "UTF-8"));
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            byte[] buffer = new byte[1024];
            FileInputStream fis = null;
            BufferedInputStream bis = null;
            try {
                fis = new FileInputStream(file);
                bis = new BufferedInputStream(fis);
                OutputStream os = rsp.getOutputStream();
                int i = bis.read(buffer);
                while (i != -1) {
                    os.write(buffer, 0, i);
                    i = bis.read(buffer);
                }

            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("程序猿真不知道为什么, 反正就是下载失败了");

            } finally {
                if (bis != null) {
                    try {
                        bis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
                if (fis != null) {
                    try {
                        fis.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public ResponseInfoDTO<UserSimpleDTO> helpReturnDownload (String file_no,String file_name,HttpServletResponse rsp){
        All_file testAll_file = new All_file(file_no, null, file_name);
        All_file all_file = sqlMapper.findAll_fileByFile_no_name(testAll_file);


        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(all_file == null){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("download.file.failure_1.code")),
                    PropertiesUtil.getProperty("download.file.failure_1.msg"),null);
            return responseInfoDTO;
        }else{
            String filePath = all_file.getAddress();
            File file = new File(filePath);

            System.out.println(file_name+"  "+filePath);

            helpDownload(file, file_name, rsp);//调用文件地址服务器下载函数

            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("download.file.success.code")),
                    PropertiesUtil.getProperty("download.file.success.msg"),null);
            return responseInfoDTO;
        }
    }

    /**老师作业文件下载     模式1**/
    @PostMapping(value = "/student_download_1",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> download1 (HttpServletRequest request, HttpServletResponse rsp) {

        String file_no = request.getParameter("file_no");//作业文件存储编号
        String file_name = request.getParameter("file_name");//作业文件存储编号

        return helpReturnDownload(file_no, file_name, rsp);
    }

    /**老师作业文件下载     模式2**/
    @PostMapping(value = "/student_download_2",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> download2 (@RequestBody String params, HttpServletRequest request,
                          HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String file_no = paramJson.get("file_no").textValue();
        String file_name = paramJson.get("file_name").textValue();

        return helpReturnDownload(file_no, file_name, rsp);
    }


    /**学生文件上传前，提示学生已经上传的作业，以便删除以前的上传作业**/
    @PostMapping(value = "/student_help_upload",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public List<Score_FileDTO> helpS_Upload(@RequestBody String params, HttpServletRequest request,
                                  HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String s_id = paramJson.get("s_id").textValue();

        List<Score> scores = sqlMapper.findScoreByS_id(s_id);
        //++添加课程信息
        List<Score_FileDTO> score_fileDTOS = new ArrayList<>();
        for(Score sc : scores){
            Assign assign = sqlMapper.findAssignByHW(sc.getC_no_hw());

            Score_FileDTO score_fileDTO = new Score_FileDTO(sqlMapper.findCourseNameByOther(sc.getC_no_hw()), sc.getC_no_hw(),assign.getMessage(),sqlMapper.findAll_fileByFile_no(sc.getFile_no()));
            score_fileDTOS.add(score_fileDTO);
        }

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        return score_fileDTOS;
    }

    /**学生删除文件**/
    @PostMapping(value = "/student_delete_file",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> helpS_Delete(@RequestBody String params, HttpServletRequest request,
                                                     HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String s_id = paramJson.get("s_id").textValue();
        String c_no_hw = paramJson.get("c_no_hw").textValue();
        String file_name = paramJson.get("file_name").textValue();

        Score score = new Score(s_id, c_no_hw);
        score = sqlMapper.findScoreByClass(score);

        All_file testAll_file = new All_file(score.getFile_no(), null, file_name);
        All_file all_file = sqlMapper.findAll_fileByFile_no_name(testAll_file);

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if( all_file == null ){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("delete.file.failure.code")),
                    PropertiesUtil.getProperty("delete.file.failure.msg"),null);
            return responseInfoDTO;
        }else {
            Integer nid = all_file.getNid();
            sqlMapper.deleteStudentAll_file(nid);//直接将数据库数据删除

            //消息提示工具类获取key// 正确码,字符型转为整型
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("delete.file.success.code")),
                    PropertiesUtil.getProperty("delete.file.success.msg"),null);
            return responseInfoDTO;
        }
    }


    /**学生文件上传**/
    @PostMapping(value = "/student_upload",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    @ResponseBody
    public ResponseInfoDTO<Assign> student_upload(HttpServletRequest request, HttpServletResponse rsp)  {

        String s_id = request.getParameter("s_id");
        String c_no_hw = request.getParameter("c_no_hw");
        String file_no = c_no_hw +"-"+ s_id;

        Score score = new Score(s_id, c_no_hw, null, file_no);
        score.toString();
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");//获取文件
        Assign assign = sqlMapper.findAssignByHW(c_no_hw);

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(assign == null){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("upload.file.failure_4.code")),
                    PropertiesUtil.getProperty("upload.file.failure_4.msg"),null);
            return responseInfoDTO;
        }else {
            MultipartFile file = null;
            BufferedOutputStream stream = null;
            //设置保存路径D:/aim/课程ID/老师ID/课程开课ID/学生ID/学生作业文件
            String filePath = "D://aim//" +assign.getC_no()+ "//" +assign.getT_id()+ "//" +c_no_hw+ "//" +s_id+ "//";

            File file_isExists = new File(filePath);
            if( !file_isExists.exists()){//不存在该文件夹时，就创建
                file_isExists.mkdirs();
            }

            for (int i = 0; i < files.size(); ++i) {
                file = files.get(i);

                if (!file.isEmpty()) {
                    try {
                        byte[] bytes = file.getBytes();
                        stream = new BufferedOutputStream(new FileOutputStream(
                                new File(filePath + file.getOriginalFilename())));//设置文件路径及名字

                        /*Date date=new Date();
                        DateFormat format= new SimpleDateFormat("yyyyMMdd");
                        String create_time = format.format(date);*/

                        All_file all_file = new All_file(file_no,filePath + file.getOriginalFilename(),file.getOriginalFilename());
                        sqlMapper.addStudentAll_file(all_file);

                        stream.write(bytes);// 写入
                        stream.close();
                    } catch (Exception e) {
                        stream = null;
                        responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                                PropertiesUtil.getProperty("upload.file.failure_1.code")),
                                "第 " + (i+1) + PropertiesUtil.getProperty("upload.file.failure_1.msg") + e.getMessage(),null);
                        return responseInfoDTO;
                    }
                } else {
                    responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                            PropertiesUtil.getProperty("upload.file.failure_2.code")),
                            "第 " + (i+1) + PropertiesUtil.getProperty("upload.file.failure_2.msg"),null);
                    return responseInfoDTO;
                }
            }
        }
        sqlMapper.addScoreHW(score);

        responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                PropertiesUtil.getProperty("upload.file.success.code")),
                PropertiesUtil.getProperty("upload.file.success.msg"),assign);
        return responseInfoDTO;
    }


    /**获取学生作业信息**/
    @PostMapping(value = "/getS_Homework",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public List<Teach_Student_FileDTO> showStudentHomework (@RequestBody String params, HttpServletRequest request,
                                                  HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String t_id = paramJson.get("t_id").textValue();

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        List<Teaching> teachings = sqlMapper.findTeachingALLByT_id(t_id);

        List<Teach_Student_FileDTO> teach_student_fileDTOS = new ArrayList<>();
        for(Teaching ts : teachings){
            Teach_Student_FileDTO teach_student_fileDTO = new Teach_Student_FileDTO();
            teach_student_fileDTO.setC_name(sqlMapper.findCourseNameByC_no(ts.getC_no()));

            List<Assign_Score> assign_scores = sqlMapper.findAssignByIDAndC_no2(ts);

                for(Assign_Score as : assign_scores){

                    List<Score_File> score_files = sqlMapper.findScore_FileByC_no_hw(as.getC_no_hw());
                        for(Score_File sf : score_files){


                            List<All_file> all_files = sqlMapper.findAll_file_AllByAll(sf.getFile_no());
                            sf.setAll_files(all_files);
                        }

                    as.setScore_files(score_files);

                }

            teach_student_fileDTO.setAssign_scores(assign_scores);
            teach_student_fileDTOS.add(teach_student_fileDTO);
        }
        return teach_student_fileDTOS;
    }

    /**老师将学生作业文件下载     模式1**/
    @PostMapping(value = "/teacher_download_1",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> teacher_download_1 (HttpServletRequest request, HttpServletResponse rsp) {

        String file_no = request.getParameter("file_no");//作业文件存储编号
        String file_name = request.getParameter("file_name");//作业文件存储编号

        return helpTeacherReturnDownload(file_no, file_name, rsp);
    }

    /**老师将学生作业文件下载     模式2**/
    @PostMapping(value = "/teacher_download_2",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> teacher_download_2 (@RequestBody String params, HttpServletRequest request,
                                                     HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String file_no = paramJson.get("file_no").textValue();
        String file_name = paramJson.get("file_name").textValue();

        return helpTeacherReturnDownload(file_no, file_name, rsp);
    }

    public ResponseInfoDTO<UserSimpleDTO> helpTeacherReturnDownload (String file_no,String file_name,HttpServletResponse rsp){

        All_file testAll_file = new All_file(file_no, null, file_name);
        All_file all_file = sqlMapper.findAll_fileByFile_no_name(testAll_file);

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;

        if(all_file == null){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("download.file.failure_2.code")),
                    PropertiesUtil.getProperty("download.file.failure_2.msg"),null);
            return responseInfoDTO;
        }else{
            String filePath = all_file.getAddress();
            File file = new File(filePath);

            System.out.println(file_name+"  "+filePath);

            helpDownload(file, file_name, rsp);//调用文件地址服务器下载函数

            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("download.file.success.code")),
                    PropertiesUtil.getProperty("download.file.success.msg"),null);
            return responseInfoDTO;
        }
    }

    /**********************************普通员工操作**********************************/
    /**用户登录管理**/
    @PostMapping(value = "/login",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> userLogin(@RequestBody String params, HttpServletRequest request,
                                                  HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        //将json格式的数据解析成类的对象

        String account = paramJson.get("account").textValue();
        String password = paramJson.get("password").textValue();
        String radio = paramJson.get("radio").textValue();
        //获取对象账号，密码
        UserSimpleDTO userSimpleDTO;
        if(radio.equals("学生")){
            userSimpleDTO = sqlMapper.findStudentByAccount(account);
        }else{
            userSimpleDTO = sqlMapper.findTeacherByAccount(account);
        }

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(userSimpleDTO == null){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("getone.failure.code")), PropertiesUtil.getProperty("getone.failure.msg"),null);
            return responseInfoDTO;
        }else if(userSimpleDTO.getPassword().equals(password)){
            //消息提示工具类获取key// 正确码,字符型转为整型
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("login.success.code")), PropertiesUtil.getProperty("login.success.msg"),userSimpleDTO);
            return responseInfoDTO;
        }else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(PropertiesUtil.getProperty("login.failure.code")), PropertiesUtil.getProperty("login.failure.msg"),null);
            return responseInfoDTO;
        }
    }


}
