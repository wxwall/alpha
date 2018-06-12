package com.asiainfo.crm.common.aspect;

import com.asiainfo.crm.common.annotation.SysLog;
import com.asiainfo.crm.common.model.SysLogEntity;
import com.asiainfo.crm.common.utils.HttpContextUtils;
import com.asiainfo.crm.common.utils.IPUtils;
import com.google.gson.Gson;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.Date;


/**
 * 系统日志，切面处理类
 */
@Aspect
@Component
public class SysLogAspect {


	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Pointcut("@annotation(com.asiainfo.crm.common.annotation.SysLog)")
	public void logPointCut() { 
		
	}
	



	@Around(value = "logPointCut()")
	public void around(ProceedingJoinPoint joinPoint) throws  Throwable{
		MethodSignature signature = (MethodSignature) joinPoint.getSignature();
		Method method = signature.getMethod();

		SysLogEntity sysLog = new SysLogEntity();
		SysLog syslog = method.getAnnotation(SysLog.class);
		if(syslog != null){
			//注解上的描述
			sysLog.setOperation(syslog.value());
		}

		//请求的方法名
		String className = joinPoint.getTarget().getClass().getName();
		String methodName = signature.getName();
		sysLog.setMethod(className + "." + methodName + "()");

		//请求的参数
		Object[] args = joinPoint.getArgs();
		if(args.length > 0){
			String params = new Gson().toJson(args[0]);
			sysLog.setParams(params);
		}

		//获取request
		HttpServletRequest request = HttpContextUtils.getHttpServletRequest();
		//设置IP地址
		sysLog.setIp(IPUtils.getIpAddr(request));

		//用户名
		//String username = ((SysUserEntity) SecurityUtils.getSubject().getPrincipal()).getUsername();
		//sysLog.setUsername(username);

		Long beginTime = System.currentTimeMillis();
		sysLog.setCreateDate(new Date());


		joinPoint.proceed();

		//方法消耗时间
		Long currentTime = System.currentTimeMillis() - beginTime;
		sysLog.setCurrentTime(currentTime);

		//保存服务IP
		sysLog.setHostIP(InetAddress.getLocalHost().getHostAddress());

		//保存系统日志
		//sysLogService.save(sysLog);
		logger.info(sysLog.toString());

	}
}
