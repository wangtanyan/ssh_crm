package cn.wt.dao;

import java.util.List;

import cn.wt.domain.Customer;
import cn.wt.domain.Dict;

public interface CustomerDao extends BaseDao<Customer>{
//	public String addCustomer(Customer customer);
//	public void updateCustomer(Customer customer);
//	public void deleteCustomer(Customer customer);
//	public List<Customer> findAll();
//	public Customer findById(int cusId);
	public int getTotalCount();
	public List<Customer> findPageList(int begin, int pageSize);
	public List<Customer> fingByName(Customer customer, int begin, int pageSize);
	public int findCountByName(Customer customer);
	public int findCountCoundition(Customer customer);
	public List<Customer> findListCountion(int pageSize, int begin, Customer customer);
	public List<Dict> findDict();
	public int sourceStat();
	public List sourceStatList(int begin, int pageSize);
	public int levelStat();
	public List levelStatList(int begin, int pageSize);
}
