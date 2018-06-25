package com.asiainfo.crm.common.Interceptor;

import com.al.crm.cache.routing.RedisContext;
import com.asiainfo.crm.busi.RedisMDA;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 缓存拦截器，拦截对于areaId的设置
 * Created by wuxiaowei on 2018/6/25
 */
@SpringBootConfiguration
public class CacheInterceptorConfigurer extends WebMvcConfigurerAdapter {

    @Autowired
    RedisMDA redisMDA;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new CacheHandlerInterceptor()).addPathPatterns(redisMDA.getPathPattern());
    }

    class CacheHandlerInterceptor implements HandlerInterceptor{

        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
            checkAreaId(request);
            RedisContext.setRoutingId(request.getParameter("areaId"));
            return true;
        }

        private void checkAreaId(HttpServletRequest request) throws Exception {
            String areaId = request.getParameter("areaId");
            if(null != areaId){
                if (isNum(areaId)) {
                    throw new Exception("areaId必须是数字,请检查！");
                }
                if(areaId.length() != 6){
                    throw new Exception("areaId必须是6位,请检查！");
                }
            }else{
                throw new Exception("操作缓存入参缺少areaId,请检查！");
            }
        }

        //判断是否是数字
        private boolean isNum(String areaId) {
            Pattern pattern = Pattern.compile("[0-9]*");
            Matcher isNum = pattern.matcher(areaId);
            if( !isNum.matches() ){
                return true;
            }
            return false;
        }

        @Override
        public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler, ModelAndView modelAndView) throws Exception {

        }

        @Override
        public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        }
    }

}
