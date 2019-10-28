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

import com.medicine.entity.Isdepotheader;
import com.medicine.entity.Medicine;
import com.medicine.entity.Sale;

/**
 * A data access object (DAO) providing persistence and search support for
 * Isdepotheader entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.medicine.entity.Isdepotheader
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class IsdepotheaderDAO {
	private static final Logger log = LoggerFactory
			.getLogger(IsdepotheaderDAO.class);
	// property constants
	public static final String TAB_MAN = "tabMan";
	public static final String STATUS = "status";
	public static final String REMARK = "remark";
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

	public void save(Isdepotheader transientInstance) {
		log.debug("saving Isdepotheader instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Isdepotheader persistentInstance) {
		log.debug("deleting Isdepotheader instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Isdepotheader findById(java.lang.Integer id) {
		log.debug("getting Isdepotheader instance with id: " + id);
		try {
			Isdepotheader instance = (Isdepotheader) getCurrentSession().get(
					"com.medicine.entity.Isdepotheader", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Isdepotheader> findByExample(Isdepotheader instance) {
		log.debug("finding Isdepotheader instance by example");
		try {
			List<Isdepotheader> results = (List<Isdepotheader>) getCurrentSession()
					.createCriteria("com.medicine.entity.Isdepotheader")
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
		log.debug("finding Isdepotheader instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Isdepotheader as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Isdepotheader> findByTabMan(Object tabMan) {
		return findByProperty(TAB_MAN, tabMan);
	}

	public List<Isdepotheader> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<Isdepotheader> findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List<Isdepotheader> findBySpare1(Object spare1) {
		return findByProperty(SPARE1, spare1);
	}

	public List<Isdepotheader> findBySpare2(Object spare2) {
		return findByProperty(SPARE2, spare2);
	}

	public List findAll() {
		log.debug("finding all Isdepotheader instances");
		try {
			String queryString = "from Isdepotheader";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Isdepotheader merge(Isdepotheader detachedInstance) {
		log.debug("merging Isdepotheader instance");
		try {
			Isdepotheader result = (Isdepotheader) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(Isdepotheader instance) {
		log.debug("attaching dirty Isdepotheader instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Isdepotheader instance) {
		log.debug("attaching clean Isdepotheader instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IsdepotheaderDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IsdepotheaderDAO) ctx.getBean("IsdepotheaderDAO");
	}
	
	/*****************************************************/
	/**
	 * 统计入库单的数量
	 * @return
	 */
	public Integer getCount(Integer userId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Isdepotheader m where m.tabMan =:userId ";
		Query query = getCurrentSession().createQuery(hql).setInteger("userId", userId);
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}

	/**
	 * 查询所有入库单列表
	 */
	public List<Isdepotheader> findAllList(Integer userId) {
		// TODO Auto-generated method stub
		String hql = "from Isdepotheader m where m.tabMan=:userId";
		Query query = getCurrentSession().createQuery(hql).setInteger("userId", userId);
		return query.list();
	}

	/**
	 * 根据查询条件统计入库单的数量
	 */
	public Integer getkwCount(Integer userId, Integer inDepotId) {
		String hql = "select count(*) from Isdepotheader m where m.tabMan =:userId and m.inDepotId=:inDepotId";
		Query query = getCurrentSession().createQuery(hql).setInteger("userId", userId).setInteger("inDepotId", inDepotId);
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}

	/**
	 * 查询所有符合条件的入库单
	 */
	public List<Isdepotheader> findAllKwList(Integer offset, Integer length, Integer userId, Integer inDepotId) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder("from Isdepotheader m where m.tabMan=:userId");
		if(!"".equals(inDepotId) || inDepotId != null){
			hql.append(" and m.inDepotId=:inDepotId");
		}
		Query query = getCurrentSession().createQuery(hql.toString()).setInteger("userId", userId);
		if(!"".equals(inDepotId) || inDepotId != null){
			query.setInteger("inDepotId", inDepotId);
		}
		query.setFirstResult(offset);
		query.setMaxResults(length);
		return query.list();
	}

	/**
	 * 统计所有入库单的总数
	 */
	public Integer getCountAll() {
		String hql = "select count(*) from Isdepotheader s  ";
		Query query = getCurrentSession().createQuery(hql);
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}
	
	/**
	 * 管理员查询所有入库单信息
	 */
	public List<Isdepotheader> AdminfindAllList() {
		String hql = "from Isdepotheader s ";
		Query query = getCurrentSession().createQuery(hql);
		return query.list();
	}
}