package service;

/**
 * @Title Created by xzm
 * @date 2017/3/3.
 */
public interface CacheService {

    <T> T findCache(String key,int seconds,CacheCallback<T> callback);
}
