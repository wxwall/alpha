package com.asiainfo.common.exception;

import com.asiainfo.common.model.ResponseCode;
import com.asiainfo.common.model.RestResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.PrintWriter;
import java.io.StringWriter;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) throws Exception {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName("error");
        return mav;
    }

    @ExceptionHandler(value = BaseException.class)
    @ResponseBody
    public RestResult<String> jsonErrorHandler(HttpServletRequest req, BaseException e) throws Exception {
        RestResult<String> r = new RestResult<>();
        r.setMessage(getErrorInfoFromException(e));
        r.setCode(ResponseCode.SERVER_ERROR_CODE.getCode());
        r.setData(req.getRequestURL().toString());
        return r;
    }

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


