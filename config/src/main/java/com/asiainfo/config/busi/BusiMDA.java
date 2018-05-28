package com.asiainfo.config.busi;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.stereotype.Component;

/**
 * Created by wuxiaowei on 2018/5/3
 */
@Component
@RefreshScope
public class BusiMDA {

    @Value(value = "${BusiMDA.prodOfferName}")
    public String prodOfferName;

   // @Value(value = "${prodOfferId}")
    public String prodOfferId;


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
