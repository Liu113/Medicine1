package com.medicine.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medicine.dao.AdminDAO;
import com.medicine.entity.Admin;

@Service
public class AdminPasswordService {

	@Autowired
	private AdminDAO adminDAO;

	public AdminDAO getAdminDAO() {
		return adminDAO;
	}

	public void setAdminDAO(AdminDAO adminDAO) {
		this.adminDAO = adminDAO;
	}

	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean update(Admin admin, String userPassword,
			String reUserPassword) {
		
		if("".equals(userPassword)){
			return false;
		}else{
			Admin adminDo = this.adminDAO.findById(admin.getId());
			adminDo.setPassword(userPassword);
			return true;
		}
	}
	
}
