package com.edu.cache;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhe
 * @date 2019/3/23 16:26
 */
public class TestCachePlus {

    private static Logger log = LoggerFactory.getLogger(TestCachePlus.class);

    public static void main(String[] args) {

        CachePlus cache = new CachePlus();

        String key = "id";
        log.info("***********not setle the timeout**********");
        cache.put(key, 123);
        log.info("key:" + key + ", value:" + cache.getCacheByKey(key));
        cache.clearCacheByKey(key);
        log.info("key:" + key + ", value:" + cache.getCacheByKey(key));
        log.info("***********setle the timeout**********");
        cache.put(key, "123456", 1000l);
        log.info("key:" + key + ", value:" + cache.getCacheByKey(key));
        try {
            TimeUnit.MILLISECONDS.sleep(2000l);
            log.info("I have a break for 2 seconds");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info("key:" + key + ", value:" + cache.getCacheByKey(key));

    }
}
