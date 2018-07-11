package com.asiainfo.crm.controller;

import com.alibaba.fastjson.JSONObject;
import com.asiainfo.crm.busi.BusiModel;
import com.asiainfo.crm.common.controller.AbstractController;
import com.asiainfo.crm.common.model.RestResult;
import com.netflix.appinfo.InstanceInfo;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.cloud.netflix.eureka.EurekaDiscoveryClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by wuxiaowei on 2018/7/6
 */
@RestController
@Api("监控Controller")
@RequestMapping(value = "/monitor")
public class MonitorController extends AbstractController {

    @Autowired
    private DiscoveryClient discoveryClient;



    @RequestMapping(value = "/instances" ,method = RequestMethod.GET)
    public RestResult instances( @RequestParam(value = "service_id")String service_id){
        List<String> instancesList = getInstances(service_id);
        RestResult<BusiModel> restResult = new RestResult<BusiModel>();
        restResult.setMessage(JSONObject.toJSONString(instancesList));
        return restResult;
    }

    private List<String> getInstances(String service_id) {
        List<String> instancesList = new ArrayList<>();
        String instancesStr = null;
        List<ServiceInstance> instances = discoveryClient.getInstances(service_id);
        if(instances == null){
            instancesStr = new String("service_id未找到，请检查：" + service_id);
            instancesList.add(instancesStr);
        }else{
            for (int i = 0; i < instances.size(); i++) {
                ServiceInstance serviceInstance =  instances.get(i);
                EurekaDiscoveryClient.EurekaServiceInstance eurekaServiceInstance = (EurekaDiscoveryClient.EurekaServiceInstance) serviceInstance;
                InstanceInfo instanceInfo = eurekaServiceInstance.getInstanceInfo();
                instancesStr = new StringBuilder("Host:").append(instanceInfo.getHostName())
                        .append(" IP:").append(instanceInfo.getIPAddr())
                        .append(" port:").append(instanceInfo.getPort())
                        .append(" groupName:").append(instanceInfo.getAppGroupName()).toString();
                logger.info(instancesStr);
                instancesList.add(instancesStr);
            }
        }
        return instancesList;
    }
}
