package service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import service.BaseService;
import tk.mybatis.mapper.common.Mapper;

/**   
* @Description:通过service的默认实现，采用通用mapper的实现
* @author 席志明
* @date 2016年6月23日 上午11:46:40 
* @version V1.0   
*/
//@Service
public abstract class BaseServiceImpl<T> implements BaseService<T> {
	@Autowired
	protected Mapper<T> mapper;
	
	public int insert(T entiry){
		return mapper.insert(entiry);
	}
	
	public int insertSelective(T entiry){
		return mapper.insertSelective(entiry);
	}
	
	public int deleteByPrimaryKey(T entiry){
		return mapper.deleteByPrimaryKey(entiry);
	}
	
	public T selectByPrimaryKey(T entiry){
		return mapper.selectByPrimaryKey(entiry);
	}
	
	public T selectOne(T entiry){
		return mapper.selectOne(entiry);
	}
	
	public int updateByPrimaryKeySelective(T entiry){
		return mapper.updateByPrimaryKeySelective(entiry);
	}
	
	public List<T> select(T entiry){
		return mapper.select(entiry);
	}

}
