package com.asiainfo.crm.common.exception;

import com.asiainfo.crm.common.model.HttpCode;
import com.asiainfo.crm.common.model.RestResult;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 统一异常处理
 */
@ControllerAdvice
public class GlobalExceptionHandler extends AbstactExceptionHandler {

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
        r.setCode(HttpCode.SERVER_ERROR_CODE.getCode());
        r.setData(req.getRequestURL().toString());
        return r;
    }

    @ExceptionHandler(value = RestfulException.class)
    @ResponseBody
    public RestResult<String> restfulException(HttpServletRequest req, RestfulException e) throws Exception {
        RestResult<String> r = new RestResult<>();
        r.setMessage(getErrorInfoFromException(e));
        r.setCode(e.getRestFulExceptionCode().getCode());
        r.setData(req.getRequestURL().toString());
        HttpServletResponse response = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getResponse();
        response.addHeader("httpCode", String.valueOf(e.getRestFulExceptionCode().getHttpCode().getCode()));
        return r;
    }


}


