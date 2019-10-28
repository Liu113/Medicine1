package com.medicine.dao;

import java.util.Date;
import java.util.List;

import org.hibernate.LockOptions;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import static org.hibernate.criterion.Example.create;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.transaction.annotation.Transactional;

import com.medicine.entity.Provider;

/**
 * A data access object (DAO) providing persistence and search support for
 * Provider entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.medicine.entity.Provider
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ProviderDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ProviderDAO.class);
	// property constants
	public static final String PNAME = "pname";
	public static final String ADDRESS = "address";
	public static final String PRINCIPAL = "principal";
	public static final String TELEPHONE = "telephone";
	public static final String IS_DELETE = "isDelete";
	public static final String PSTATUS = "pstatus";
	public static final String SPARE1 = "spare1";
	public static final String SPARE2 = "spare2";

	private SessionFactory sessionFactory;

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private Session getCurrentSession() {
		return sessionFactory.getCurrentSession();
	}

	protected void initDao() {
		// do nothing
	}

	public void save(Provider transientInstance) {
		log.debug("saving Provider instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Provider persistentInstance) {
		log.debug("deleting Provider instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Provider findById(java.lang.Integer id) {
		log.debug("getting Provider instance with id: " + id);
		try {
			Provider instance = (Provider) getCurrentSession().get(
					"com.medicine.entity.Provider", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Provider> findByExample(Provider instance) {
		log.debug("finding Provider instance by example");
		try {
			List<Provider> results = (List<Provider>) getCurrentSession()
					.createCriteria("com.medicine.entity.Provider")
					.add(create(instance)).list();
			log.debug("find by example successful, result size: "
					+ results.size());
			return results;
		} catch (RuntimeException re) {
			log.error("find by example failed", re);
			throw re;
		}
	}

	public List findByProperty(String propertyName, Object value) {
		log.debug("finding Provider instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Provider as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Provider> findByPname(Object pname) {
		return findByProperty(PNAME, pname);
	}

	public List<Provider> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<Provider> findByPrincipal(Object principal) {
		return findByProperty(PRINCIPAL, principal);
	}

	public List<Provider> findByTelephone(Object telephone) {
		return findByProperty(TELEPHONE, telephone);
	}

	public List<Provider> findByIsDelete(Object isDelete) {
		return findByProperty(IS_DELETE, isDelete);
	}

	public List<Provider> findByPstatus(Object pstatus) {
		return findByProperty(PSTATUS, pstatus);
	}

	public List<Provider> findBySpare1(Object spare1) {
		return findByProperty(SPARE1, spare1);
	}

	public List<Provider> findBySpare2(Object spare2) {
		return findByProperty(SPARE2, spare2);
	}

	public List findAll() {
		log.debug("finding all Provider instances");
		try {
			String queryString = "from Provider";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Provider merge(Provider detachedInstance) {
		log.debug("merging Provider instance");
		try {
			Provider result = (Provider) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(Provider instance) {
		log.debug("attaching dirty Provider instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Provider instance) {
		log.debug("attaching clean Provider instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProviderDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ProviderDAO) ctx.getBean("ProviderDAO");
	}
	
/**********************************************************/
	
	/**
	 * 统计供应商的数量
	 * @return
	 */
	public Integer getCount(Integer is_delete) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Provider p ";
		Query query = getCurrentSession().createQuery(hql);
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}
	
	/**
	 * 查询所有供应商列表
	 */
	public List<Provider> findAllList(Integer offset,Integer length) {
		// TODO Auto-generated method stub
		String hql = "from Provider p ";
		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(length);
		return query.list();
	}
	
	/**
	 * 查询所有供应商(未删除)列表
	 */
	public List<Provider> findAllKwList(Integer offset,Integer length,Integer is_delete,Integer pid,String pname) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder("from Provider p where p.isDelete=:is_delete");
		//判断供应商状态是否为删除
		if(!"".equals(is_delete) && is_delete != null){
			hql.append(" and p.isDelete =:is_delete");
		}
		//判断pid是否为空
		if(!"".equals(pid) && pid != null){
			hql.append(" and p.pid =:pid");
		}
		if(!"".equals(pname) && pname != null){
			hql.append(" and p.pname like :pname");
		}
		Query query = getCurrentSession().createQuery(hql.toString());
		//hql语句中存放is_delete
		if(!"".equals(is_delete) && is_delete != null){
			query.setInteger("is_delete", is_delete);
		}
		//判断pid是否为空
		if(!"".equals(pid) && pid != null){
			query.setInteger("pid", pid);
		}
		if(!"".equals(pname) && pname != null){
			query.setString("pname", "%"+pname+"%");
		}
		query.setFirstResult(offset);
		query.setMaxResults(length);
		return query.list();
	}
	/**
	 * 查询符合条件的信息数
	 */
	public Integer getCountKw( Integer pid, String pname) {
		// TODO Auto-generated method stub
		
		StringBuilder hql = new StringBuilder("select count(*) from Provider p where 1=1 ");
		//判断供应商状态是否为删除
		/*if(!"".equals(is_delete) && is_delete != null){
			hql.append(" and p.isDelete =:is_delete");
		}*/
		//判断pid是否为空
		if(!"".equals(pid) && pid != null){
			hql.append(" and p.pid =:pid");
		}
		if(!"".equals(pname) && pname != null){
			hql.append(" and p.pname like :pname");
		}
		Query query = getCurrentSession().createQuery(hql.toString());
		//hql语句中存放is_delete
		/*if(!"".equals(is_delete) && is_delete != null){
			query.setInteger("is_delete", is_delete);
		}*/
		//判断pid是否为空
		if(!"".equals(pid) && pid != null){
			query.setInteger("pid", pid);
		}
		if(!"".equals(pname) && pname != null){
			query.setString("pname", "%"+pname+"%");
		}
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}
}