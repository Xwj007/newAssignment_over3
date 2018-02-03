## 学校网上作业布置开发文档
---

### 一、登录管理
#### 1. 用户登陆
* **接口名称：** `/login`
* **接口描述：** `用户登录`
* **请求协议：** `HTTP post + json`
* **传入参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`account` | `String` | 是 | 登录用户名
`password` | `String` | 是 | 登录用户密码

**请求示例**
```json
{
    "account" : "登录用户名",
    "password" : "登录密码"
}
```

* **返回参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`code` | `int` | 是 | 等于0：正确；小于0：错误
`msg` | `String` | 是 | 返回结果说明
`data` | `object` | 是 | 请求的返回结果
`token` | `String` | 是 | 登录鉴权token，包括隶属公司数据库

**返回码说明**

返回码(code) | 说明(msg)
---|---
`0`|`登录成功`
`-1`|`登录失败，账号或密码存在错误，请重新输入`

**返回示例**
```json
{
    "code" : 0,
    "msg" : "登录成功" ,
    "data" : {
        "token": "登录鉴权token，包括隶属公司数据库"
    }
}
```

#### 2.人员添加
* **接口名称：** `/insert`
* **接口描述：** `学生或者老师添加`
* **请求协议：** `HTTP post + json`
* **传入参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`account` | `String` | 是 | 用户名
`password` | `String` | 是 | 用户密码
`nclass` | `String` | 是 | 班级
`work` | `String` | 是 | 学生或者老师


**请求示例**
```json
{
    "account" : "用户名",
    "password" : "用户密码",
    "nclass" : "班级",
    "work" : "学生或者老师"
}
```

* **返回参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`code` | `int` | 是 | 等于1：正确；等于-100：错误
`msg` | `String` | 是 | 返回结果说明
`data` | `object` | 是 | 请求的返回结果

**返回码说明**

返回码(code) | 说明(msg)
---|---
`1`|`添加成功`
`-100`|`添加失败，账号已存在`

**返回示例**
```json
{
    "code" : 1,
    "msg" : "添加成功" ,
    "data" : {
        "account" : "用户名",
        "password" : "用户密码"
    }
}
```


#### 3.人员删除
* **接口名称：** `/delete`
* **接口描述：** `学生或者老师删除`
* **请求协议：** `HTTP post + json`
* **传入参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`staff_id` | `String` | 是 | 用户ID
`nclass` | `String` | 是 | 班级


**请求示例**
```json
{
    "account" : "登录用户名",
    "nclass" : "班级"
}
```

* **返回参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`code` | `int` | 是 | 等于2：正确；等于-200和-201：错误
`msg` | `String` | 是 | 返回结果说明
`data` | `object` | 是 | 请求的返回结果

**返回码说明**

返回码(code) | 说明(msg)
---|---
`2`|`删除成功`
`-200`|`删除失败，账号不存在`
`-201`|`删除失败，班级不正确`

**返回示例**
```json
{
    "code" : 2,
    "msg" : "删除成功" ,
    "data" : {
        null
    }
}
```



#### 4.人员密码修改
* **接口名称：** `/update_pwd`
* **接口描述：** `人员密码修改`
* **请求协议：** `HTTP post + json`
* **传入参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`account` | `String` | 是 | 登录用户名(隐藏不让修改)
`old_password` | `String` | 是 | 登录旧密码
`new_password` | `String` | 是 | 新密码

**请求示例**
```json
{
    "account" : "登录用户名",
    "old_password" : "登录旧密码",
    "new_password" : "新密码"
}
```

* **返回参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`code` | `int` | 是 | 等于4：正确；等于-400和-401：错误
`msg` | `String` | 是 | 返回结果说明
`data` | `object` | 是 | 请求的返回结果

**返回码说明**

返回码(code) | 说明(msg)
---|---
`4`|`更新密码成功`
`-400`|`更新密码失败，用户不存在`
`-401`|`更新密码失败，原密码错误`

**返回示例**
```json
{
    "code" : 4,
    "msg" : "更新密码成功" ,
    "data" : {
        "account" : "用户名",
        "password" : "新密码"
    }
}
```

