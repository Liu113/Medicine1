package com.medicine.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medicine.bean.ReturnMessage;
import com.medicine.dao.AdminDAO;
import com.medicine.entity.Admin;

@Service
public class AdminService {

	@Autowired
	private AdminDAO adminDAO;

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}
	
	/**
	 * 管理员登录
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean adminLogin(Admin adminVo){
		Admin adminDo = this.adminDAO.getAdminByLoginname(adminVo.getLoginname());
		if(adminDo == null){
			return false;
		}
		if(!adminDo.getPassword().equals(adminVo.getPassword())){
			return false;
		}
		BeanUtils.copyProperties(adminDo, adminVo);
		return true;
	}
	
	/**
	 * 修改管理员个人信息
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean updatePerson(Admin adminVo){
		Admin adminDo = this.adminDAO.findById(adminVo.getId());
		System.out.println("cehsi="+adminVo.getAddress());
		adminDo.setUsername(adminVo.getUsername());
		adminDo.setEmail(adminVo.getEmail());
		adminDo.setAddress(adminVo.getAddress());
		adminDo.setTel(adminVo.getTel());
		return true;
	}
}
