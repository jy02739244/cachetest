package dao;

import bean.Goods;
import org.apache.ibatis.annotations.Param;
import tk.mybatis.mapper.common.Mapper;

/**
 * @Title Created by xzm
 * @date 2017/3/6.
 */
public interface GoodsDao extends Mapper<Goods>{
    int modifyCount(@Param("name") String name,@Param("buy") int buy);
}
