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

import com.medicine.entity.Admin;
import com.medicine.entity.Provider;
import com.medicine.entity.User;

/**
 * A data access object (DAO) providing persistence and search support for User
 * entities. Transaction control of the save(), update() and delete() operations
 * can directly support Spring container-managed transactions or they can be
 * augmented to handle user-managed Spring transactions. Each of these methods
 * provides additional information for how to configure it for the desired type
 * of transaction control.
 * 
 * @see com.medicine.entity.User
 * @author MyEclipse Persistence Tools
 */
@Transactional
public class UserDAO {
	private static final Logger log = LoggerFactory.getLogger(UserDAO.class);
	// property constants
	public static final String USERNAME = "username";
	public static final String LOGINNAME = "loginname";
	public static final String PASSWORD = "password";
	public static final String EMAIL = "email";
	public static final String ADDRESS = "address";
	public static final String ROLE = "role";
	public static final String SALARY = "salary";
	public static final String SALARY_DESC = "salaryDesc";
	public static final String STATUS = "status";
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

	public void save(User transientInstance) {
		log.debug("saving User instance");
		try {
			getCurrentSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(User persistentInstance) {
		log.debug("deleting User instance");
		try {
			getCurrentSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public User findById(java.lang.Integer id) {
		log.debug("getting User instance with id: " + id);
		try {
			User instance = (User) getCurrentSession().get(
					"com.medicine.entity.User", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List<User> findByExample(User instance) {
		log.debug("finding User instance by example");
		try {
			List<User> results = (List<User>) getCurrentSession()
					.createCriteria("com.medicine.entity.User")
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
		log.debug("finding User instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from User as model where model."
					+ propertyName + "= ?";
			Query queryObject = getCurrentSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List<User> findByUsername(Object username) {
		return findByProperty(USERNAME, username);
	}

	public List<User> findByLoginname(Object loginname) {
		return findByProperty(LOGINNAME, loginname);
	}

	public List<User> findByPassword(Object password) {
		return findByProperty(PASSWORD, password);
	}

	public List<User> findByEmail(Object email) {
		return findByProperty(EMAIL, email);
	}

	public List<User> findByAddress(Object address) {
		return findByProperty(ADDRESS, address);
	}

	public List<User> findByRole(Object role) {
		return findByProperty(ROLE, role);
	}

	public List<User> findBySalary(Object salary) {
		return findByProperty(SALARY, salary);
	}

	public List<User> findBySalaryDesc(Object salaryDesc) {
		return findByProperty(SALARY_DESC, salaryDesc);
	}

	public List<User> findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List<User> findBySpare1(Object spare1) {
		return findByProperty(SPARE1, spare1);
	}

	public List<User> findBySpare2(Object spare2) {
		return findByProperty(SPARE2, spare2);
	}

	public List findAll() {
		log.debug("finding all User instances");
		try {
			String queryString = "from User";
			Query queryObject = getCurrentSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public User merge(User detachedInstance) {
		log.debug("merging User instance");
		try {
			User result = (User) getCurrentSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void saveOrUpdate(User instance) {
		log.debug("attaching dirty User instance");
		try {
			getCurrentSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(User instance) {
		log.debug("attaching clean User instance");
		try {
			getCurrentSession().buildLockRequest(LockOptions.NONE).lock(
					instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public static UserDAO getFromApplicationContext(ApplicationContext ctx) {
		return (UserDAO) ctx.getBean("UserDAO");
	}
	
	/************************************************/
	
	
	/**
	 * 统计用户的数量
	 * @return
	 */
	public Integer getCount() {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User u  ";
		Query query = getCurrentSession().createQuery(hql);
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}
	
	/**
	 * 查询所有用户列表
	 */
	public List<User> findAllList(Integer offset,Integer length) {
		// TODO Auto-generated method stub
		String hql = "from User u ";
		Query query = getCurrentSession().createQuery(hql);
		query.setFirstResult(offset);
		query.setMaxResults(length);
		return query.list();
	}

	/**
	 * 根据角色ID查询该角色下的用户数量 
	 */
	public Integer getCountByRole(Integer id) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User u where u.role =:id ";
		Query query = getCurrentSession().createQuery(hql).setInteger("id", id);
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}

	/**
	 * 根据搜索信息查询符合条的信息总数
	 */
	public Integer getCountKw(Integer roleId, String username) {
		// TODO Auto-generated method stub
		StringBuilder hql = new StringBuilder("select count(*) from User u where 1=1");
		//判断pid是否为空
		if(!"".equals(roleId) && roleId != null){
			hql.append(" and u.role =:roleId");
		}
		if(!"".equals(username) && username != null){
			hql.append(" and u.username like :username");
		}
		Query query = getCurrentSession().createQuery(hql.toString());
		//判断pid是否为空
		if(!"".equals(roleId) && roleId != null){
			query.setInteger("roleId", roleId);
		}
		if(!"".equals(username) && username != null){
			query.setString("username", "%"+username+"%");
		}
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}

	/**
	 * 按条件查询符合条件的用户信息 
	 * @return
	 */
	public List<User> findAllKwList(Integer offset, Integer length,
			Integer roleId, String username) {
        StringBuilder hql = new StringBuilder("from User u where 1=1");
		
		//判断id是否为空
		if(!"".equals(roleId) && roleId != null){
			hql.append(" and u.role =:roleId");
		}
		if(!"".equals(username) && username != null){
			hql.append(" and u.username like :username");
		}
		Query query = getCurrentSession().createQuery(hql.toString());
		
		//判断id是否为空
		if(!"".equals(roleId) && roleId != null){
			query.setInteger("roleId", roleId);
		}
		if(!"".equals(username) && username != null){
			query.setString("username", "%"+username+"%");
		}
		query.setFirstResult(offset);
		query.setMaxResults(length);
		return query.list();
	}

	/**
	 * 统计在添加信息时是否有重复信息
	 */
	public Integer getCountByLogin(String loginname) {
		// TODO Auto-generated method stub
		String hql = "select count(*) from User u where  u.loginname=:loginname and u.status=:status";
		Query query = getCurrentSession().createQuery(hql).setString("loginname", loginname).setInteger("status", 0);
		long count = (Long) query.uniqueResult();//返回唯一值
		return Integer.parseInt(count+"");
	}
	
	
	public User getUserByLoginname(String loginname) {
		// TODO Auto-generated method stub
		String hql = "from User u where u.loginname=:loginname";
		Query query = getCurrentSession().createQuery(hql).setString("loginname", loginname);
		return (User) (query.list().isEmpty()?null:query.list().get(0));
	}
}