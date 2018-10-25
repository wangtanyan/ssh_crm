package cn.wt.service;

import java.util.List;

import org.springframework.transaction.annotation.Transactional;

import cn.wt.dao.VisitDao;
import cn.wt.domain.PageBean;
import cn.wt.domain.Visit;

@Transactional
public class VisitService {
	private VisitDao visitDao;
	public void setVisitDao(VisitDao visitDao) {
		this.visitDao = visitDao;
	}
	
	//添加客户访问记录
	public void addVisit(Visit visit) {
		visitDao.addVisit(visit);
	}

	//查询所有客户拜访记录
	public PageBean visitList(int currentPage) {
		int pageSize = 3;
		int begin = (currentPage-1)*pageSize;
		int totalCount = visitDao.getTotalCount();
		int totalPage;
		if(totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}
		List<Visit> list = visitDao.allVisit(begin,pageSize);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		pageBean.setCurrentPage(currentPage);
		pageBean.setList(list);
		return pageBean;
	}

	//多条件组合查询
	public PageBean moreCondition(int currentPage, Visit visit) {
		int pageSize = 3;
		int begin = (currentPage-1)*pageSize;
		List<Visit> list = visitDao.moreConCount(visit);
		int totalCount = list.size();
		int totalPage;
		if(totalCount%pageSize==0) {
			totalPage = totalCount/pageSize;
		}else {
			totalPage = totalCount/pageSize+1;
		}
		List<Visit> list1 = visitDao.moreCondition(pageSize,begin,visit);
		PageBean pageBean = new PageBean();
		pageBean.setPageSize(pageSize);
		pageBean.setCurrentPage(currentPage);
		pageBean.setList(list1);
		pageBean.setTotalCount(totalCount);
		pageBean.setTotalPage(totalPage);
		return pageBean;
	}

	//删除客户访问记录
	public void deleteVisit(Visit visit) {
		visitDao.deleteVisit(visit);
	}

	//修改客户访问记录
	public void updateVisit(Visit visit) {
		visitDao.updateVisit(visit);
	}

	//通过Id查询客户访问记录
	public Visit findVisitById(Integer vid) {
		return visitDao.findVisitById(vid);
	}
	
}
