package cn.wt.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.wt.domain.LinkMan;
@SuppressWarnings("unchecked")
public class LinkManDaoImpl extends HibernateDaoSupport implements LinkManDao{
	
	//添加联系人
	public int addLinkMan(LinkMan linkMan) {
		Serializable result = this.getHibernateTemplate().save(linkMan);
		return Integer.parseInt(result.toString());
	}

	//获取总记录数
	public int getTotalCount() {
		List<?> list = this.getHibernateTemplate().find("select count(*) from LinkMan");
		Long result = (Long) list.get(0);
		int totalCount = result.intValue();
		return totalCount;
	}

	//获取每页记录数
	public List<LinkMan> findPageList(int begin, int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	//通过Id查询联系人
	public LinkMan findById(Integer lid) {
		return this.getHibernateTemplate().get(LinkMan.class,lid);
	}

	//删除联系人
	public void deleteLinkMan(LinkMan findLinkMan) {
		this.getHibernateTemplate().delete(findLinkMan);
	}

	//修改联系人
	public void editLinkMan(LinkMan linkMan) {
		this.getHibernateTemplate().update(linkMan);
	}

	//按条件查询记录数
	public List<LinkMan> findTotalCount(LinkMan linkMan) {
		StringBuilder url = new StringBuilder("from LinkMan where 1=1");
		List<Object> p = new ArrayList<Object>();
		if(linkMan.getLkmName()!=null&&!linkMan.getLkmName().trim().isEmpty()) {
			url.append(" and lkmName like ?");
			p.add("%"+linkMan.getLkmName()+"%");
		}
		if(linkMan.getLkmGender()!=null&&!linkMan.getLkmGender().trim().isEmpty()) {
			url.append(" and lkmGender like ?");
			p.add(linkMan.getLkmGender());
		}
		//通过hibernate原始方法查询关联表可通过外键进行查询
		if(linkMan.getCustomer().getCid()!=null) {
			url.append(" and clid = ?");
			p.add(linkMan.getCustomer().getCid());
		}
		List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().find(url.toString(),p.toArray());
		return  list;
	}

	//按条件查询记录
	public List<LinkMan> findListByRequirement(LinkMan linkMan,int begin,int pageSize) {
		DetachedCriteria criteria = DetachedCriteria.forClass(LinkMan.class);
		if(linkMan.getLkmName()!=null&&!linkMan.getLkmName().trim().isEmpty()) {
			criteria.add(Restrictions.like("lkmName", "%"+linkMan.getLkmName()+"%"));
		}
		if(linkMan.getLkmGender()!=null&&!linkMan.getLkmGender().trim().isEmpty()) {
			criteria.add(Restrictions.like("lkmGender", "%"+linkMan.getLkmGender()+"%"));
		}
		if(linkMan.getCustomer().getCid()!=null) {
			//通过离线对象查询关联表可通过向离线对象中添加customer对象作为查询参数进行查询
			criteria.add(Restrictions.like("customer", linkMan.getCustomer()));
		}
		List<LinkMan> list = (List<LinkMan>) this.getHibernateTemplate().findByCriteria(criteria,begin, pageSize);
		
		return list;
	}

}
