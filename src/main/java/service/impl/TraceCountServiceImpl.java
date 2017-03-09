package service.impl;

import bean.TraceCount;
import cache.SerializeUtil;
import dao.TraceCountDao;
import org.crazycake.shiro.RedisManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;
import service.CacheCallback;
import service.CacheService;
import service.TraceCountService;

/**
 * @Title Created by xzm
 * @date 2017/3/3.
 */
@Service
public class TraceCountServiceImpl extends BaseServiceImpl<TraceCount> implements TraceCountService {

    @Autowired
    private TraceCountDao traceCountDao;

    @Autowired
    private CacheService cacheService;

    private Logger logger = LoggerFactory.getLogger(getClass());

    private int count;

    @Override
    @Transactional
    public String getTotalCountByCompany(final String company) {
       return cacheService.findCache("cache:getTotalCountByCompany:" + company, 30, new CacheCallback<String>() {
            @Override
            public String load() {
                String total = traceCountDao.getTotalCountByCompany(company);
                logger.info("total:" + total);
                return total;
            }
        });
    }


    @Override
    public int getCount() {
        return count;
    }
}
