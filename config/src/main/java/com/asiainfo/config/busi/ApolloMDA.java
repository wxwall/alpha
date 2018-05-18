package com.asiainfo.config.busi;

import com.ctrip.framework.apollo.spring.annotation.ApolloJsonValue;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * 集成apollo
 * Created by wuxiaowei on 2018/5/18
 */
@Component
public class ApolloMDA {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    @Value(value = "${name:jobs}")
    private String name;

    @Value(value = "${age:10}")
    private int age;


    @ApolloJsonValue("${jsonBeanProperty:[]}")
    private List<JsonBean> jsonBeans;


    public String getName() {
        return name;
    }


    public List<JsonBean> getJsonBeans() {
        return jsonBeans;
    }

    /**
     * jsonBeanProperty=[{"someString":"hello","someInt":100},{"someString":"world!","someInt":200}]
     * @param jsonBeans
     */
    public void setJsonBeans(List<JsonBean> jsonBeans) {
        logger.info("updating json beans, old value: {}, new value: {}", this.jsonBeans, jsonBeans);
        this.jsonBeans = jsonBeans;
    }

    public void setName(String name) {
        logger.info("updating batch, old value: {}, new value: {}", this.name, name);
        this.name = name;
    }

    public int getAge() {
        return age;
    }


    public void setAge(int age) {
        this.age = age;
    }

    private static class JsonBean{

        private String someString;
        private int someInt;

        @Override
        public String toString() {
            return "JsonBean{" +
                    "someString='" + someString + '\'' +
                    ", someInt=" + someInt +
                    '}';
        }
    }
}
