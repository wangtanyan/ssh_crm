package cn.wt.dao;

import java.util.List;

public interface BaseDao<T> {
	public String add(T t);
	public void delete(T t);
	public void update(T t);
	//查询全部
	public List<T> findAll();
	//通过Id查询
	public T findById(int id);
}
