package cn.wt.dao;

import java.util.List;

import cn.wt.domain.PageBean;
import cn.wt.domain.Visit;

public interface VisitDao {

	public void addVisit(Visit visit);

	public List<Visit> allVisit(int begin, int pageSize);

	public int getTotalCount();

	public List<Visit> moreConCount(Visit visit);

	public List<Visit> moreCondition(int pageSize, int begin, Visit visit);

	public void deleteVisit(Visit visit);

	public void updateVisit(Visit visit);

	public Visit findVisitById(Integer vid);

}
