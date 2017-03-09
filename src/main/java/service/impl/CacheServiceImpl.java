package service.impl;

import cache.SerializeUtil;
import org.crazycake.shiro.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.CacheCallback;
import service.CacheService;

/**
 * @Title Created by xzm
 * @date 2017/3/3.
 */
@Service
public class CacheServiceImpl implements CacheService{

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Autowired
    private RedisManager redisManager;

    @Override
    public <T> T findCache(String key, int seconds, CacheCallback<T> callback) {
        byte[] keyByte=key.getBytes();
        byte[] bytes = redisManager.get(keyByte);

        if(bytes==null){
            synchronized (this){
                bytes = redisManager.get(keyByte);
                if(bytes==null){
                    T value = callback.load();
                    redisManager.set(keyByte, SerializeUtil.serialize(value),seconds);
                    return value;
                }
                T deserialize = SerializeUtil.deserialize(bytes);
                logger.info("cache:"+deserialize);
                return deserialize;
            }

        }else{
            T deserialize = SerializeUtil.deserialize(bytes);
            logger.info("cache:"+deserialize);
            return deserialize;
        }
    }
}
