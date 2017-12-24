package com.summit.cache.cachedemo;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

@Component
public class CacheDemo {

    /**
     * 将方法返回数据加入缓存
     * @param name
     * @return
     */
    @Cacheable(value = {"cacheTest"}, key = "#name")
    public String getCache(String name) {
        System.out.printf("############即将缓存数据" );
        return name+System.currentTimeMillis();
    }

    /**
     * 更新缓存
     * @param name
     * @return
     */
    @CachePut(value = {"cacheTest"}, key = "#name")
    public String updateCache(String name) {
        System.out.printf("############即将更新缓存");
        return name+System.currentTimeMillis();
    }

    /**
     * 清空缓存对象cacheTest所有内容
     */
    @CacheEvict(value = "cacheTest", allEntries = true)
    public void clearCache( ) {
        System.out.printf("############缓存已清空" );
    }

}
