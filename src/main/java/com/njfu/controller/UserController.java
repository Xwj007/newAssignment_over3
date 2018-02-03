package com.njfu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njfu.DTO.HWSimpleDTO;
import com.njfu.DTO.ResponseInfoDTO;
import com.njfu.DTO.UserSimpleDTO;
import com.njfu.entity.Homework;
import com.njfu.entity.User;
import com.njfu.mapper.UserMapper;
import com.njfu.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
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
    private UserMapper userMapper;//引入MyBatis接口

    /**********************************管理员操作**********************************/
    /**查询所有用户信息列表**/
    @PostMapping(value = "/all",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public List<User> allResult(@RequestBody String params,
                                HttpServletRequest request, HttpServletResponse rsp) {

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        List<User> newUser = userMapper.Query();
        return newUser;
    }

    /**添加用户信息**/
    @PostMapping(value = "/insert",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> insertResult(@RequestBody String params, HttpServletRequest request,
                                                       HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(params);
        JsonNode paramJson = objectMapper.readTree(params);

        String radio = paramJson.get("radio").textValue();
        if(radio.equals("学生")){
            radio = "0";
        }else{
            radio = "1";
        }
        User user = new User(paramJson.get("account").textValue(),
                paramJson.get("password").textValue(),
                radio, paramJson.get("nclass").textValue());

        //System.out.println(user.toString());

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if( userMapper.findUserByAccount(user.getAccount()) == null ){
            userMapper.insert(user);//添加


            UserSimpleDTO userSimpleDTO = new UserSimpleDTO(user.getAccount(),user.getPassword());
            //消息提示工具类获取key// 正确码,字符型转为整型
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("add.success.code")),
                    PropertiesUtil.getProperty("add.success.msg"),userSimpleDTO);
            return responseInfoDTO;
        }else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("add.failure.code")),
                    PropertiesUtil.getProperty("add.failure.msg"),null);
            return responseInfoDTO;
        }
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


        User newUser = userMapper.findUserByAccount(account);//新对象

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if( newUser != null ){
            if(newUser.getPassword().equals(old_password)){
                newUser.setPassword(new_Password);
                userMapper.update_pwd(newUser);

                UserSimpleDTO userSimpleDTO = new UserSimpleDTO(newUser.getAccount(),newUser.getPassword());
                //消息提示工具类获取key// 正确码,字符型转为整型
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                        PropertiesUtil.getProperty("update.pwd.success.code")),
                        PropertiesUtil.getProperty("update.pwd.success.msg"),userSimpleDTO);
                return responseInfoDTO;
            }else {
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                        PropertiesUtil.getProperty("update.pwd.failure_2.code")),
                        PropertiesUtil.getProperty("update.pwd.failure_2.msg"),null);
                return responseInfoDTO;
            }
        }else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("update.pwd.failure_1.code")),
                    PropertiesUtil.getProperty("update.pwd.failure_1.msg"),null);
            return responseInfoDTO;
        }
    }


    /**删除用户信息**/
    @PostMapping(value = "/delete",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> deleteResult(@RequestBody String params, HttpServletRequest request,
                                                       HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String account = paramJson.get("account").textValue();
        String nclass = paramJson.get("nclass").textValue();

        User newUser = userMapper.findUserByAccount(account);//扩展接口,以account查找
        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(newUser == null){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("delete.failure_1.code")),
                    PropertiesUtil.getProperty("delete.failure_1.msg"),null);
            return responseInfoDTO;
        }else {
            if(newUser.getNclass().equals(nclass)){
                Integer id = newUser.getId();
                userMapper.delete(id);//直接将数据库数据删除

                //消息提示工具类获取key// 正确码,字符型转为整型
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                        PropertiesUtil.getProperty("delete.success.code")),
                        PropertiesUtil.getProperty("delete.success.msg"),null);
                return responseInfoDTO;
            }else{
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                        PropertiesUtil.getProperty("delete.failure_2.code")),
                        PropertiesUtil.getProperty("delete.failure_2.msg"),null);
                return responseInfoDTO;
            }
        }
    }


    /**文件上传**/
    @PostMapping(value = "/teacher_upload",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    @ResponseBody
    public ResponseInfoDTO<UserSimpleDTO> upload(HttpServletRequest request, HttpServletResponse rsp)  {

        String t_account = request.getParameter("t_account");
        List<MultipartFile> files = ((MultipartHttpServletRequest) request).getFiles("file");//获取文件

        User oldUser = new User();
        oldUser.setAccount(t_account);//老师上传文件
        oldUser.setWork("1");
        User newUser = userMapper.findUserByOther(oldUser);

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(newUser == null){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("upload.file.failure_3.code")),
                    PropertiesUtil.getProperty("upload.file.failure_3.msg"),null);
            return responseInfoDTO;
        }else {
            MultipartFile file = null;
            BufferedOutputStream stream = null;
            //设置保存路径D:/aim/老师姓名/文件
            String filePath = "D://aim//" +t_account+ "//";
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

                        Date date=new Date();
                        DateFormat format= new SimpleDateFormat("yyyyMMdd");
                        String create_time = format.format(date);

                        Homework homework = new Homework(t_account,create_time,file.getOriginalFilename(),filePath + file.getOriginalFilename());
                        userMapper.insertHomework(homework);
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
        UserSimpleDTO userSimpleDTO = new UserSimpleDTO(newUser.getAccount(),newUser.getPassword());
        responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                PropertiesUtil.getProperty("upload.file.success.code")),
                PropertiesUtil.getProperty("上传成功"),userSimpleDTO);
        return responseInfoDTO;
    }

    /**获取老师作业信息**/
    @PostMapping(value = "/getT_Homework",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public List<HWSimpleDTO> showTeacherHomework (@RequestBody String params, HttpServletRequest request,
                                                     HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String s_account = paramJson.get("s_account").textValue();

        rsp.setHeader("Access-Control-Allow-Methods", "POST");

        User user = userMapper.findUserByAccount(s_account);
        List<String> t_accounts = userMapper.getTeacherList(user);

        List<HWSimpleDTO> hwSimpleDTOS =  new ArrayList<>();
        for(String teacher : t_accounts){ // teacher就是遍历的每一个String对象
            HWSimpleDTO hwSimpleDTO = new HWSimpleDTO(teacher);
            hwSimpleDTOS.add(hwSimpleDTO);
        }

        for(HWSimpleDTO hws : hwSimpleDTOS){
            hws.setFielname(userMapper.getHomeworkList(hws.getAccount()));
        }
        return hwSimpleDTOS;
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

    public ResponseInfoDTO<UserSimpleDTO> helpReturnDownload (String t_account,String fileName,HttpServletResponse rsp){
        Homework oldHomework = new Homework();
        oldHomework.setT_account(t_account);
        oldHomework.setFilename(fileName);
        Homework newHomework = userMapper.findHomeworkByOther(oldHomework);

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(newHomework == null){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("download.file.failure_1.code")),
                    PropertiesUtil.getProperty("download.file.failure_1.msg"),null);
            return responseInfoDTO;
        }else{
            String filePath  = newHomework.getAddress();
            File file = new File(filePath);

            helpDownload(file, fileName, rsp);//调用文件地址服务器下载函数
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("download.file.success.code")),
                    PropertiesUtil.getProperty("download.file.success.msg"),null);
            return responseInfoDTO;
        }
    }

    /**文件下载     模式1**/
    @PostMapping(value = "/student_download_1",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> download1 (HttpServletRequest request, HttpServletResponse rsp) {

        String t_account = request.getParameter("t_account");//老师账号
        String fileName = request.getParameter("file");//文件名

        return helpReturnDownload(t_account, fileName, rsp);
    }

    /**文件下载     模式2**/
    @PostMapping(value = "/student_download_2",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> download2 (@RequestBody String params, HttpServletRequest request,
                          HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        String t_account = paramJson.get("t_account").textValue();
        String fileName = paramJson.get("file").textValue();

        return helpReturnDownload(t_account, fileName, rsp);
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
        //System.out.println(paramJson.get("account").textValue());
        //System.out.println(paramJson.get("password").textValue());
        String account = paramJson.get("account").textValue();
        String password = paramJson.get("password").textValue();

        User oldUser = new User();
        oldUser.setAccount(account);
        User newUser = userMapper.findUserByOther(oldUser);

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(newUser == null){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("getone.failure.code")),
                    PropertiesUtil.getProperty("getone.failure.msg"),null);
            return responseInfoDTO;
        }else if(newUser.getPassword().equals(password)){
            UserSimpleDTO userSimpleDTO = new UserSimpleDTO(newUser.getAccount(),newUser.getPassword());
            //消息提示工具类获取key// 正确码,字符型转为整型
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("login.success.code")),
                    PropertiesUtil.getProperty("login.success.msg"),userSimpleDTO);
            return responseInfoDTO;
        }else{
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("login.failure.code")),
                    PropertiesUtil.getProperty("login.failure.msg"),null);
            return responseInfoDTO;
        }
    }


}
