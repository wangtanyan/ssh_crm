package cn.wt.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.wt.dao.LinkManDao;
import cn.wt.domain.Customer;
import cn.wt.domain.LinkMan;
import cn.wt.domain.PageBean;

@Transactional
public class LinkManService {
	private LinkManDao linkManDao;
	public void setLinkManDao(LinkManDao linkManDao) {
		this.linkManDao = linkManDao;
	}
	private CustomerService customerService;
	public void setCustomerService(CustomerService customerService) {
		this.customerService = customerService;
	}

	//添加联系人
	public int addLinkMan(LinkMan linkMan) {
		return linkManDao.addLinkMan(linkMan);
	}
	
	//分页查询所有联系人
	public PageBean findPageList(int currentPage) {
		int pageSize = 3;
		int begin = (currentPage-1)*pageSize;
		int totalCount = linkManDao.getTotalCount();
		
		int totalPage;
		if(totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}
		List<LinkMan> list = linkManDao.findPageList(begin,pageSize);
		//在联系人中查询客户名出错
		//（解决办法一）配置OpenSessionInViewFilter监听器
		//（解决办法二）如下：
		/*for (LinkMan linkMan : list) {
			Customer customer = customerService.findById(linkMan.getCustomer().getCid());
			linkMan.setCustomer(customer);
		}*/
		
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setTotalPage(totalPage);
		pageBean.setTotalCount(totalCount);
		pageBean.setCurrentPage(currentPage);
		pageBean.setList(list);
		return pageBean;
	}
	
	//删除联系人
	public String deleteLinkMan(LinkMan linkMan) {
		LinkMan findLinkMan = linkManDao.findById(linkMan.getLid());
		if(findLinkMan!=null) {
			linkManDao.deleteLinkMan(findLinkMan);
			return "Success";
		}else {
			return "Error";
		}
	}
	
	//修改联系人
	public String editLinkMan(LinkMan linkMan,int clid) {
		//级联修改
		Customer customer = customerService.findById(clid);
		if(customer!=null) {
			linkMan.setCustomer(customer);
			linkManDao.editLinkMan(linkMan);
			return "Success";
		}else {
			return "Error";
		}
	}
		

	//通过id查询联系人
	public LinkMan findLinkManById(int lid) {
		LinkMan linkMan = linkManDao.findById(lid);
		Customer customer = customerService.findById(linkMan.getCustomer().getCid());
		linkMan.setCustomer(customer);
		return linkMan;
	}

	//通过条件查询
	public PageBean findByRequirement(LinkMan linkMan,int currentPage) {
		int pageSize = 3;
		int begin = (currentPage-1)*pageSize;
		List<LinkMan> list1 = linkManDao.findTotalCount(linkMan);
		int totalCount = list1.size();
		int totalPage;
		if(totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}
		List<LinkMan> list2 = linkManDao.findListByRequirement(linkMan,begin,pageSize);
		/*通过配置OpenSessionInViewFilter可进行级联查询
		 * for (LinkMan linkMan2 : list2) {
			Customer customer = customerService.findById(linkMan2.getCustomer().getCid());
			linkMan2.setCustomer(customer);
		}*/
		PageBean pageBean = new PageBean();
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setCurrentPage(currentPage);
		pageBean.setList(list2);
		return pageBean;
	}

}