### 二、系统功能
#### 1.老师文件上传
* **接口名称：** `/teacher_upload`
* **接口描述：** `老师上传作业到数据库`
* **请求协议：** `HTTP post`
* **传入参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`t_account` | `String` | 是 | 老师用户名
`t_file` | `File` | 是 | 作业文件(可以多个上传)


**请求示例**
```
{
    "t_account" : "老师用户名",
    "t_file" : "作业文件"
}
```

* **返回参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`code` | `int` | 是 | 等于6：正确；小于-600：错误
`msg` | `String` | 是 | 返回结果说明
`data` | `object` | 是 | 请求的返回结果

**返回码说明**

返回码(code) | 说明(msg)
---|---
`6`|上传成功
`-601`|个文件上传失败  ==>
`-602`|个文件上传失败因为文件为空
`-603`|上传失败，老师账号不存在
`-604`|上传失败，学生账号不存在



**返回示例**
```json
{
    "code" : 6,
    "msg" : "上传成功" ,
    "data" : {
        null
    }
}
```

#### 2.学生文件下载
* **接口名称：** `/student_download`
* **接口描述：** `学生从服务器中下载作业`
* **请求协议：** `HTTP post + json`
* **传入参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`t_account` | `String` | 是 | 学生账号
`file` | `String` | 是 | 文件名


**请求示例**
```json
{
    "t_account" : "学生账号",
    "file" : "文件名"
}
```

* **返回参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`code` | `int` | 是 | 等于7：正确；小于-700：错误
`msg` | `String` | 是 | 返回结果说明
`data` | `object` | 是 | 请求的返回结果

**返回码说明**

返回码(code) | 说明(msg)
---|---
`7`|下载成功
`-701`|下载失败，老师作业不存在
`-702`|下载失败，学生账号不存在

**返回示例**
```json
{
    "code" : 7,
    "msg" : "下载成功" ,
    "data" : {
        null
    }
}

文件下载(输出流下载)
```


#### 3.学生文件上传
* **接口名称：** `/student_upload`
* **接口描述：** `学生上交作业`
* **请求协议：** `HTTP post`
* **传入参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`s_account` | `String` | 是 | 学生用户名
`t_file` | `File` | 是 | 作业文件(可以多个上传)


**请求示例**
```
{
    "s_account
    " : "学生用户名",
    "t_file" : "作业文件"
}
```

* **返回参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`code` | `int` | 是 | 等于6：正确；小于-600：错误
`msg` | `String` | 是 | 返回结果说明
`data` | `object` | 是 | 请求的返回结果

**返回码说明**

返回码(code) | 说明(msg)
---|---
`6`|上传成功
`-601`|个文件上传失败  ==>
`-602`|个文件上传失败因为文件为空
`-603`|上传失败，老师账号不存在
`-604`|上传失败，学生账号不存在


**返回示例**
```json
{
    "code" : 6,
    "msg" : "上传成功" ,
    "data" : {
        null
    }
}
```

#### 4.评审作业
* **接口名称：** `/teacher_download`
* **接口描述：** `老师下载所有作业`
* **请求协议：** `HTTP post + json`
* **传入参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`account` | `String` | 是 | 老师用户名
`nclass` | `String` | 是 | 班级


**请求示例**
```json
{
    "account" : "老师用户名",
    "nclass" : "班级"
}
```

* **返回参数**

参数名称 | 类型 | 是否必填 | 备注
---|---|---|---
`code` | `int` | 是 | 等于7：正确；小于-700：错误
`msg` | `String` | 是 | 返回结果说明
`data` | `object` | 是 | 请求的返回结果

**返回码说明**

返回码(code) | 说明(msg)
---|---
`7`|下载成功
`-701`|下载失败，老师作业不存在
`-702`|下载失败，学生账号不存在


**返回示例**
```json
{
    "code" : 7,
    "msg" : "下载成功" ,
    "data" : {
        null
    }
}

文件下载(输出流下载)
```

## 开发人员
---
### HTML前端开发
* **人员** `陶国玲 + 许文娇`
* **分工**

### java服务端开发
* **人员** `lvxz`
* **分工**

### 数据库开发
* **人员** `once + lvxz`
* **分工**



