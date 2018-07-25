package com.asiainfo.crm.common.exception;

/**
 * Created by wuxiaowei on 2018/7/25
 */
public class SeqException extends RuntimeException {

    public SeqException() {
    }

    public SeqException(String message) {
        super(message);
    }

    public SeqException(String message, Throwable cause) {
        super(message, cause);
    }

    public SeqException(Throwable cause) {
        super(cause);
    }
}
