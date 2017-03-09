package service;

import java.util.List;

/**   
* @Description:通用基础service
* @author 席志明
* @date 2016年6月23日 上午11:41:17 
* @version V1.0   
*/
public interface BaseService<T> {

	/**保存一个实体，null的属性也会保存，不会使用数据库默认值
	 * @param entiry
	 * @return
	 */
	int insert(T entiry);
	
	/**保存一个实体，null的属性不会保存，会使用数据库默认值
	 * @param entiry
	 * @return
	 */
	int insertSelective(T entiry);
	
	/**根据主键字段进行删除，方法参数必须包含完整的主键属性
	 * @param entiry
	 * @return
	 */
	int deleteByPrimaryKey(T entiry);
	
	/**根据主键字段进行查询，方法参数必须包含完整的主键属性，查询条件使用等号
	 * @param entiry
	 * @return
	 */
	T selectByPrimaryKey(T entiry);
	
	/**根据实体中的属性进行查询，只能有一个返回值，有多个结果是抛出异常，查询条件使用等号
	 * @param entiry
	 * @return
	 */
	T selectOne(T entiry);
	
	/**根据主键更新属性不为null的值
	 * @param entiry
	 * @return
	 */
	int updateByPrimaryKeySelective(T entiry);
	
	/**根据实体中的属性值进行查询，查询条件使用等号
	 * @param entiry
	 * @return
	 */
	List<T> select(T entiry);
}
