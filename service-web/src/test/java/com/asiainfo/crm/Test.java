package com.asiainfo.crm;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by wuxiaowei on 2018/7/3
 */
public class Test {


    public static void main(String[] args) {

        List list = new ArrayList(1000);
        list.add("1");
        list.add("2");
        list.add("3");
        list.add("4");
        list.add("5");
        list.add("6");
        list.add("7");
        list.add("7");
        List newList = (List) list.stream().distinct().collect(Collectors.toList());
        System.out.println(newList.size());

    }
}
