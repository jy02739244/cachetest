package cache;

import java.util.concurrent.ConcurrentHashMap;


import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import dto.VO;

/**   
* @Title: SerializeUtil.java 
* @Package cn.rfidcer.cache 
* @Description:序列化工具类
* @author 席志明
* @Copyright Copyright
* @date 2016年8月18日 下午4:45:43 
* @version V1.0   
*/
public class SerializeUtil {
    private static ConcurrentHashMap<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();
    @SuppressWarnings("rawtypes")
	public static <T> byte[] serialize(final T source) {
        VO<T> vo = new VO<T>(source);
        final LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
        try {
            final Schema<VO> schema = getSchema(VO.class);
            return serializeInternal(vo, schema, buffer);
        } catch (final Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        } finally {
            buffer.clear();
        }
    }
    @SuppressWarnings({ "rawtypes", "unchecked" })
	public static <T> T deserialize(final byte[] bytes) {
        try {
            Schema<VO> schema = getSchema(VO.class);
            VO vo = deserializeInternal(bytes, schema.newMessage(), schema);
            if (vo != null && vo.getValue() != null) {
                return (T) vo.getValue();
            }
        } catch (final Exception e) {
            throw new IllegalStateException(e.getMessage(), e);
        }
        return null;
    }
    private static <T> byte[] serializeInternal(final T source, final Schema<T> schema, final LinkedBuffer buffer) {
        return ProtostuffIOUtil.toByteArray(source, schema, buffer);
    }
    private static <T> T deserializeInternal(final byte[] bytes, final T result, final Schema<T> schema) {
        ProtostuffIOUtil.mergeFrom(bytes, result, schema);
        return result;
    }
    @SuppressWarnings("unchecked")
	private static <T> Schema<T> getSchema(Class<T> clazz) {
        Schema<T> schema = (Schema<T>) cachedSchema.get(clazz);
        if (schema == null) {
            schema = RuntimeSchema.createFrom(clazz);
            cachedSchema.put(clazz, schema);
        }
        return schema;
    }
}
