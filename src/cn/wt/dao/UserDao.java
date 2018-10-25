package cn.wt.dao;

import java.util.List;

import cn.wt.domain.User;

public interface UserDao {
	public User findUser(User user);
	
	public void updateUser(User user);

	public List<User> findAll();
}
