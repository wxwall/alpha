package com.asiainfo.config.busi;

import org.springframework.beans.factory.annotation.Value;
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

    public String test;


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
