package service.impl;

import bean.TraceCount;
import net.sourceforge.groboutils.junit.v1.MultiThreadedTestRunner;
import net.sourceforge.groboutils.junit.v1.TestRunnable;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import service.TraceCountService;

import java.util.Random;
import java.util.concurrent.CountDownLatch;

/**
 * @Title Created by xzm
 * @date 2017/3/3.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-config.xml")
public class TraceCountServiceImplTest {
    @Autowired
    private TraceCountService traceCountService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    public void insertData() {
        TraceCount traceCount;
        String[] companys = {"质尊溯源", "平安", "腾讯", "百度", "阿里巴巴"};
        Random random = new Random();
        for (int i = 0; i < 1000000; i++) {
            traceCount = new TraceCount();
            traceCount.setCount(i + 1);
            String company = companys[random.nextInt(companys.length)];
            logger.info("company:" + company);
            traceCount.setCompany(company);
            traceCountService.insertSelective(traceCount);
        }


    }

    @Test
    public void getTotalCountByCompany() throws Exception {

        // Runner 数组, 相当于并发多少个
//        TestRunnable[] trs = new TestRunnable[size];
//
//        for (int i = 0; i < size; i++) {
//            trs[i] = new ExecuteThread();
//        }
//
//        // 用于执行多线程测试用例的Runner, 将前面定义的单个Runner组成的数组传入
//        MultiThreadedTestRunner mttr = new MultiThreadedTestRunner(trs);
//
//        // 开发并发执行数组里定义的内容
//        try {
//            mttr.runTestRunnables();
//        } catch (Throwable throwable) {
//            throwable.printStackTrace();
//        }
        for (int i = 0; i < size; i++) {
            Thread thread = new Thread(new ExecuteRunnable() );
            thread.start();
            latch.countDown();
        }
        Thread.sleep(3000);

    }
    private int size=10;
    private CountDownLatch latch=new CountDownLatch(size);
    private class ExecuteThread extends TestRunnable {
        @Override
        public void runTest() {
            String count = traceCountService.getTotalCountByCompany("百度");
        }
    }

    private class ExecuteRunnable extends Thread{
        @Override
        public void run() {
            try{
                latch.await();
            }catch (Exception e){
                e.printStackTrace();
            }
            traceCountService.getTotalCountByCompany("百度");
        }
    }
}