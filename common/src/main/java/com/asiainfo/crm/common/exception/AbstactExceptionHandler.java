package com.asiainfo.crm.common.exception;

import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * Created by wuxiaowei on 2018/10/17
 */
public abstract class AbstactExceptionHandler {


    /**
     * 将e.printStackTrace转成String输出
     * @param e
     * @return
     */
    public static String getErrorInfoFromException(Exception e) {
        try {
            StringWriter sw = new StringWriter();
            PrintWriter pw = new PrintWriter(sw);
            e.printStackTrace(pw);
            return "\r\n" + sw.toString() + "\r\n";
        } catch (Exception e2) {
            return "bad getErrorInfoFromException";
        }
    }
}
