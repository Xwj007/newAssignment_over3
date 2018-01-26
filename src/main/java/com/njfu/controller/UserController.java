package com.njfu.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.njfu.DTO.ResponseInfoDTO;
import com.njfu.DTO.UserInfoDTO;
import com.njfu.DTO.UserSimpleDTO;
import com.njfu.entity.Staff;
import com.njfu.entity.User;
import com.njfu.mapper.UserMapper;
import com.njfu.utils.PropertiesUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;


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
                                HttpServletRequest request, HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        User user = new User(paramJson.get("account").textValue(),paramJson.get("password").textValue());

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        List<User> newUser = userMapper.Query();
        return newUser;
    }

    /**获取个人信息**/
    @PostMapping(value = "/getOne",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> getOneResult(@RequestBody String params, HttpServletRequest request,
                                                     HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);

        User newUser = userMapper.findUserByAccount(paramJson.get("account").textValue());
        Staff newStaff = userMapper.findStaffById(newUser.getStaff_id());
        //System.out.println(newStaff.toString());

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if( newUser == null ){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("getone.failure.code")),
                    PropertiesUtil.getProperty("getone.failure.msg"),null);
            return responseInfoDTO;
        }else {
            UserInfoDTO userInfoDTO = new UserInfoDTO(newStaff.getStaff_id(),newStaff.getPower(),
                    newStaff.getWork(),newUser.getAccount(),newUser.getPassword(),newUser.getPhone(),
                    newUser.getLast_update(),newUser.getAddress(),newUser.getEmail());

            //消息提示工具类获取key// 正确码,字符型转为整型
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("getone.success.code")),
                    PropertiesUtil.getProperty("getone.success.msg"),userInfoDTO);
            return responseInfoDTO;
        }
    }

    /**添加用户信息**/
    @PostMapping(value = "/insert",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> insertResult(@RequestBody String params, HttpServletRequest request,
                                                     HttpServletResponse rsp) throws IOException {

        Date date=new Date();
        DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String create_time = format.format(date);

        ObjectMapper objectMapper = new ObjectMapper();
        System.out.println(params);
        JsonNode paramJson = objectMapper.readTree(params);
        User user = new User(paramJson.get("account").textValue(),
                paramJson.get("password").textValue(),
                paramJson.get("staff_id").textValue(),
                paramJson.get("phone").textValue(),
                create_time,
                paramJson.get("address").textValue(),
                paramJson.get("email").textValue());

        System.out.println(user.toString());

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
        String newPassword = paramJson.get("new_password").textValue();

        User newUser = userMapper.findUserByAccount(account);

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if( newUser != null ){
            if(newUser.getPassword().equals(old_password)){
                newUser.setPassword(newPassword);
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

    /**更新用户信息--修改用户信息**/
    @PostMapping(value = "/update_info",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> update_info_Result(@RequestBody String params, HttpServletRequest request,
                                                           HttpServletResponse rsp) throws IOException {

        Date date=new Date();
        DateFormat format= new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String create_time = format.format(date);

        ObjectMapper objectMapper = new ObjectMapper();
        JsonNode paramJson = objectMapper.readTree(params);
        User user = new User(paramJson.get("account").textValue(),
                "",
                "",
                paramJson.get("phone").textValue(),
                create_time,
                paramJson.get("address").textValue(),
                paramJson.get("email").textValue());

        System.out.println(user.toString());

        User newUser = userMapper.findUserByAccount(user.getAccount());
        System.out.println(newUser.toString());

        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if( newUser != null ){
            newUser.setPhone(user.getPhone());
            newUser.setAddress(user.getAddress());
            newUser.setLast_update(user.getLast_update());
            newUser.setEmail(user.getEmail());
            userMapper.update_info(newUser);


            UserSimpleDTO userSimpleDTO = new UserSimpleDTO(newUser.getAccount(),newUser.getPassword());
            //消息提示工具类获取key// 正确码,字符型转为整型
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("update.info.success.code")),
                    PropertiesUtil.getProperty("update.info.success.msg"),userSimpleDTO);
            return responseInfoDTO;
        }else {
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("update.info.failure_1.code")),
                    PropertiesUtil.getProperty("update.info.failure_1.msg"),null);
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
        String staff_id = paramJson.get("staff_id").textValue();

        User newUser = userMapper.findUserByAccount(account);//扩展接口,以account查找
        rsp.setHeader("Access-Control-Allow-Methods", "POST");
        ResponseInfoDTO responseInfoDTO;
        if(newUser == null){
            responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                    PropertiesUtil.getProperty("delete.failure_1.code")),
                    PropertiesUtil.getProperty("delete.failure_1.msg"),null);
            return responseInfoDTO;
        }else {
            if(newUser.getStaff_id().equals(staff_id)){
                Integer id = newUser.getId();
                userMapper.delete(id);//直接将数据库数据删除

                UserSimpleDTO userSimpleDTO = new UserSimpleDTO(newUser.getAccount(),newUser.getPassword());
                //消息提示工具类获取key// 正确码,字符型转为整型
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                        PropertiesUtil.getProperty("delete.success.code")),
                        PropertiesUtil.getProperty("delete.success.msg"),userSimpleDTO);
                return responseInfoDTO;
            }else{
                responseInfoDTO = new ResponseInfoDTO(Integer.valueOf(
                        PropertiesUtil.getProperty("delete.failure_2.code")),
                        PropertiesUtil.getProperty("delete.failure_2.msg"),null);
                return responseInfoDTO;
            }
        }
    }


    /**********************************普通员工操作**********************************/
    /**用户登录管理**/
    @PostMapping(value = "/login",produces="application/json;charset=UTF-8")
    @CrossOrigin(allowCredentials = "false")
    public ResponseInfoDTO<UserSimpleDTO> userLogin(@RequestBody String params, HttpServletRequest request,
                                                  HttpServletResponse rsp) throws IOException {

        ObjectMapper objectMapper = new ObjectMapper();
        //System.out.println(params);
        JsonNode paramJson = objectMapper.readTree(params);

        //将json格式的数据解析成类的对象
        //System.out.println(paramJson.get("account").textValue());
        //System.out.println(paramJson.get("password").textValue());
        String account = paramJson.get("account").textValue();
        String password = paramJson.get("password").textValue();

        User newUser = userMapper.findUserByAccount(account);//扩展接口,以account查找

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
