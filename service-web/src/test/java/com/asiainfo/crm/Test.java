package com.asiainfo.crm;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by wuxiaowei on 2018/7/3
 */
public class Test {


    public static void main(String[] args) {


        HashMap hashMap = new HashMap<String,Object>();
        hashMap.put("aa","aa");
        Map map = hashMap;
        HashMap  map1 = (HashMap) map;
        System.out.println(map);
    }
}
