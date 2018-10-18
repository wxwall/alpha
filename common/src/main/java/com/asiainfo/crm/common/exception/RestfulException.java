package com.asiainfo.crm.common.exception;

import com.asiainfo.crm.common.model.RestFulExceptionCode;

/**
 * Created by wuxiaowei on 2018/10/17
 */
public class RestfulException extends RuntimeException {


    private RestFulExceptionCode restFulExceptionCode;

    public RestFulExceptionCode getRestFulExceptionCode() {
        return restFulExceptionCode;
    }

    public RestfulException(RestFulExceptionCode code) {
        super(code.getMessage());
        this.restFulExceptionCode = code;
    }


    public RestfulException(RestFulExceptionCode code, Throwable cause) {
        super(cause);
        this.restFulExceptionCode = code;
    }

    /**
     * 是否输出堆栈信息
     * @param code
     * @param cause
     * @param ifOutPutStackInformation true:输出，false：不输出   默认：true
     */
    public RestfulException(RestFulExceptionCode code, Throwable cause, Boolean ifOutPutStackInformation) {
        super(cause);
        this.restFulExceptionCode = code;
    }








}
