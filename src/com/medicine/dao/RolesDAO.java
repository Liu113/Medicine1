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

import com.medicine.entity.Roles;

/**
 * A data access object (DAO) providing persistence and search support for Roles
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.medicine.entity.Roles
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class RolesDAO {
	private static final Logger log = LoggerFactory.getLogger(RolesDAO.class);
	// property constants
	public static final String ROLENAME = "rolename";
	public static final String ROLEDESC = "roledesc";
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

	public void save(Roles transientInstance) {
		log.debug("saving Roles instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Roles persistentInstance) {
		log.debug("deleting Roles instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Roles findById(java.lang.Integer id) {
		log.debug("getting Roles instance with id: " + id);
		try {
			Roles instance = (Roles) getCurrentSession().get(
					"com.medicine.entity.Roles", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<Roles> findByExample(Roles instance) {
		log.debug("finding Roles instance by example");
		try {
			List<Roles> results = (List<Roles>) getCurrentSession()
					.createCriteria("com.medicine.entity.Roles")
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
		log.debug("finding Roles instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Roles as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<Roles> findByRolename(Object rolename) {
		return findByProperty(ROLENAME, rolename);
	}

	public List<Roles> findByRoledesc(Object roledesc) {
		return findByProperty(ROLEDESC, roledesc);
	}

	public List<Roles> findByRemark(Object remark) {
		return findByProperty(REMARK, remark);
	}

	public List<Roles> findBySpare1(Object spare1) {
		return findByProperty(SPARE1, spare1);
	}

	public List<Roles> findBySpare2(Object spare2) {
		return findByProperty(SPARE2, spare2);
	}

	public List findAll() {
		log.debug("finding all Roles instances");
		try {
			String queryString = "from Roles";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Roles merge(Roles detachedInstance) {
		log.debug("merging Roles instance");
		try {
			Roles result = (Roles) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(Roles instance) {
		log.debug("attaching dirty Roles instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Roles instance) {
		log.debug("attaching clean Roles instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static RolesDAO getFromApplicationContext(ApplicationContext ctx) {
		return (RolesDAO) ctx.getBean("RolesDAO");
	}

	
	/*********************************************************************/
	
	/**
	 * 获取角色数量
	 */
	public Integer getCount() {
		String hql = "select count(*) from Roles r ";
		Query query = getCurrentSession().createQuery(hql);
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}

	/**
	 * 查询角色列表 
	 */
	public List<Roles> findAllList(Integer offset, Integer length) {
		String hql = "from Roles r ";
		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(length);
		return query.list();
	}

	/**
	 * 根据关键词统计符合要求的信息数
	 */
	public Integer getCountKw(Integer id, String rolename) {
		StringBuilder hql = new StringBuilder("select count(*) from Roles r where 1=1 ");
		
		//判断pid是否为空
		if(!"".equals(id) && id != null){
			hql.append(" and r.id =:id");
		}
		if(!"".equals(rolename) && rolename != null){
			hql.append(" and r.rolename like :rolename");
		}
		Query query = getCurrentSession().createQuery(hql.toString());
		
		//判断pid是否为空
		if(!"".equals(id) && id != null){
			query.setInteger("id", id);
		}
		if(!"".equals(rolename) && rolename != null){
			query.setString("rolename", "%"+rolename+"%");
		}
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}

	/**
	 * 根据关键字获取所有数据的信息 
	 */
	public List<Roles> findAllKwList(Integer offset, Integer length,
			Integer id, String rolename) {
		StringBuilder hql = new StringBuilder("from Roles r where 1=1");
		
		//判断id是否为空
		if(!"".equals(id) && id != null){
			hql.append(" and r.id =:id");
		}
		if(!"".equals(rolename) && rolename != null){
			hql.append(" and r.rolename like :rolename");
		}
		Query query = getCurrentSession().createQuery(hql.toString());
		
		//判断id是否为空
		if(!"".equals(id) && id != null){
			query.setInteger("id", id);
		}
		if(!"".equals(rolename) && rolename != null){
			query.setString("rolename", "%"+rolename+"%");
		}
		query.setFirstResult(offset);
		query.setMaxResults(length);
		return query.list();
	}

	/**
	 * 查询所有角色信息 
	 */
	public List<Roles> selectAllRoles() {
		// TODO Auto-generated method stub
		String hql = "from Roles r ";
		Query query = getCurrentSession().createQuery(hql);
		return query.list();
	}
	
	
}