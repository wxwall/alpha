package com.asiainfo.crm.controller;

import com.asiainfo.crm.busi.BusiModel;
import com.asiainfo.crm.busi.BusiSMO;
import com.asiainfo.crm.common.controller.AbstractController;
import com.asiainfo.crm.common.model.RestResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 单个restful风格的示例
 * Created by wuxiaowei on 2018/6/8
 */
@RestController
@RequestMapping(value="/busi")
public class BusiController extends AbstractController {

    @Autowired
    BusiSMO busiSMO;

    /**
     * 处理"/busi/"的GET请求，用来获取列表
     * 还可以通过@RequestParam从页面中传递参数来进行查询条件或者翻页信息的传递
     */
    @RequestMapping(value="/", method= RequestMethod.GET)
    public RestResult<List<BusiModel>> getBusi(){
        RestResult<List<BusiModel>> restResult = new RestResult<List<BusiModel>>();
        List<BusiModel> busiList = busiSMO.getBusiList();
        restResult.setData(busiList);
        return restResult;
    }

    /**
     * 处理"/busi/"的POST请求，用来创建BusiModel
     * 除了@ModelAttribute绑定参数之外，还可以通过@RequestParam从页面中传递参数
     * @param busiModel
     * @return
     */
    @RequestMapping(value="/", method=RequestMethod.POST)
    public RestResult postBusi(@ModelAttribute BusiModel busiModel) {
        RestResult<BusiModel> restResult = new RestResult<BusiModel>();
        restResult.setMessage(busiModel.getName());
        return restResult;
    }


    /**
     * 处理"/busi/{id}"的GET请求，用来获取url中id值的busi信息
     * url中的id可通过@PathVariable绑定到函数的参数中
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}", method=RequestMethod.GET)
    public RestResult getBusi(@PathVariable Long id) {
        RestResult<BusiModel> restResult = new RestResult<BusiModel>();
        BusiModel model = new BusiModel();
        model.setId(1);
        model.setName("小明");
        restResult.setData(model);
        return restResult;
    }

    /**
     * 处理"/busi/{id}"的PUT请求，用来更新Busi信息
     * @param id
     * @param busiModel
     * @return
     */
    @RequestMapping(value="/{id}", method=RequestMethod.PUT)
    public RestResult putBusi(@PathVariable Long id, @ModelAttribute BusiModel busiModel) {
        logger.debug(busiModel.getName() + "  " + busiModel.getId());
        busiSMO.put(id);
        RestResult<BusiModel> restResult = new RestResult<BusiModel>();
        restResult.setMessage("success");
        return restResult;
    }

    /**
     * 处理"/busi/{id}"的DELETE请求，用来删除BusiModel
     * @param id
     * @return
     */
    @RequestMapping(value="/{id}", method=RequestMethod.DELETE)
    public RestResult deleteBusi(@PathVariable Long id) {
        busiSMO.remove(id);
        RestResult<BusiModel> restResult = new RestResult<BusiModel>();
        restResult.setMessage("success");
        return restResult;
    }
}
