package service;

import bean.TraceCount;

/**
 * @Title Created by xzm
 * @date 2017/3/3.
 */
public interface TraceCountService extends BaseService<TraceCount>{

    String getTotalCountByCompany(String company);

    int getCount();
}
