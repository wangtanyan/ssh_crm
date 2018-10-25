package cn.wt.dao;

import java.util.List;

import cn.wt.domain.LinkMan;

public interface LinkManDao {
	public int addLinkMan(LinkMan linkMan);
	public int getTotalCount();
	public List<LinkMan> findPageList(int begin, int pageSize);
	public LinkMan findById(Integer lid);
	public void deleteLinkMan(LinkMan findLinkMan);
	public void editLinkMan(LinkMan linkMan);
	public List<LinkMan> findTotalCount(LinkMan linkMan);
	public List<LinkMan> findListByRequirement(LinkMan linkMan,int begin,int pageSize);
}
