package com.medicine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medicine.bean.Page;
import com.medicine.dao.MedicineDAO;
import com.medicine.dao.UserDAO;
import com.medicine.entity.Provider;
import com.medicine.entity.Roles;
import com.medicine.entity.User;

@Service
public class AdminUserService {

	@Autowired
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	/**
	 * 查询用户列表
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<User> getUserList(Integer currentPage,Integer pageSize){
		//获取用户总数
		Integer count = this.userDAO.getCount();
		//获取总页数
		Integer totalPage = Page.countPage(count, pageSize);
		//offset length
		Integer offset = Page.countOffSet(pageSize, currentPage);
		Integer length = Page.countMySQLLength(pageSize);

		List<User> list = this.userDAO.findAllList(offset,length);
		Page<User> page = new Page<User>(count, totalPage, pageSize, currentPage, list);
		return page;
	}
	
	/**
	 * 根据角色ID获取角色信息 
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public User getUserById(Integer id) {
		// TODO Auto-generated method stub
		User user = this.userDAO.findById(id);
		return user;
	}

	/**
	 * 添加角色信息或者修改角色信息 
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean addOrUpdate(User user) {
		//根据pid判断添加或者更新
		if("".equals(user.getId()) || user.getId() == null){
			Integer count = this.userDAO.getCountByLogin(user.getLoginname());
			if(count == 0){
				user.setStatus(0);
				this.userDAO.saveOrUpdate(user);
				return true;
			}else{
				return false;
			}
			
		}else{
			User userDo = this.userDAO.findById(user.getId());
			userDo.setRole(user.getRole());
			userDo.setSalary(user.getSalary());
			userDo.setSalaryDesc(user.getSalaryDesc());
			return true;
		}
	}

	/**
	 * 根据Id删除 
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean delById(Integer id) {
		User userDo = this.userDAO.findById(id);
		userDo.setStatus(1);
		
		return true;
	}

	/**
	 *  按关键字查询角色信息
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page getUserKwList(Integer roleId, String username, Integer pageNumber,
			Integer pageSize) {
		//获取符合条件的用户总数
		Integer count = this.userDAO.getCountKw(roleId,username);
		System.out.println("测试符合添加供应商数量"+count);
		//获取总页数
		Integer totalPage = Page.countPage(count, pageSize);
		//offset length
		Integer offset = Page.countOffSet(pageSize, pageNumber);
		Integer length = Page.countMySQLLength(pageSize);

		List<User> list = this.userDAO.findAllKwList(offset,length,roleId,username);
		Page<User> page = new Page<User>(count, totalPage, pageSize, pageNumber, list);
		return page;
	}
}
