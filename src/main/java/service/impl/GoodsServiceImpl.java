package service.impl;

import bean.Goods;
import dao.GoodsDao;
import net.spy.memcached.CASResponse;
import net.spy.memcached.CASValue;
import net.spy.memcached.MemcachedClient;
import org.crazycake.shiro.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import service.GoodsService;

/**
 * @Title Created by xzm
 * @date 2017/3/6.
 */
@Service
public class GoodsServiceImpl extends BaseServiceImpl<Goods> implements GoodsService{

    private Logger logger= LoggerFactory.getLogger(getClass());
    @Autowired
    private GoodsDao goodsDao;

//    @Autowired
//    private MemcachedClient memcachedClient;

    @Autowired
    private RedisTemplate redisTemplate;

    @Override
    public boolean modifyCount(String name, int buy) {
        int rows = goodsDao.modifyCount(name, buy);
        logger.info("抢购："+rows);
       return rows==1;
    }

    @Override
    public boolean modifyCountByMemcached(String name, int buy) {
//        CASValue<Object> casValue = memcachedClient.gets(name);
//        Integer count = Integer.valueOf(casValue.getValue().toString());
//        if(count>buy){
//            CASResponse casResponse = memcachedClient.cas(name, casValue.getCas(), count - buy);
//            if(casResponse.equals(CASResponse.OK)){
//                return true;
//            }else{
//                logger.info("CAS冲突：");
//            }
//        }else{
//            logger.info("库存不足："+count);
//        }
        return false;
    }

    @Override
    public boolean modifyCountByReids(final String name, final int buy) {
        ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
        Object execute = redisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection redisConnection) throws DataAccessException {
                RedisSerializer<String> serializer = redisTemplate.getStringSerializer();
                byte[] keyBytes = serializer.serialize(name);
                byte[] valueBytes = redisConnection.get(keyBytes);
                String value = serializer.deserialize(valueBytes);
                int parseInt = Integer.parseInt(value);
                if (parseInt > 0 && parseInt - buy > 0) {
                    Long decr = redisConnection.decrBy(keyBytes,buy);
                    logger.info("redis 结果：" + decr + "");
                    return Boolean.TRUE;
                }
                return Boolean.FALSE;
            }
        });

        logger.info("execute:"+execute);
        return (boolean) execute;

    }
}
