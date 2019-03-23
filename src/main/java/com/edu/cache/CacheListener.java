package com.edu.cache;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangzhe
 * @date 2019/3/23 12:49
 */
@AllArgsConstructor
@Data
public class CacheListener {

    private static final Logger log = LoggerFactory.getLogger(CacheManager.class);
    private final CacheManager cacheManager;

    public void startListener() {
        Thread thread = new Thread(() -> {
            while (true) {
                Set<String> keys = cacheManager.getAllKey();
                for (String key : keys) {
                    if (cacheManager.isDisabledByTimeout(key)) {
                        cacheManager.clearCacheByKey(key);
                        log.info("the data has been deleted");
                    }

                }
                try {
                    TimeUnit.SECONDS.sleep(3);
                    log.info("I have a break for 5 seconds");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

        });

        thread.start();
    }

}
