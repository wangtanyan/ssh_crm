package cn.wt.dao;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.transform.Transformers;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;

import com.sun.javafx.geom.TransformedShape;

import cn.wt.domain.Customer;
import cn.wt.domain.Dict;
import javassist.convert.Transformer;
@SuppressWarnings("unchecked")
public class CustomerDaoImpl extends BaseDaoImpl<Customer> implements CustomerDao{
	
	
/*	通过抽取BaseDao进行优化
 * 
 * //通过id查询客户
	public Customer findById(int cusId) {
		return this.getHibernateTemplate().get(Customer.class, cusId);
	}
	
	//添加客户
	public String addCustomer(Customer customer) {
		Serializable result = this.getHibernateTemplate().save(customer);
		Integer indes = Integer.valueOf(result.toString());
		if(indes>0) {
			return "addSuccess";
		}else {
			return "addError";
		}
		
	}
	
	//修改客户
	public void updateCustomer(Customer customer) {
		this.getHibernateTemplate().update(customer);
		
	}

	//删除客户
	public void deleteCustomer(Customer customer) {
		this.getHibernateTemplate().delete(customer);
	}

	//查询所有客户
	public List<Customer> findAll() {
		List<Customer> customers = (List<Customer>) this.getHibernateTemplate().find("from Customer");
		return customers;
		
	}
*/
	//查询总记录数
	public int getTotalCount() {
		List<?> list = this.getHibernateTemplate().find("select count(*) from Customer");
		Long result = (Long) list.get(0);
		int totalCount = result.intValue();
		return totalCount;
	}

	//获取每页记录数
	public List<Customer> findPageList(int begin, int pageSize) {
		//通过hibernate原始方法获取
		/*SessionFactory factory = this.getSessionFactory();
		Session session = factory.openSession();
		Query query = session.createQuery("from Customer");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		List<Customer> list = query.list();*/
		
		//通过离线对象获取
		//1.创建离线对象
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		//2.添加实体类的属性作为查询条件
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	//条件查询记录数
	public int findCountByName(Customer customer) {
		List<?> list = this.getHibernateTemplate().find("select Count(*) from Customer where custName like ?", "%"+customer.getCustName()); 
		Long countS = (Long) list.get(0);
		int countI = countS.intValue();
		return countI;
	}
	//通过客户名查找
	public List<Customer> fingByName(Customer customer,int begin,int pageSize) {
		/*//方法一 hibernate原始方法
		SessionFactory sessionFactory = this.getHibernateTemplate().getSessionFactory();
		Session session = sessionFactory.openSession();
		Query query = session.createQuery("from Customer where custName like ?");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		query.setParameter(0, "%"+customer.getCustName()+"%");
		List<Customer> list = query.list();*/
		
		/*//方法二 调用HibernateTemplate的find方法实现
		List<Customer> list =(List<Customer>) this.getHibernateTemplate().find("from Customer where custName like ?", "%"+customer.getCustName()+"%");
		return list; */
		
		//方法三 通过离线对象获取
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		criteria.add(Restrictions.like("custName", "%"+customer.getCustName()+"%"));
		List<Customer> list = (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
		return list;
	}

	//多条件查询记录数
	public int findCountCoundition(Customer customer) {
		String custName = customer.getCustName();
		String custLevel = customer.getCustLevel().getDid();
		String custSource = customer.getCustSource();
		StringBuilder url = new StringBuilder("select count(*) from Customer where 1=1");
		List<Object> parameter = new ArrayList<Object>();
		if(custName!=null&&!custName.trim().isEmpty()) {
			url.append(" and custName=?");
			parameter.add(custName);
		}
		if(custLevel!=null&&!custLevel.trim().isEmpty()) {
			url.append(" and custLevelDict=?");
			parameter.add(custLevel);
		}
		
		if(custSource!=null&&!custSource.trim().isEmpty()) {
			url.append(" and custSource=?");
			parameter.add(custSource);
		}
		Long count = (Long) this.getHibernateTemplate().find(url.toString(), parameter.toArray()).get(0);
		
		return count.intValue();
	}

	//多条件分页查询记录列表
	public List<Customer> findListCountion(int pageSize, int begin, Customer customer) {
		DetachedCriteria criteria = DetachedCriteria.forClass(Customer.class);
		String custName = customer.getCustName();
		String custLevel = customer.getCustLevel().getDid();
		String custSource = customer.getCustSource();
		if(custName!=null&&!custName.trim().isEmpty()) {
			criteria.add(Restrictions.like("custName", "%"+custName+"%"));
		}
		if(custLevel!=null&&!custLevel.trim().isEmpty()) {
			criteria.add(Restrictions.like("custLevel.did", custLevel));
		}
		if(custSource!=null&&!custSource.trim().isEmpty()) {
			criteria.add(Restrictions.like("custSource", "%"+custSource+"%"));
		}
		return (List<Customer>) this.getHibernateTemplate().findByCriteria(criteria, begin, pageSize);
	}

	//获取客户级别数据字典
	public List<Dict> findDict() {
		List<Dict> dicts = (List<Dict>) this.getHibernateTemplate().find("from Dict");
		return dicts;
	}

	//获取根据客户来源统计记录数
	public int sourceStat() {
		SessionFactory sessionFactory = this.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("SELECT COUNT(*) AS num,custSource FROM crm_customer GROUP BY custSource");
		query.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		int count = query.list().size();
		return count;
	}

	//根据客户来源获取记录
	public List sourceStatList(int begin, int pageSize) {
		SessionFactory sessionFactory = this.getSessionFactory();
		Session session = sessionFactory.openSession();
		SQLQuery query = session.createSQLQuery("SELECT COUNT(*) AS num,custSource FROM crm_customer GROUP BY custSource");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		query.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = query.list();
		return list;
	}

	//根据客户级别统计
	public int levelStat() {
		SessionFactory sessionFactory = this.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("SELECT num , dname FROM "
				+ "(SELECT COUNT(*) AS num,custLevelDict FROM crm_customer GROUP BY custLevelDict) c,"
				+ "crm_dict d WHERE c.custLevelDict = d.did");
		query.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		int count = query.list().size();
		return count;
	}

	//根据客户级别查询记录
	public List levelStatList(int begin, int pageSize) {
		SessionFactory sessionFactory = this.getSessionFactory();
		Session session = sessionFactory.getCurrentSession();
		SQLQuery query = session.createSQLQuery("SELECT num,dname FROM "
				+ "(SELECT COUNT(*) num,custLevelDict FROM crm_customer GROUP BY custLevelDict) c,"
				+ "crm_dict d WHERE c.custLevelDict=d.did");
		query.setFirstResult(begin);
		query.setMaxResults(pageSize);
		query.setResultTransformer(Transformers.aliasToBean(HashMap.class));
		List list = query.list();
		return list;
	}


	

	
	
}
