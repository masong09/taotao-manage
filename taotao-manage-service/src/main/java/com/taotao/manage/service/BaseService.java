package com.taotao.manage.service;

import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.github.abel533.entity.Example;
import com.github.abel533.mapper.Mapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.taotao.manage.pojo.BasePojo;

public abstract class BaseService<T extends BasePojo> {

	@Autowired
	public Mapper<T>  mapper;
	
	//public abstract Mapper<T> getMapper();
	public  Mapper<T> getMapper(){
		return this.mapper;
	};
	
	
	private Class<T> clazz;
	
	public BaseService(){
		
		super();
		Type type = this.getClass().getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) type;
		this.clazz = (Class<T>) pType.getActualTypeArguments()[0];
	}
	
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	public T queryById(Long id){
		
		T t = this.getMapper().selectByPrimaryKey(id);
		return t;
	}
	
	/**
	 * 查询所有数据
	 * @return
	 */
	public List<T> queryAll(){
		List<T> list = this.getMapper().select(null);
		return list;
	}
	
	/**
	 * 查询数据总条数
	 * @return
	 */
	public Integer queryAllCount(){
		
		int count = this.getMapper().selectCount(null);
		return count;
	}
	
	/**
	 * 根据条件查询
	 * @param t
	 * @return
	 */
	
	public List<T> queryListByWhere(T t){
		
		List<T> list = this.getMapper().select(t);
		return list;
	}
	
	/**
	 * 根据条件分页查询
	 * @param t
	 * @param page
	 * @param rows
	 * @return
	 */
	public PageInfo<T> queryListByPage(T t , Integer page,Integer rows){
		
		PageHelper.startPage(page,rows);
		List<T> list = this.getMapper().select(t);
		
		PageInfo<T> pageInfo = new PageInfo<T>(list);
		
		return pageInfo;
	}
	
	/**
	 * 根据条件查询一条数据
	 * @param t
	 * @return
	 */
	public T queryOne(T t){
		
		T one = this.getMapper().selectOne(t);
		return one;
				
	}
	
	/**
	 * 保存数据
	 * @param t
	 * @return
	 */	
	public Integer save(T t){
		
		t.setCreated(new Date());
		t.setUpdated(t.getCreated());
		int i = this.getMapper().insert(t);
		return i;
	}
	
	/**
	 * 保存数据忽略空字段
	 * @param t
	 * @return
	 */	
	public Integer saveSelective(T t){
		
		int i = this.getMapper().insertSelective(t);
		return i;
	}
	
	/**
	 * 修改数据
	 * @param t
	 * @return
	 */
	public Integer updateById(T t){
		t.setUpdated(new Date());
		int i = this.getMapper().updateByPrimaryKey(t);
		return i;
	}
	
	/**
	 * 修改数据胡忽略空字段
	 * @param t
	 * @return
	 */
	public Integer updateByPrimaryKeySelective(T t){
		t.setUpdated(new Date());
		int i = this.getMapper().updateByPrimaryKeySelective(t);
		return i;
	}
	
	/**
	 * 根据id删除
	 * @param id
	 * @return
	 */	
	public Integer deleteById(Long id){
		int i = this.getMapper().deleteByPrimaryKey(id);
		return i;
	}
	
	/**
	 * 批量删除
	 * @param ids
	 * @return
	 */
	public Integer deleteByIds(List<Object> ids){
		Example example = new Example(clazz);
		example.createCriteria().andIn("id", ids);		
		int i = this.getMapper().deleteByExample(example);
		
		return i;
		
	}
}
