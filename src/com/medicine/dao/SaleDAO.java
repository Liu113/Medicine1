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
import com.medicine.entity.Sale;

/**
 * A data access object (DAO) providing persistence and search support for Sale
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.medicine.entity.Sale
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SaleDAO {
	private static final Logger log = LoggerFactory.getLogger(SaleDAO.class);
	// property constants
	public static final String SALE_PRICE = "salePrice";
	public static final String SALE_MAN_ID = "saleManId";
	public static final String SALE_MAN = "saleMan";
	public static final String REMARK = "remark";

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

	public void save(Sale transientInstance) {
		log.debug("saving Sale instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Sale persistentInstance) {
		log.debug("deleting Sale instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Sale findById(java.lang.Integer id) {
		log.debug("getting Sale instance with id: " + id);
		try {
			Sale instance = (Sale) getCurrentSession().get(
					"com.medicine.entity.Sale", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Sale> findByExample(Sale instance) {
		log.debug("finding Sale instance by example");
		try {
			List<Sale> results = (List<Sale>) getCurrentSession()
					.createCriteria("com.medicine.entity.Sale")
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
		log.debug("finding Sale instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Sale as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Sale> findBySalePrice(Object salePrice) {
		return findByProperty(SALE_PRICE, salePrice);
	}

	public List<Sale> findBySaleManId(Object saleManId) {
		return findByProperty(SALE_MAN_ID, saleManId);
	}

	public List<Sale> findBySaleMan(Object saleMan) {
		return findByProperty(SALE_MAN, saleMan);
	}

	public List<Sale> findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List findAll() {
		log.debug("finding all Sale instances");
		try {
			String queryString = "from Sale";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Sale merge(Sale detachedInstance) {
		log.debug("merging Sale instance");
		try {
			Sale result = (Sale) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Sale instance) {
		log.debug("attaching dirty Sale instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Sale instance) {
		log.debug("attaching clean Sale instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SaleDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SaleDAO) ctx.getBean("SaleDAO");
	}
	
/*************************************/
	
	/**
	 * 统计入库单的数量
	 * @return
	 */
	public Integer getCount(Integer userId) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Sale s where s.saleManId =:userId ";
		Query query = getCurrentSession().createQuery(hql).setInteger("userId", userId);
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}

	/**
	 * 查询所有入库单列表
	 */
	public List<Sale> findAllList(Integer userId) {
		// TODO Auto-generated method stub
		String hql = "from Sale s where s.saleManId=:userId";
		Query query = getCurrentSession().createQuery(hql).setInteger("userId", userId);
		return query.list();
	}

	/**
	 * 统计所有销售单的总数
	 */
	public Integer getCountAll() {
		String hql = "select count(*) from Sale s  ";
		Query query = getCurrentSession().createQuery(hql);
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}

	/**
	 * 管理员查询所有销售单信息
	 */
	public List<Sale> AdminfindAllList() {
		String hql = "from Sale s ";
		Query query = getCurrentSession().createQuery(hql);
		return query.list();
	}
}