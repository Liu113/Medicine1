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

import com.medicine.entity.Indepotbody;
import com.medicine.entity.Saledesc;

/**
 * A data access object (DAO) providing persistence and search support for
 * Saledesc entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.medicine.entity.Saledesc
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class SaledescDAO {
	private static final Logger log = LoggerFactory
			.getLogger(SaledescDAO.class);
	// property constants
	public static final String SALE_ID = "saleId";
	public static final String PROVIDER_ID = "providerId";
	public static final String MEDICINE_ID = "medicineId";
	public static final String AMOUT = "amout";

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

	public void save(Saledesc transientInstance) {
		log.debug("saving Saledesc instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Saledesc persistentInstance) {
		log.debug("deleting Saledesc instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Saledesc findById(java.lang.Integer id) {
		log.debug("getting Saledesc instance with id: " + id);
		try {
			Saledesc instance = (Saledesc) getCurrentSession().get(
					"com.medicine.entity.Saledesc", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Saledesc> findByExample(Saledesc instance) {
		log.debug("finding Saledesc instance by example");
		try {
			List<Saledesc> results = (List<Saledesc>) getCurrentSession()
					.createCriteria("com.medicine.entity.Saledesc")
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
		log.debug("finding Saledesc instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Saledesc as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Saledesc> findBySaleId(Object saleId) {
		return findByProperty(SALE_ID, saleId);
	}

	public List<Saledesc> findByProviderId(Object providerId) {
		return findByProperty(PROVIDER_ID, providerId);
	}

	public List<Saledesc> findByMedicineId(Object medicineId) {
		return findByProperty(MEDICINE_ID, medicineId);
	}

	public List<Saledesc> findByAmout(Object amout) {
		return findByProperty(AMOUT, amout);
	}

	public List findAll() {
		log.debug("finding all Saledesc instances");
		try {
			String queryString = "from Saledesc";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Saledesc merge(Saledesc detachedInstance) {
		log.debug("merging Saledesc instance");
		try {
			Saledesc result = (Saledesc) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Saledesc instance) {
		log.debug("attaching dirty Saledesc instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Saledesc instance) {
		log.debug("attaching clean Saledesc instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static SaledescDAO getFromApplicationContext(ApplicationContext ctx) {
		return (SaledescDAO) ctx.getBean("SaledescDAO");
	}

	/************************************************/
	
	public Saledesc findBySId(Integer id) {
		// TODO Auto-generated method stub
		String hql = "from Saledesc sd where sd.saleId=:id";
		Query query = getCurrentSession().createQuery(hql).setInteger("id", id);
		return (Saledesc) (query.list().isEmpty()?null:query.list().get(0));
	}
}