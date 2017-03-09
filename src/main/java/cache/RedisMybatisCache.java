package cache;

import java.util.Set;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.crazycake.shiro.RedisManager;
import spring.SpringContextHolder;


/**   
* @Title: RedisMybatisCache.java 
* @Package cn.rfidcer.cache 
* @Description:Redis实现的mybatis的缓存
* @author 席志明
* @Copyright Copyright
* @date 2016年8月18日 下午5:00:11 
* @version V1.0   
*/
public class RedisMybatisCache implements Cache {

    private RedisManager cache;
    /** The ReadWriteLock. */
    private final ReadWriteLock readWriteLock = new ReentrantReadWriteLock();

    private String id;
    
    private String keyPrefix = "mybatis_cache:";

    public RedisMybatisCache(final String id) {
        if (id == null) {
            throw new IllegalArgumentException("Cache instances require an ID");
        }
        this.id = id;
    }
    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public int getSize() {
    	init();
        return Integer.valueOf(cache.dbSize().toString());
    }

    @Override
    public void putObject(Object key, Object value) {
    	init();
    	cache.set(getKeyBytes(key), SerializeUtil.serialize(value));
    }

    private byte[] getKeyBytes(Object key){
    	return (keyPrefix+id+":"+key.toString()).getBytes();
    }
    
    @Override
    public Object getObject(Object key) {
    	init();
        Object object = cache.get(getKeyBytes(key));
        return object == null ? null : SerializeUtil.deserialize((byte[]) object);
    }

    @Override
    public Object removeObject(Object key) {
    	init();
    	Object object=getObject(key);
    	cache.del(getKeyBytes(key));
        return object;
    }

    @Override
    public void clear() {
    	init();
//    	cache.flushDB();
    	Set<byte[]> keys = cache.keys(keyPrefix+id+":"+"*");
    	for (byte[] key : keys) {
			cache.del(key);
		}
    }
    @Override
    public ReadWriteLock getReadWriteLock() {
        return readWriteLock;
    }
    
    private void init(){
    	if(cache==null){
    		cache= SpringContextHolder.getBean("redisManager");
    	}
    }
}
