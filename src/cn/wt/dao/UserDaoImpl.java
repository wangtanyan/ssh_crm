package cn.wt.dao;

import java.util.List;

import org.springframework.orm.hibernate5.HibernateTemplate;

import cn.wt.domain.User;

public class UserDaoImpl implements UserDao{
	private HibernateTemplate hibernateTemplate;
	public void setHibernateTemplate(HibernateTemplate hibernateTemplate) {
		this.hibernateTemplate = hibernateTemplate;
	}
	
	//查询用户是否存在
	@SuppressWarnings("all")
	public User findUser(User user) {
		List<User> list = (List<User>) hibernateTemplate.find("from User where username=? and password=?", user.getUsername(),user.getPassword());
		if(list!=null&&list.size()!=0) {
			User resultUser = list.get(0);
			return resultUser;
		}else {
			return null;
		}
		
	}

	//编辑用户
	public void updateUser(User user) {
		hibernateTemplate.update(user);
	}

	//查询所有用户
	@SuppressWarnings("unchecked")
	public List<User> findAll() {
		return (List<User>) hibernateTemplate.find("from User");
	}
	
	
}
