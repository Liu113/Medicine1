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

import com.medicine.entity.Applicationdesc;

/**
 * A data access object (DAO) providing persistence and search support for
 * Applicationdesc entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.medicine.entity.Applicationdesc
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class ApplicationdescDAO {
	private static final Logger log = LoggerFactory
			.getLogger(ApplicationdescDAO.class);
	// property constants
	public static final String APPLICATION_ID = "applicationId";
	public static final String PROVIDER_ID = "providerId";
	public static final String MEDICINE_ID = "medicineId";
	public static final String NOW_NUM = "nowNum";
	public static final String NEED_NUM = "needNum";
	public static final String REMARK = "remark";
	public static final String SPARE1 = "spare1";

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

	public void save(Applicationdesc transientInstance) {
		log.debug("saving Applicationdesc instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Applicationdesc persistentInstance) {
		log.debug("deleting Applicationdesc instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Applicationdesc findById(java.lang.Integer id) {
		log.debug("getting Applicationdesc instance with id: " + id);
		try {
			Applicationdesc instance = (Applicationdesc) getCurrentSession()
					.get("com.medicine.entity.Applicationdesc", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Applicationdesc> findByExample(Applicationdesc instance) {
		log.debug("finding Applicationdesc instance by example");
		try {
			List<Applicationdesc> results = (List<Applicationdesc>) getCurrentSession()
					.createCriteria("com.medicine.entity.Applicationdesc")
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
		log.debug("finding Applicationdesc instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Applicationdesc as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Applicationdesc> findByApplicationId(Object applicationId) {
		return findByProperty(APPLICATION_ID, applicationId);
	}

	public List<Applicationdesc> findByProviderId(Object providerId) {
		return findByProperty(PROVIDER_ID, providerId);
	}

	public List<Applicationdesc> findByMedicineId(Object medicineId) {
		return findByProperty(MEDICINE_ID, medicineId);
	}

	public List<Applicationdesc> findByNowNum(Object nowNum) {
		return findByProperty(NOW_NUM, nowNum);
	}

	public List<Applicationdesc> findByNeedNum(Object needNum) {
		return findByProperty(NEED_NUM, needNum);
	}

	public List<Applicationdesc> findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List<Applicationdesc> findBySpare1(Object spare1) {
		return findByProperty(SPARE1, spare1);
	}

	public List findAll() {
		log.debug("finding all Applicationdesc instances");
		try {
			String queryString = "from Applicationdesc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Applicationdesc merge(Applicationdesc detachedInstance) {
		log.debug("merging Applicationdesc instance");
		try {
			Applicationdesc result = (Applicationdesc) getCurrentSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Applicationdesc instance) {
		log.debug("attaching dirty Applicationdesc instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Applicationdesc instance) {
		log.debug("attaching clean Applicationdesc instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static ApplicationdescDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (ApplicationdescDAO) ctx.getBean("ApplicationdescDAO");
	}
}