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
import com.medicine.entity.Isdepotheader;

/**
 * A data access object (DAO) providing persistence and search support for
 * Indepotbody entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see com.medicine.entity.Indepotbody
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class IndepotbodyDAO {
	private static final Logger log = LoggerFactory
			.getLogger(IndepotbodyDAO.class);
	// property constants
	public static final String IN_DEPOT_ID = "inDepotId";
	public static final String MEDICINE_ID = "medicineId";
	public static final String PROVIDER_ID = "providerId";
	public static final String IN_PRICE = "inPrice";
	public static final String OUT_PRICE = "outPrice";
	public static final String IN_NUM = "inNum";
	public static final String REMARK = "remark";
	public static final String SPARE = "spare";

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

	public void save(Indepotbody transientInstance) {
		log.debug("saving Indepotbody instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Indepotbody persistentInstance) {
		log.debug("deleting Indepotbody instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Indepotbody findById(java.lang.Integer id) {
		log.debug("getting Indepotbody instance with id: " + id);
		try {
			Indepotbody instance = (Indepotbody) getCurrentSession().get(
					"com.medicine.entity.Indepotbody", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Indepotbody> findByExample(Indepotbody instance) {
		log.debug("finding Indepotbody instance by example");
		try {
			List<Indepotbody> results = (List<Indepotbody>) getCurrentSession()
					.createCriteria("com.medicine.entity.Indepotbody")
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
		log.debug("finding Indepotbody instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Indepotbody as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Indepotbody> findByInDepotId(Object inDepotId) {
		return findByProperty(IN_DEPOT_ID, inDepotId);
	}

	public List<Indepotbody> findByMedicineId(Object medicineId) {
		return findByProperty(MEDICINE_ID, medicineId);
	}

	public List<Indepotbody> findByProviderId(Object providerId) {
		return findByProperty(PROVIDER_ID, providerId);
	}

	public List<Indepotbody> findByInPrice(Object inPrice) {
		return findByProperty(IN_PRICE, inPrice);
	}

	public List<Indepotbody> findByOutPrice(Object outPrice) {
		return findByProperty(OUT_PRICE, outPrice);
	}

	public List<Indepotbody> findByInNum(Object inNum) {
		return findByProperty(IN_NUM, inNum);
	}

	public List<Indepotbody> findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List<Indepotbody> findBySpare(Object spare) {
		return findByProperty(SPARE, spare);
	}

	public List findAll() {
		log.debug("finding all Indepotbody instances");
		try {
			String queryString = "from Indepotbody";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Indepotbody merge(Indepotbody detachedInstance) {
		log.debug("merging Indepotbody instance");
		try {
			Indepotbody result = (Indepotbody) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(Indepotbody instance) {
		log.debug("attaching dirty Indepotbody instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Indepotbody instance) {
		log.debug("attaching clean Indepotbody instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static IndepotbodyDAO getFromApplicationContext(
			ApplicationContext ctx) {
		return (IndepotbodyDAO) ctx.getBean("IndepotbodyDAO");
	}

	/********************************************************/
	public Indepotbody findByDepotId(Integer inDepotId) {
		// TODO Auto-generated method stub
		String hql = "from Indepotbody itb where itb.inDepotId=:inDepotId";
		Query query = getCurrentSession().createQuery(hql).setInteger("inDepotId", inDepotId);
		return (Indepotbody) (query.list().isEmpty()?null:query.list().get(0));
	}
}