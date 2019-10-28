package com.medicine.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medicine.bean.Page;
import com.medicine.dao.MedicineDAO;
import com.medicine.dao.RolesDAO;
import com.medicine.dao.UserDAO;
import com.medicine.entity.Provider;
import com.medicine.entity.Roles;

@Service
public class AdminRoleService {

	@Autowired
	private RolesDAO rolesDAO;
	@Autowired
	private UserDAO userDAO;
	
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public RolesDAO getRolesDAO() {
		return rolesDAO;
	}

	public void setRolesDAO(RolesDAO rolesDAO) {
		this.rolesDAO = rolesDAO;
	}

	/**
	 * 分页获取角色列表 
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<Roles> getRoleList(Integer currentPage, Integer pageSize) {
		//获取供应商总数
		Integer count = this.rolesDAO.getCount();
		//获取总页数
		Integer totalPage = Page.countPage(count, pageSize);
		//offset length
		Integer offset = Page.countOffSet(pageSize, currentPage);
		Integer length = Page.countMySQLLength(pageSize);

		List<Roles> list = this.rolesDAO.findAllList(offset,length);
		Page<Roles> page = new Page<Roles>(count, totalPage, pageSize, currentPage, list);
		return page;
	}

	/**
	 * 根据角色ID获取角色信息 
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Roles getRoleById(Integer id) {
		// TODO Auto-generated method stub
		Roles roles = this.rolesDAO.findById(id);
		return roles;
	}

	/**
	 * 添加角色信息或者修改角色信息 
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean addOrUpdate(Roles role) {
		//根据pid判断添加或者更新
		if("".equals(role.getId()) || role.getId() == null){
			this.rolesDAO.saveOrUpdate(role);
			return true;
		}else{
			Roles roleDo = this.rolesDAO.findById(role.getId());
			roleDo.setRoledesc(role.getRoledesc());
			return true;
		}
	}

	/**
	 * 根据Id删除 
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean delById(Integer id) {
		Integer count = this.userDAO.getCountByRole(id);
		Roles roleDo = this.rolesDAO.findById(id);
		if(count != 0){
			this.rolesDAO.delete(roleDo);
			return true;
		}else{
			return false;
		}
	}

	/**
	 *  按关键字查询角色信息
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page getRoleKwList(Integer id, String rolename, Integer pageNumber,
			Integer pageSize) {
		//获取供应商总数
		Integer count = this.rolesDAO.getCountKw(id,rolename);
		System.out.println("测试符合添加供应商数量"+count);
		//获取总页数
		Integer totalPage = Page.countPage(count, pageSize);
		//offset length
		Integer offset = Page.countOffSet(pageSize, pageNumber);
		Integer length = Page.countMySQLLength(pageSize);

		List<Roles> list = this.rolesDAO.findAllKwList(offset,length,id,rolename);
		Page<Roles> page = new Page<Roles>(count, totalPage, pageSize, pageNumber, list);
		return page;
	}

	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List<Roles> getAllRoleList() {
		// TODO Auto-generated method stub
		List<Roles> roleList = this.rolesDAO.selectAllRoles();
		return roleList;
	}
	
	
}
