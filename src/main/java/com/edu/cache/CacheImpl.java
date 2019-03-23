package com.edu.cache;


import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author zhangzhe
 * @date 2019/3/23 11:54
 */
public class CacheImpl implements CacheManager {

    private static Map<String, Object> cacheMap = new ConcurrentHashMap<>();


    public void put(String key, Object entity) {
        cacheMap.put(key, entity);
    }

    public void put(String key, Object entity, Long timeOut) {
        long time = timeOut >= 0L ? timeOut : 0L;
        cacheMap.put(key, new CacheEntity(entity, time, System.nanoTime()));

    }

    public Object getCacheByKey(String key) {
        if (cacheMap.containsKey(key))
            return cacheMap.get(key);
        return null;
    }

    public Map<String, Object> getCacheAll() {
        return cacheMap;
    }

    public boolean contains(String key) {
        Set<String> keys = getAllKey();
        for (String myKey : keys) {
            if (myKey.equals(key))
                return true;
        }
        return false;
    }

    public void clearAll() {
        cacheMap.clear();
    }

    public void clearCacheByKey(String key) {
        if (this.contains(key))
            cacheMap.remove(key);
    }

    public boolean isDisabledByTimeout(String time) {
        if (!this.contains(time))
            return true;
        CacheEntity cacheEntity = (CacheEntity) cacheMap.get(time);
        Long timeout = cacheEntity.getTimeout();
        Long initTime = cacheEntity.getInitTime();
        if (timeout == 0 || initTime + timeout > System.nanoTime())
            return false;

        return true;
    }

    public Set<String> getAllKey() {
        return cacheMap.keySet();
    }

    @Override
    public int size() {
        return cacheMap.size();
    }
}
