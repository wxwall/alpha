package com.asiainfo.crm.common.model;

/**
 * Created by wxwall on 2017/9/1.
 */
public class BaseModel {

    /**
     * 路由标识
     */
    protected String route;

    protected String tableName;


    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }
}
