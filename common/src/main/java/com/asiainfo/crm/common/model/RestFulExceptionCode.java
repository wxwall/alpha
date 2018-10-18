package com.asiainfo.crm.common.model;

/**
 * Created by wuxiaowei on 2018/10/17
 */
public enum RestFulExceptionCode {

    PARAM_ERROR(201300,HttpCode.PARAM_ERROR_CODE,"异常信息1"),
    TOKEN_EXCEPTION(401300,HttpCode.TOKEN_TIMEOUT_CODE,"异常信息2"),
    NOT_FOUND_EXCEPTION(201122,HttpCode.NOT_FOUND,"404找不到");

    //自定义异常编码
    private int code;

    //对应的异常httpCode，crm3.0规范中restful异常报文头需要设置httpCode
    private HttpCode httpCode;

    //异常信息
    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public HttpCode getHttpCode() {
        return httpCode;
    }

    public void setHttpCode(HttpCode httpCode) {
        this.httpCode = httpCode;
    }

    public String getMessage() {
        return message;
    }

    RestFulExceptionCode(int code, HttpCode httpCode, String message) {
        this.code = code;
        this.httpCode = httpCode;
        this.message = message;
    }
}
