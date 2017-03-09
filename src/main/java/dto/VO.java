package dto;

import java.io.Serializable;

/**   
* @Title: VO.java 
* @Package cn.rfidcer.dto 
* @Description:Mybatis序列化的封装对象
* @author 席志明
* @Copyright Copyright
* @date 2016年8月18日 下午4:43:46 
* @version V1.0   
*/
public class VO<T> implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = -2962838547224364298L;
	private T value;
    public VO(T value) {
        this.value = value;
    }
    public T getValue() {
        return value;
    }
    @Override
    public String toString() {
        return "VO{" +
                "value=" + value +
                '}';
    }
}
