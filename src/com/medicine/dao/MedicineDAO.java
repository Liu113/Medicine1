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

import com.medicine.entity.Medicine;
import com.medicine.entity.Provider;
import com.medicine.entity.Roles;

/**
 * A data access object (DAO) providing persistence and search support for
 * Medicine entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see com.medicine.entity.Medicine
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class MedicineDAO {
	private static final Logger log = LoggerFactory
			.getLogger(MedicineDAO.class);
	// property constants
	public static final String CLASS_ = "class_";
	public static final String MNAME = "mname";
	public static final String MDES = "mdes";
	public static final String MIMG = "mimg";
	public static final String IN_PRICE = "inPrice";
	public static final String OUT_PRICE = "outPrice";
	public static final String MSTATUS = "mstatus";
	public static final String MNUM = "mnum";
	public static final String IS_DELETE = "isDelete";
	public static final String PID = "pid";

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

	public void save(Medicine transientInstance) {
		log.debug("saving Medicine instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Medicine persistentInstance) {
		log.debug("deleting Medicine instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Medicine findById(java.lang.Integer id) {
		log.debug("getting Medicine instance with id: " + id);
		try {
			Medicine instance = (Medicine) getCurrentSession().get(
					"com.medicine.entity.Medicine", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Medicine> findByExample(Medicine instance) {
		log.debug("finding Medicine instance by example");
		try {
			List<Medicine> results = (List<Medicine>) getCurrentSession()
					.createCriteria("com.medicine.entity.Medicine")
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
		log.debug("finding Medicine instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Medicine as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Medicine> findByClass_(Object class_) {
		return findByProperty(CLASS_, class_);
	}

	public List<Medicine> findByMname(Object mname) {
		return findByProperty(MNAME, mname);
	}

	public List<Medicine> findByMdes(Object mdes) {
		return findByProperty(MDES, mdes);
	}

	public List<Medicine> findByMimg(Object mimg) {
		return findByProperty(MIMG, mimg);
	}

	public List<Medicine> findByInPrice(Object inPrice) {
		return findByProperty(IN_PRICE, inPrice);
	}

	public List<Medicine> findByOutPrice(Object outPrice) {
		return findByProperty(OUT_PRICE, outPrice);
	}

	public List<Medicine> findByMstatus(Object mstatus) {
		return findByProperty(MSTATUS, mstatus);
	}

	public List<Medicine> findByMnum(Object mnum) {
		return findByProperty(MNUM, mnum);
	}

	public List<Medicine> findByIsDelete(Object isDelete) {
		return findByProperty(IS_DELETE, isDelete);
	}

	public List<Medicine> findByPid(Object pid) {
		return findByProperty(PID, pid);
	}

	public List findAll() {
		log.debug("finding all Medicine instances");
		try {
			String queryString = "from Medicine";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Medicine merge(Medicine detachedInstance) {
		log.debug("merging Medicine instance");
		try {
			Medicine result = (Medicine) getCurrentSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(Medicine instance) {
		log.debug("attaching dirty Medicine instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Medicine instance) {
		log.debug("attaching clean Medicine instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static MedicineDAO getFromApplicationContext(ApplicationContext ctx) {
		return (MedicineDAO) ctx.getBean("MedicineDAO");
	}
	
	
	/*************************************/
	
	/**
	 * 统计药品的数量
	 * @return
	 */
	public Integer getCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Medicine m ";
		Query query = getCurrentSession().createQuery(hql);
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}
	
	/**
	 * 查询所有药品列表
	 */
	public List<Medicine> findAllList(Integer offset,Integer length) {
		// TODO Auto-generated method stub
		String hql = "from Medicine m ";
		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(length);
		return query.list();
	}
	
	/*********************************************************************/
	
	/**
	 * 统计药品的数量
	 * @return
	 */
	public Integer getCountByPid(Integer pid) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from Medicine m where m.pid =:pid";
		Query query = getCurrentSession().createQuery(hql).setInteger("pid", pid);
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}
	
	/**
	 * 根据关键词统计符合要求的信息数
	 */
	public Integer getCountKw(Integer mid, String medicineName) {
		StringBuilder hql = new StringBuilder("select count(*) from Medicine m where 1=1 ");
		
		//判断mid是否为空
		if(!"".equals(mid) && mid != null){
			hql.append(" and m.mid =:mid");
		}
		if(!"".equals(medicineName) && medicineName != null){
			hql.append(" and m.mname like :medicineName");
		}
		Query query = getCurrentSession().createQuery(hql.toString());
		
		//判断mid是否为空
		if(!"".equals(mid) && mid != null){
			query.setInteger("mid", mid);
		}
		if(!"".equals(medicineName) && medicineName != null){
			query.setString("medicineName", "%"+medicineName+"%");
		}
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}

	/**
	 * 根据关键字获取所有数据的信息 
	 */
	public List<Medicine> findAllKwList(Integer offset, Integer length,
			Integer mid, String medicineName) {
		StringBuilder hql = new StringBuilder("from Medicine m where 1=1");
		
		//判断id是否为空
		if(!"".equals(mid) && mid != null){
			hql.append(" and m.mid =:mid");
		}
		if(!"".equals(medicineName) && medicineName != null){
			hql.append(" and m.mname like :medicineName");
		}
		Query query = getCurrentSession().createQuery(hql.toString());
		
		//判断id是否为空
		if(!"".equals(mid) && mid != null){
			query.setInteger("mid", mid);
		}
		if(!"".equals(medicineName) && medicineName != null){
			query.setString("medicineName", "%"+medicineName+"%");
		}
		query.setFirstResult(offset);
		query.setMaxResults(length);
		return query.list();
	}
}