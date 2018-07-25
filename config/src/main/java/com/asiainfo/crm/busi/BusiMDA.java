package com.asiainfo.crm.busi;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * Created by wuxiaowei on 2018/5/3
 */
@Component
public class BusiMDA {

    @Value(value = "${BusiMDA.prodOfferName:abc}")
    public String prodOfferName;

    @Value(value = "${prodOfferId:123}")
    public String prodOfferId;

    @Autowired
    private Environment environment;


    /** spring提供一种简单的方式，直接通过propertiesName来查找**/
    public String getEnviroObject(String propertiesName){
        if(null != propertiesName){
            return environment.getProperty(propertiesName);
        }
        return "";
    }


    public String getProdOfferName() {
        return prodOfferName;
    }

    public void setProdOfferName(String prodOfferName) {
        this.prodOfferName = prodOfferName;
    }

    public String getProdOfferId() {
        return prodOfferId;
    }

    public void setProdOfferId(String prodOfferId) {
        this.prodOfferId = prodOfferId;
    }
}
