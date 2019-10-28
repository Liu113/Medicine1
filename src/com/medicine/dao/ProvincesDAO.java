package com.medicine.dao;

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

import com.medicine.entity.Provinces;

/**
 * A data access object (DAO) providing persistence and search support for
 * Provinces entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.medicine.entity.Provinces
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ProvincesDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ProvincesDAO.class);
	// property constants
	public static final String PROVINCEID = "provinceid";
	public static final String PROVINCE = "province";

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

	public void save(Provinces transientInstance) {
		log.debug("saving Provinces instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Provinces persistentInstance) {
		log.debug("deleting Provinces instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Provinces findById(java.lang.Integer id) {
		log.debug("getting Provinces instance with id: " + id);
		try {
			Provinces instance = (Provinces) getCurrentSession().get(
					"com.medicine.entity.Provinces", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Provinces> findByExample(Provinces instance) {
		log.debug("finding Provinces instance by example");
		try {
			List<Provinces> results = (List<Provinces>) getCurrentSession()
					.createCriteria("com.medicine.entity.Provinces")
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
		log.debug("finding Provinces instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Provinces as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Provinces> findByProvinceid(Object provinceid) {
		return findByProperty(PROVINCEID, provinceid);
	}

	public List<Provinces> findByProvince(Object province) {
		return findByProperty(PROVINCE, province);
	}

	public List findAll() {
		log.debug("finding all Provinces instances");
		try {
			String queryString = "from Provinces";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Provinces merge(Provinces detachedInstance) {
		log.debug("merging Provinces instance");
		try {
			Provinces result = (Provinces) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Provinces instance) {
		log.debug("attaching dirty Provinces instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Provinces instance) {
		log.debug("attaching clean Provinces instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ProvincesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (ProvincesDAO) ctx.getBean("ProvincesDAO");
	}
}