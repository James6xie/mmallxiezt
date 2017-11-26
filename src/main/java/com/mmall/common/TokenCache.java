package com.mmall.common;

import com.google.common.cache.CacheBuilder;
import com.google.common.cache.CacheLoader;
import com.google.common.cache.LoadingCache;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * @author: zhongtianemail@gmail.com
 * @create 2017-11-24 下午6:32
 * @Description:
 **/
public class TokenCache {
    //声明日志
    private static Logger logger = LoggerFactory.getLogger(TokenCache.class);
    //声明一个静态内存块
    /**
     * @author:zhongtianemail@gmail.com.cn
     * @Description:
     * @Param:  初始化、最大尺寸、LRU算法进行缓存的加载
     * @Return:
     * @Date:   下午4:39 17-11-26
     */
    private static LoadingCache<String, String> localCache = CacheBuilder.newBuilder().initialCapacity(1000).maximumSize(10000).expireAfterAccess(12, TimeUnit.HOURS)
            .build(new CacheLoader<String, String>() {
                //默认的数据加载实现，当调用get取值的时候，如果key没有对应的值，就调用这个方法进行加载
                @Override
                public String load(String s) throws Exception {
                    return "null";
                }
            });

    public static void setKey(String key, String value){
        localCache.put(key, value);
    }

    public static String getKey(String key){
        String value = null;
        try {
            value = localCache.get(key);
            if ("null".equals(value)){
                return null;
            }
        }catch (Exception e){
            logger.error("localCache get error",e);
        }
        return value;
    }
}
