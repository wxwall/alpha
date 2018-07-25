package com.asiainfo.crm.controller;

import com.ai.datasources.DataSourceContextHolder;
import com.ai.hint.HintContextHolder;
import com.asiainfo.crm.busi.BusiModel;
import com.asiainfo.crm.busi.MktResource;
import com.asiainfo.crm.busi.service.BusiSMO;
import com.asiainfo.crm.busi.service.CtgMqInterface;
import com.asiainfo.crm.busi.service.ICustQuerySMO;
import com.asiainfo.crm.busi.service.MktResourceSMO;
import com.asiainfo.crm.common.annotation.SysLog;
import com.asiainfo.crm.common.controller.AbstractController;
import com.asiainfo.crm.common.exception.BaseException;
import com.asiainfo.crm.common.model.RestResult;
import com.asiainfo.crm.multi.SoWebMultiSMO;
import com.github.pagehelper.PageHelper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.*;

import javax.sql.DataSource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by wuxiaowei on 2018/5/3
 */
@RestController
@Api("样例Controller")
public class Controller extends AbstractController {



    @Autowired
    BusiSMO busiSMO;
    @Autowired
    ICustQuerySMO custQuerySMO;
    @Autowired
    CtgMqInterface ctgMqInterface;
    @Autowired
    SoWebMultiSMO soWebMultiSMO;
    @Autowired
    MktResourceSMO mktResourceSMO;

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
        map.put("employeeId",1);
        DataSourceContextHolder.setDataSourceType("ds1");
        Map inst = null;
        try{
            inst = custQuerySMO.queryProdInstByCond(map);
        }catch (Exception e) {
            e.printStackTrace();
        }finally{
            return "hello controller";
        }

    }


    @ApiOperation(value="生产者发消息",notes="生产者发消息到CTGMQ")
    @RequestMapping(value = "/sendMsgByCtgMqTest" ,method = RequestMethod.GET)
    public String sendMsgByCtgMqTest(){
        ctgMqInterface.sendTest();
        return "";
    }


    @ApiOperation(value="消费者PULL消费",notes="消费者消费CTGMQ")
    @RequestMapping(value = "/pullMsgByCtgMqTest" ,method = RequestMethod.GET)
    public String pullMsgByCtgMqTest(){
        ctgMqInterface.pullTest();
        return "";
    }

    @ApiOperation(value="消费者侦听消费",notes="消费者消费CTGMQ")
    @RequestMapping(value = "/pushMsgByCtgMqTest" ,method = RequestMethod.GET)
    public String pushMsgByCtgMqTest(){
        ctgMqInterface.pushTest();
        return "";
    }

    @ApiOperation(value="http远程调用",notes="调用非eureka注册的外部系统，194测试环境后台查询接口")
    @RequestMapping(value = "/queryOrderListInfoByCustomerOrderId" ,method = RequestMethod.POST)
    public RestResult queryOrderListInfoByCustomerOrderId(@RequestBody String jsonParam){
        RestResult restResult = soWebMultiSMO.queryOrderListInfoByCustomerOrderId(jsonParam);
        return restResult;
    }

    @ApiOperation(value="测试UDAL启动器",notes="udal启动器数据源路由hint路由测试")
    @RequestMapping(value = "/testudamhint" ,method = RequestMethod.GET)
    public String testJndi() {
        DataSourceContextHolder.setDataSourceType("ds2");
        HintContextHolder.setHintOfSelectSql("/* !HINT({\"balance\":\"2\"})*/");
        PageHelper.offsetPage(0,2);
        MktResource mktResource = mktResourceSMO.getMktResourceById(1);
        if(mktResource==null){
            return "null";
        }
        return mktResource.toString();
    }

    @ApiOperation(value="测试UDAL多分片单条插入",notes="测试单条插入没问题")
    @RequestMapping(value = "/testudaldtstart" ,method = RequestMethod.GET)
    public String testudaldtstart() {
        DataSourceContextHolder.setDataSourceType("ds2");
        List<MktResource> mktList = new ArrayList<>();
        mktList.add(instancMktResource(1000));
        mktList.add(instancMktResource(1001));
        mktResourceSMO.insertMktResources(mktList);
        return "";
    }

    @ApiOperation(value="测试UDAL多分片批量修改",notes="待测试")
    @RequestMapping(value = "/testupdatemulti" ,method = RequestMethod.GET)
    public String testupdatemulti() {
        DataSourceContextHolder.setDataSourceType("ds2");
        mktResourceSMO.updateMktResources();
        mktResourceSMO.updateMktResourcesWithOutDtStart();
        return "";
    }

    private MktResource instancMktResource(Integer id){
        MktResource mktResource = mktResourceSMO.getMktResourceById(1);
        mktResource.setMktResId(id);
        mktResource.setMktResName("查询后修改一下ID"+id);
        return mktResource;
    };

    @ApiOperation(value="测试事务提交",notes="测试事务的正常、异常提交情况")
    @RequestMapping(value = "/testTransaction" ,method = RequestMethod.GET)
    public String testTransaction() {
        DataSourceContextHolder.setDataSourceType("ds2");
        List<MktResource> mktList = new ArrayList<>();
        mktList.add(instancMktResource(1022));
        mktList.add(instancMktResource(1023));
        mktResourceSMO.insertMktResources(mktList);
        return "";
    }

    @ApiOperation(value="测试分片事务拦截器",notes="测试分片事务拦截器是否成功")
    @RequestMapping(value = "/testUdalDtStartInterceptor" ,method = RequestMethod.GET)
    public String testUdalDtStartInterceptor() {
        DataSourceContextHolder.setDataSourceType("ds2");
        List<MktResource> mktList = new ArrayList<>();
        mktList.add(instancMktResource(1041));
        mktList.add(instancMktResource(1042));
        mktResourceSMO.insertMktResourcesStart(mktList);
        return "";
    }

    @Autowired
    DataSource dataSource;
    @Autowired
    ApplicationContext applicationContext;

    @ApiOperation(value="通过容器获取指定数据源",notes="通过容器获取指定数据源bean")
    @RequestMapping(value = "/testGetPointDataSource" ,method = RequestMethod.GET)
    public String testGetPointDataSource() {
        Object ds1 = applicationContext.getBean("ds1");
        return "dd";
    }
}
