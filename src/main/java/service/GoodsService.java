package service;

import bean.Goods;

/**
 * @Title Created by xzm
 * @date 2017/3/6.
 */
public interface GoodsService extends BaseService<Goods> {

    boolean modifyCount(String name, int buy);

    boolean modifyCountByMemcached(String name, int buy);

    boolean modifyCountByReids(String name,int buy);
}
