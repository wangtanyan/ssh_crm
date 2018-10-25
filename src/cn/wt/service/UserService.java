package cn.wt.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.wt.dao.UserDao;
import cn.wt.domain.User;

@Transactional
public class UserService {
	private UserDao userDao;
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	
	//登录功能
	public User login(User user) {
		User resultUser = userDao.findUser(user);
		return resultUser;
	}
	
	//修改密码
	public void updateUser(User user) {
		userDao.updateUser(user);
		
	}

	//查询所有用户
	public List<User> findAll() {
		return userDao.findAll();
	}
}
