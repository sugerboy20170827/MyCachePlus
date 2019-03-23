package com.edu.cache;

import lombok.*;

/**
 * @author zhangzhe
 * @date 2019/3/23 10:54
 */
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class CacheEntity {

    private static final CacheEntity NULL = new NullCacheEntity();

    /**
     * data entity
     */
    private Object data;

    /**
     * setle the timeout
     */
    private Long timeout;


    /***
     * setle the initial time
     */
    private Long initTime;


    /**
     * create the class guard against the NullPointException
     */


    public static class NullCacheEntity extends CacheEntity implements Null {

        private NullCacheEntity() {
            super(null, 0L, 0L);
        }

        @Override
        public String toString() {
            return "This a NullCacheEntity";
        }
    }
}
