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

import com.medicine.entity.Areas;

/**
 * A data access object (DAO) providing persistence and search support for Areas
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.medicine.entity.Areas
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class AreasDAO {
	private static final Logger log = LoggerFactory.getLogger(AreasDAO.class);
	// property constants
	public static final String AREAID = "areaid";
	public static final String AREA = "area";
	public static final String CITYID = "cityid";

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

	public void save(Areas transientInstance) {
		log.debug("saving Areas instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Areas persistentInstance) {
		log.debug("deleting Areas instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Areas findById(java.lang.Integer id) {
		log.debug("getting Areas instance with id: " + id);
		try {
			Areas instance = (Areas) getCurrentSession().get(
					"com.medicine.entity.Areas", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Areas> findByExample(Areas instance) {
		log.debug("finding Areas instance by example");
		try {
			List<Areas> results = (List<Areas>) getCurrentSession()
					.createCriteria("com.medicine.entity.Areas")
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
		log.debug("finding Areas instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Areas as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Areas> findByAreaid(Object areaid) {
		return findByProperty(AREAID, areaid);
	}

	public List<Areas> findByArea(Object area) {
		return findByProperty(AREA, area);
	}

	public List<Areas> findByCityid(Object cityid) {
		return findByProperty(CITYID, cityid);
	}

	public List findAll() {
		log.debug("finding all Areas instances");
		try {
			String queryString = "from Areas";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Areas merge(Areas detachedInstance) {
		log.debug("merging Areas instance");
		try {
			Areas result = (Areas) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Areas instance) {
		log.debug("attaching dirty Areas instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Areas instance) {
		log.debug("attaching clean Areas instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static AreasDAO getFromApplicationContext(ApplicationContext ctx) {
		return (AreasDAO) ctx.getBean("AreasDAO");
	}
}