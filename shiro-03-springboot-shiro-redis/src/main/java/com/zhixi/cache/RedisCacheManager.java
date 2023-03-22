package com.zhixi.cache;

import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;

/**
 * @author zhixi
 */
public class RedisCacheManager implements CacheManager {

    /**
     * 缓存
     *
     * @param cacheName 认证或者是授权缓存的统一名称
     * @param <K> k
     * @param <V> v
     * @return 自定义CacheManager
     * @throws CacheException 异常
     */

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        System.out.println(cacheName);
        return new RedisCache<K, V>(cacheName);
    }
}
