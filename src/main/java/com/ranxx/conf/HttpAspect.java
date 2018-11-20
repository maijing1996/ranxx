package com.ranxx.conf;

import javax.servlet.http.HttpServletRequest;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.alibaba.fastjson.JSONObject;

@Aspect
@Component
public class HttpAspect {

    private final static Logger logger = LoggerFactory.getLogger(HttpAspect.class);
    
    /**
     * 定义wechat端接口的 扫描路径
     */
    @Pointcut("execution(public * com.shop.controller..*.*(..))")
    public void wechat(){}
    
    /**
     * 定义系统后台接口的 扫描路径
     */
    @Pointcut("execution(public * com.shop.controller..*.*(..))")
    public void admin(){}
    
    /**
     * 定义AOP扫描方法
     * 打印日志
     */
    @Pointcut("admin() || wechat()")
    public void log(){}
    
    /**
     * 记录HTTP请求开始时的日志
     */
    @Before("log()")
    public void doBefore(JoinPoint joinPoint){
    	ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
    	if(attributes != null){
    		HttpServletRequest request = attributes.getRequest();
    		logger.info("---------------------------------------------------------");
    		//URL
    		logger.info("--------: url = {}", request.getRequestURI());
    		//method
    		logger.info("--------: method = {}", request.getMethod());
    		//ip
    		logger.info("--------: ip = {}", request.getRemoteAddr());
    		//类名
    		logger.info("--------: class = {}", joinPoint.getSignature().getDeclaringTypeName());
    		//方法名
    		logger.info("--------: name = {}", joinPoint.getSignature().getName());
    		//参数
    		logger.info("--------: params = {}", joinPoint.getArgs());
    		logger.info("---------------------------------");
    	}
    }

    /**
     * 记录HTTP请求结束时的日志
     */
    @After("log()")
    public void doAfter(){
        //ServletRequestAttributes attributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        //HttpServletRequest request = attributes.getRequest();
        //logger.info("--------: url = {} end of execution", request.getRequestURL());
    }

    /**
     * 获取返回内容
     * @param object
     */
    @AfterReturning(returning = "object",pointcut = "log()")
    public void doAfterReturn(JoinPoint joinPoint, Object object){
    	logger.info("---------------------------------");
        logger.info("--------: response={}", JSONObject.toJSON(object));
        logger.info("---------------------------------------------------------");
    }
}
