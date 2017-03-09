package task;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

/**
 * @Title Created by xzm
 * @date 2017/3/8.
 */
@Component
public class SpringTask {

    private Logger logger= LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "5/5 48 9 * * ?")
    public void task(){
        logger.info("task test...........");
    }
}
