package com.asiainfo.crm.common.model;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;

import java.io.Serializable;
import java.util.Date;


/**
 * 系统日志
 */
@JsonSerialize(include=JsonSerialize.Inclusion.NON_EMPTY)
public class SysLogEntity implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private Long id;
	//用户名
	private String username;
	//用户操作
	private String operation;
	//服务主机
	private String hostIP;
	//请求方法
	private String method;
	//请求参数
	private String params;
	//IP地址
	private String ip;
	//创建时间
	private Date createDate;
	//花费时间
	private Long currentTime;

	/**
	 * 设置：
	 */
	public void setId(Long id) {
		this.id = id;
	}
	/**
	 * 获取：
	 */
	public Long getId() {
		return id;
	}
	/**
	 * 设置：用户名
	 */
	public void setUsername(String username) {
		this.username = username;
	}
	/**
	 * 获取：用户名
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * 设置：用户操作
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}
	/**
	 * 获取：用户操作
	 */
	public String getOperation() {
		return operation;
	}
	/**
	 * 设置：请求方法
	 */
	public void setMethod(String method) {
		this.method = method;
	}
	/**
	 * 获取：请求方法
	 */
	public String getMethod() {
		return method;
	}
	/**
	 * 设置：请求参数
	 */
	public void setParams(String params) {
		this.params = params;
	}
	/**
	 * 获取：请求参数
	 */
	public String getParams() {
		return params;
	}
	/**
	 * 设置：IP地址
	 */
	public void setIp(String ip) {
		this.ip = ip;
	}
	/**
	 * 获取：IP地址
	 */
	public String getIp() {
		return ip;
	}
	/**
	 * 设置：创建时间
	 */
	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	/**
	 * 获取：创建时间
	 */
	public Date getCreateDate() {
		return createDate;
	}

	public Long getCurrentTime() {
		return currentTime;
	}

	public void setCurrentTime(Long currentTime) {
		this.currentTime = currentTime;
	}

	public String getHostIP() {
		return hostIP;
	}

	public void setHostIP(String hostIP) {
		this.hostIP = hostIP;
	}

	@Override
	public String toString() {
		return "SysLogEntity{" +
				"id=" + id +
				", username='" + username + '\'' +
				", operation='" + operation + '\'' +
				", hostIP='" + hostIP + '\'' +
				", method='" + method + '\'' +
				", params='" + params + '\'' +
				", ip='" + ip + '\'' +
				", createDate=" + createDate +
				", currentTime=" + currentTime +
				'}';
	}
}
