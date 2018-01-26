package com.njfu.aspect;

import com.njfu.entity.User;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;

/**处理文件
 * 显示访问请求信息
 */
@Aspect
@Component  //将文件引入spring容器
public class HttpAspect {

    //选择logger日志进行输出
    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    //对HttpAspect类进行logger日志跟踪记录

    //集合层函数
    @Pointcut("execution(public * com.njfu.controller.UserController.*(..))")
    public void flag() {
    }

    @Before("flag()")
    //对文件名下的函数进行调用Before拦截
    public void loginBefore(JoinPoint jP) {
        //记录http请求//URL//method//IP//类方法//参数
        //强制转换
        ServletRequestAttributes SRattributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        HttpServletRequest HSrequest = SRattributes.getRequest();
        //URL
        logger.info("URL={}",HSrequest.getRequestURL());//在括号中输出
        //method
        logger.info("method={}",HSrequest.getMethod());
        // IP
        logger.info("IP={}",HSrequest.getRemoteAddr());
        // 类方法
        logger.info("class_method={}",jP.getSignature().getDeclaringType()+"."+jP.getSignature().getName());//获取类名
        // 参数
        logger.info("args={}",jP.getArgs());
    }

    //对文件名下的函数进行结束After拦截
    @After("flag()")
    public void loginAfter() {
        logger.info("--函数进行结束After拦截");
    }

}
