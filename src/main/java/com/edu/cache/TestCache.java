package com.edu.cache;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhe
 * @date 2019/3/23 13:33
 */
public class TestCache {

    private static Logger log = LoggerFactory.getLogger(TestCache.class);

    public static void main(String[] args) {


        CacheManager cacheManager = new CacheImpl();
        cacheManager.put("1", "梅利奥达斯", 10 * 1000L);
        cacheManager.put("2", "梅利奥达", 0L);
        cacheManager.put("3", "meiliao", 0L);
        cacheManager.put("4", "梅利", 30 * 1000L);
        cacheManager.put("5", "梅", 10 * 1000L);

        CacheListener cacheListener = new CacheListener(cacheManager);
        cacheListener.startListener();

        log.info("the data of cache is " + cacheManager.getCacheAll().toString());
        log.info("the size of cache is " + cacheManager.size());

        try {
            TimeUnit.SECONDS.sleep(15);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        log.info("the data of cache is " + cacheManager.getCacheAll().toString());
        log.info("the size of cache is " + cacheManager.size());
    }
}
