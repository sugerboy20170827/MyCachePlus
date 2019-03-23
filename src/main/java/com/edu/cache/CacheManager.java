package com.edu.cache;

import java.util.Map;
import java.util.Set;

/**
 * @author zhangzhe
 * @date 2019/3/23 11:01
 */
public interface CacheManager<T> {

    /**
     * put operation of cache
     */
    void put(String key, T entity);


    /**
     * put operation of cache with the timeout
     *
     * @see CacheEntity
     */
    void put(String key, Object data, Long timeOut);

    /**
     * get operation of cache
     */
    T getCacheByKey(String key);


    /***
     * get all the cache
     */
    Map<String, T> getCacheAll();

    /**
     * judge whether the cache contains the assigned data
     */
    boolean contains(String key);

    /**
     * clear all the cache
     */
    void clearAll();

    /***
     * clear the assigned the cache by key
     */
    void clearCacheByKey(String key);

    /***
     * judge whether the cache is timeout
     */
    boolean isDisabledByTimeout(String key);

    /***
     * get all the key
     */
    Set<Object> getAllKey();

    /**
     * get the size of cache
     */
    int size();

}
