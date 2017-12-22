package com.summit.encacheTest;


import com.summit.cache.cachedemo.CacheDemo;
import com.summit.common.injectDemo.controller.DemoInject;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:spring/spring-application.xml","classpath:spring/spring-mvc.xml","classpath:spring/spring-mybatis.xml" })
@Transactional
public class EnCacheTest {
    @Autowired
    private CacheDemo cacheDemo;

    @Test
    public void testGetAcccountById() throws InterruptedException {
        while (true) {
            String name = cacheDemo.getCache("张三");
            System.out.println(name);
            Thread.sleep(2000);
        }

    }
}