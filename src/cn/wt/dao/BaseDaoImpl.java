package cn.wt.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

@SuppressWarnings("all")
public class BaseDaoImpl<T> extends HibernateDaoSupport implements BaseDao<T> {
	
	private String customerName;
	private Class custoemr;

	public BaseDaoImpl(){
		//1.得到当前运行的类
		Class clazz = this.getClass();
		
		//2.得到当前类的父类的参数化类型
		Type type = clazz.getGenericSuperclass();
		ParameterizedType pType = (ParameterizedType) type;
		//3.得到参数化类型中的实际参数中的类
		Type[] types = pType.getActualTypeArguments();
		custoemr =  (Class) types[0];
		customerName = custoemr.getSimpleName();
	}
	
	//添加
	public String add(T t) {
		Serializable result = this.getHibernateTemplate().save(t);
		Integer indes = Integer.valueOf(result.toString());
		if(indes>0) {
			return "addSuccess";
		}else {
			return "addError";
		}
	}

	//删除
	public void delete(T t) {
		this.getHibernateTemplate().delete(t);
	}

	//修改
	public void update(T t) {
		this.getHibernateTemplate().update(t);
	}

	//查询全部
	public List<T> findAll() {
		return (List<T>) this.getHibernateTemplate().find("from "+customerName);
	}

	//通过Id查询
	public T findById(int id) {
		return (T) this.getHibernateTemplate().get(custoemr, id);
	}

}
