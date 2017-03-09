package dao;

import bean.TraceCount;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Title Created by xzm
 * @date 2017/3/3.
 */
public interface TraceCountDao extends Mapper<TraceCount>{

    String getTotalCountByCompany(String company);
}
