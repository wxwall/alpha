package com.asiainfo.controller;

import com.ai.dbua.route.DataSourceContextHolder;
import com.asiainfo.busi.BusiModel;
import com.asiainfo.busi.BusiSMO;
import com.asiainfo.busi.service.CtgMqInterface;
import com.asiainfo.busi.service.ICustQuerySMO;
import com.asiainfo.common.annotation.SysLog;
import com.asiainfo.common.controller.AbstractController;
import com.asiainfo.common.exception.BaseException;
import com.asiainfo.common.model.RestResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuxiaowei on 2018/5/3
 */
@Api(value="测试用Controller",tags={"一组操作实例"})
@RestController
public class Controller extends AbstractController {



    @Autowired
    BusiSMO busiSMO;
    @Autowired
    ICustQuerySMO custQuerySMO;

    @SysLog
    @ApiOperation(value="查询Demo",notes="用来查看服务是否正常启动")
    @RequestMapping(value = "/queryDemo" ,method = RequestMethod.GET)
    public RestResult queryDemo(){
        RestResult<BusiModel> restResult = new RestResult<BusiModel>();
        restResult.setMessage(busiSMO.queryDemo());
        return restResult;
    }

    @ApiOperation(value="查询配置",notes="检查配置是否从配置中心拿到数据了")
    @RequestMapping(value = "/queryConfigDemo" ,method = RequestMethod.GET)
    public RestResult queryConfigDemo(){
        RestResult<BusiModel> restResult = new RestResult<BusiModel>();
        restResult.setMessage(busiSMO.queryConfigDemo());
        return restResult;
    }
    @ApiOperation(value="查询Apollo配置",notes="检查配置是否从配置中心拿到数据了")
    @RequestMapping(value = "/queryConfigByApollo" ,method = RequestMethod.GET)
    public RestResult queryConfigByApollo(){
        RestResult<BusiModel> restResult = new RestResult<BusiModel>();
        restResult.setMessage(busiSMO.queryConfigByApollo());
        return restResult;
    }


    @ApiOperation(value="查询mysql数据",notes="从mysql中查询到数据并返回")
    @RequestMapping(value = "/queryJdbcDemo" ,method = RequestMethod.GET)
    public RestResult<BusiModel> queryJdbcDemo(@ApiParam(name="name",value = "名称") @RequestParam(value = "name")String name){
        RestResult<BusiModel> restResult = new RestResult<BusiModel>();
        logger.debug(busiSMO.queryJdbcDemo(name));
        restResult.setMessage(busiSMO.queryJdbcDemo(name));
        return restResult;
    }

    @ApiOperation(value="远程调用示例",notes="远程调用从其他微服务获得数据并返回")
    @RequestMapping(value = "/queryDemoForFeign" ,method = RequestMethod.GET)
    public RestResult<BusiModel> queryDemoForFeign(@ApiParam(name="name",value = "名称") @RequestParam(value = "name")String name){
        RestResult<BusiModel> restResult = new RestResult<BusiModel>();
        restResult = busiSMO.queryDemoForFeign(name);
        return restResult;
    }

    @ApiOperation(value="查询接口异常",notes="异常统一处理，并返回统一格式")
    @RequestMapping(value = "/queryExceptionDemo" ,method = RequestMethod.GET)
    public RestResult<BusiModel> queryExceptionDemo(){
        throw new BaseException("错误了");
    }


    /**
     * 日志级别： TRACE, DEBUG, INFO, WARN, ERROR
     * @return
     */
    @RequestMapping(value = "/queryLogDemo" ,method = RequestMethod.GET)
    public RestResult<BusiModel> queryLogDemo(){
        logger.trace("这是一个日志{}输出","trace");
        logger.debug("这是一个日志{}输出","debug");
        logger.info("这是一个日志{}输出","info");
        logger.warn("这是一个日志{}输出","warn");
        logger.error("这是一个日志{}输出","error");
        return new RestResult<>();
    }

    @ApiOperation(value="查询用户信息",notes="从mysql中查询到数据并返回")
    @RequestMapping(value = "/queryProdInstById" ,method = RequestMethod.POST)
    public String queryProdInstById(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("employeeId",1111);
        DataSourceContextHolder.setDataSourceType("dataSource0");
        try{
            Map inst = custQuerySMO.queryProdInstByCond(map);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            return "hello controller";
        }

    }

    @Autowired
    CtgMqInterface ctgMqInterface;

    @RequestMapping(value = "/sendMsgByCtgMqTest" ,method = RequestMethod.GET)
    public String sendMsgByCtgMqTest(){
        ctgMqInterface.sendTest();
        //ctgMqInterface.pullTest();
        //ctgMqInterface.pushTest();
        return "";
    }

    @RequestMapping(value = "/pushMsgByCtgMqTest" ,method = RequestMethod.GET)
    public String pushMsgByCtgMqTest(){
        ctgMqInterface.pushTest();
        return "";
    }




}
