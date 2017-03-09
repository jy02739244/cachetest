package service.impl;

import net.spy.memcached.MemcachedClient;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.GoodsService;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @Title Created by xzm
 * @date 2017/3/6.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class GoodsServiceImplTest {
    @Autowired
    private GoodsService goodsService;

//    @Autowired
    private MemcachedClient memcachedClient;

    @Autowired
    private RedisTemplate redisTemplate;

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void modifyCount() throws Exception {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(new ModifyThread() );
            thread.start();
            latch.countDown();
        }
        Thread.sleep(20000);
        logger.info("卖出："+totalSale);
        logger.info("抢购人数："+totalUser);
    }


    @Before
    public void before(){
//        memcachedClient.set(goodsName,30,100+"");
        ValueOperations valueOperations = redisTemplate.opsForValue();
        valueOperations.set(goodsName,100+"",30, TimeUnit.SECONDS);
        Object ab = valueOperations.get(goodsName);
        logger.info("初始："+ab);
    }

    @After
    public void after(){
//        Object value = memcachedClient.get(goodsName);
        ValueOperations valueOperations = redisTemplate.opsForValue();
        logger.info("香蕉库存："+valueOperations.get(goodsName));
    }

    @Test
    public void modifyCountByMemcached() throws Exception {
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(new ModifyThread() );
            thread.start();
            latch.countDown();
        }
        Thread.sleep(3000);
        logger.info("卖出："+totalSale);
        logger.info("抢购人数："+totalUser);
    }

    private int size=100;
    private CountDownLatch latch=new CountDownLatch(size);
    private int totalSale=0;
    private int totalUser=0;
    private int buy=3;
    private String goodsName="banana";
    private class ModifyThread extends Thread{
        @Override
        public void run() {
            try{
                latch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            boolean flag = goodsService.modifyCountByReids(goodsName, buy);
            if(flag){
                totalSale+=buy;
                totalUser+=1;
            }
        }
    }
}