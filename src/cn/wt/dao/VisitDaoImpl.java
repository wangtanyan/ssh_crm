package cn.wt.dao;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import cn.wt.domain.Visit;

public class VisitDaoImpl extends HibernateDaoSupport implements VisitDao {

	//添加客户访问记录
	public void addVisit(Visit visit) {
		this.getHibernateTemplate().save(visit);
	}

	//查询所有客户拜访记录
	@SuppressWarnings("unchecked")
	public List<Visit> allVisit(int begin, int pageSize) {
		//使用离线对象查询
		/*DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		List<Visit> list = (List<Visit>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);*/
		
		//通过hibernate原始方法查询
		SessionFactory sessionFactory = this.getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Visit");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Visit> list = query.list();
		return list;
	}

	//查询总记录数
	public int getTotalCount() {
		Long result = (Long) this.getHibernateTemplate().find("select count(*) from Visit").get(0);
		return result.intValue();
	}

	//多条件组合查询记录数
	@SuppressWarnings("unchecked")
	public List<Visit> moreConCount(Visit visit) {
		StringBuilder uri = new StringBuilder("from Visit where 1=1");
		List<Object> p = new ArrayList<Object>();
		if(visit.getVcustomer().getCid()!=null&&visit.getVcustomer().getCid()!=-1) {
			uri.append(" and vcid = ?");
			p.add(visit.getVcustomer().getCid());
		}
		if(visit.getVuser().getUid()!=null&&visit.getVuser().getUid()!=-1) {
			uri.append(" and vuid = ?");
			p.add(visit.getVuser().getUid());
		}
		if(visit.getVcontent()!=null&&!visit.getVcontent().trim().isEmpty()) {
			uri.append(" and vcontent like ?");
			p.add("%"+visit.getVcontent()+"%");
		}
		if(visit.getVaddress()!=null&&!visit.getVaddress().trim().isEmpty()) {
			uri.append(" and vaddress like ?");
			p.add("%"+visit.getVaddress()+"%");
		}
		List<Visit> list = (List<Visit>) this.getHibernateTemplate().find(uri.toString(), p.toArray());
		return list;
	}

	//多条件组合查询记录
	@SuppressWarnings("unchecked")
	public List<Visit> moreCondition(int pageSize, int begin, Visit visit) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Visit.class);
		if(visit.getVcustomer().getCid()!=null&&visit.getVcustomer().getCid()!=-1) {
			criteria.add(Restrictions.like("vcustomer", visit.getVcustomer()));
		}
		if(visit.getVuser().getUid()!=null&&visit.getVuser().getUid()!=-1) {
			criteria.add(Restrictions.like("vuser", visit.getVuser()));
		}
		if(visit.getVcontent()!=null&&!visit.getVcontent().trim().isEmpty()) {
			criteria.add(Restrictions.like("vcontent", "%"+visit.getVcontent()+"%"));
		}
		if(visit.getVaddress()!=null&&!visit.getVaddress().trim().isEmpty()) {
			criteria.add(Restrictions.like("vaddress", "%"+visit.getVaddress()+"%"));
		}
		List<Visit> list = (List<Visit>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	//删除客户访问记录
	public void deleteVisit(Visit visit) {
		this.getHibernateTemplate().delete(visit);
	}

	//更新客户访问记录
	public void updateVisit(Visit visit) {
		this.getHibernateTemplate().update(visit);
	}

	//通过Id查询客户访问记录
	public Visit findVisitById(Integer vid) {
		Visit visit = (Visit) this.getHibernateTemplate().find("from Visit where vid=?", vid).get(0);
		return visit;
	}

}
