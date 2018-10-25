package cn.wt.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.wt.dao.CustomerDao;
import cn.wt.domain.Customer;
import cn.wt.domain.Dict;
import cn.wt.domain.PageBean;

@Transactional
public class CustomerService {
	private CustomerDao customerDao;
	public void setCustomerDao(CustomerDao customerDao) {
		this.customerDao = customerDao;
	}
	//查询所有客户
	public List<Customer> findAll() {
		return customerDao.findAll();
	} 
	
	//按分页查询全部客户
	public PageBean findPageList(int currentPage){
		int pageSize = 3;
		int begin = (currentPage-1)*pageSize;
		int totalCount = customerDao.getTotalCount();
		List<Customer> list = customerDao.findPageList(begin,pageSize);
		int totalPage;
		if(totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalCount(totalCount);
		pageBean.setList(list);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}
	//添加客户
	public String addCustoemr(Customer customer) {
		return customerDao.add(customer);
	}
	
	//修改客户
	public String updateCustomer(Customer customer) {
		customerDao.update(customer);
		return "updateSuccess";
	}
	
	//删除客户
	public String deleteCustomer(String  cusId) {
		int cusIds = Integer.valueOf(cusId);
		Customer customer = customerDao.findById(cusIds);
		if(customer!=null) {
			customerDao.delete(customer);
			return "deleteSuccess";
		}else {
			return "deleteError";
		}
	}
	
	//通过id查询客户
	public Customer findById(int cid) {
		return customerDao.findById(cid);
	}
	
	//通过客户名查找
	public PageBean findByName(Customer customer,int currentPage) {
		int pageSize = 3;
		int begin = (currentPage-1)*pageSize;
		int totalCount = customerDao.findCountByName(customer);
		int totalPage;
		if(totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}
		List<Customer> list = customerDao.fingByName(customer,begin,pageSize);
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		pageBean.setList(list);
		pageBean.setCurrentPage(currentPage);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}
	
	//多条件组合查询
	public PageBean moreCondition(int currentPage, Customer customer) {
		int pageSize = 3;
		int begin = (currentPage-1)*pageSize;
		int totalCount = customerDao.findCountCoundition(customer);
		int totalPage;
		if(totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}
		List<Customer> list = customerDao.findListCountion(pageSize,begin,customer);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setList(list);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}
	
	//获取客户级别数据字典
	public List<Dict> findDict() {
		return customerDao.findDict();
	}
	
	//根据客户来源统计
	public PageBean sourceStat(int currentPage) {
		int pageSize = 3;
		int begin = (currentPage-1)*3;
		int totalCount = customerDao.sourceStat();
		int totalPage;
		if(totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}
		List list = customerDao.sourceStatList(begin,pageSize);
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		pageBean.setList(list);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}
	
	//根据客户级别统计
	public PageBean levelStat(int currentPage) {
		int pageSize = 3;
		int begin = (currentPage-1)*pageSize;
		int totalCount = customerDao.levelStat();
		int totalPage;
		if(totalCount%pageSize ==0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}
		List list = customerDao.levelStatList(begin,pageSize);
		PageBean pageBean = new PageBean();
		pageBean.setCurrentPage(currentPage);
		pageBean.setList(list);
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}
}
