package com.summit.common.injectDemo.controller;

import com.summit.common.injectDemo.model.Cat;
import com.summit.common.injectDemo.model.Dog;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.Cacheable;

import javax.annotation.Resource;

public class DemoInject {

    @Value(value = "true")
    private boolean id;
    private String name;

    @Resource
    private Dog ssss;

    // 经测试@Resource(name = "com.summit.common.injectDemo.model.Cat")等同于@Resource(name = "com.summit.common.injectDemo.model.Cat#0"),都是spring容器中第一个Cat对象
    //@Resource(name = "com.summit.common.injectDemo.model.Cat#1")则注入了第二个Cat对象
    @Resource(name = "com.summit.common.injectDemo.model.Cat#1")
    private Cat cat;

    public DemoInject() {

    }

    public String getName() {
        return name;
    }

    @Cacheable(value = {"cacheTest"}, key = "#name")
    public String getCache(String name) {
        System.out.printf("############" + name);
        return name+System.currentTimeMillis();
    }

    public void setName(String name) {
        this.name = name;
        System.out.println(this.name + ">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>" + ssss.getName() + this.id);
    }

}
