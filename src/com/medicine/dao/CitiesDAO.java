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

import com.medicine.entity.Cities;

/**
 * A data access object (DAO) providing persistence and search support for
 * Cities entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.medicine.entity.Cities
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class CitiesDAO {
	private static final Logger log = LoggerFactory.getLogger(CitiesDAO.class);
	// property constants
	public static final String CITYID = "cityid";
	public static final String CITY = "city";
	public static final String PROVINCEID = "provinceid";

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

	public void save(Cities transientInstance) {
		log.debug("saving Cities instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Cities persistentInstance) {
		log.debug("deleting Cities instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Cities findById(java.lang.Integer id) {
		log.debug("getting Cities instance with id: " + id);
		try {
			Cities instance = (Cities) getCurrentSession().get(
					"com.medicine.entity.Cities", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Cities> findByExample(Cities instance) {
		log.debug("finding Cities instance by example");
		try {
			List<Cities> results = (List<Cities>) getCurrentSession()
					.createCriteria("com.medicine.entity.Cities")
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
		log.debug("finding Cities instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Cities as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Cities> findByCityid(Object cityid) {
		return findByProperty(CITYID, cityid);
	}

	public List<Cities> findByCity(Object city) {
		return findByProperty(CITY, city);
	}

	public List<Cities> findByProvinceid(Object provinceid) {
		return findByProperty(PROVINCEID, provinceid);
	}

	public List findAll() {
		log.debug("finding all Cities instances");
		try {
			String queryString = "from Cities";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Cities merge(Cities detachedInstance) {
		log.debug("merging Cities instance");
		try {
			Cities result = (Cities) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Cities instance) {
		log.debug("attaching dirty Cities instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Cities instance) {
		log.debug("attaching clean Cities instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static CitiesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (CitiesDAO) ctx.getBean("CitiesDAO");
	}
}