package com.edu.cache;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.*;

/**
 * @author zhangzhe
 * @date 2019/3/23 15:37
 */
public class CachePlus implements CacheManager {

    private Map<Object, Entity> map = new HashMap<>();

    private ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();

    @Override
    public synchronized void put(String key, Object entity) {
        put(key, entity, 0L);

    }

    @Override
    public synchronized void put(String key, Object data, Long timeOut) {
        if (timeOut > 0) {
            Future future = executorService.schedule(() -> {
                clearCacheByKey(key);
            }, timeOut, TimeUnit.MILLISECONDS);
            map.put(key, new Entity(data, future));
        }
        map.put(key, new Entity(data, null));

    }

    @Override
    public synchronized Object getCacheByKey(String key) {
        Entity entity = map.get(key);
        return entity == null ? null : entity.getValue();
    }

    @Override
    public synchronized Map getCacheAll() {
        return map;
    }

    @Override
    public synchronized boolean contains(String key) {
        return map.containsKey(key);
    }

    @Override
    public synchronized void clearAll() {
        map.clear();
    }

    @Override
    public synchronized void clearCacheByKey(String key) {
        Entity entity = map.remove(key);

        Future future = entity.getFuture();
        if (future != null)
            future.cancel(true);

    }

    @Override
    public synchronized boolean isDisabledByTimeout(String key) {
        return false;
    }

    @Override
    public synchronized Set<Object> getAllKey() {
        return map.keySet();
    }

    @Override
    public synchronized int size() {
        return map.size();
    }

    @Setter
    @Getter
    @AllArgsConstructor
    private class Entity {
        private final Object value;
        private final Future future;
    }

}
