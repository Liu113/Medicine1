package com.medicine.service;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medicine.dao.UserDAO;
import com.medicine.entity.Admin;
import com.medicine.entity.User;

@Service
public class UserService {

	@Autowired
	private UserDAO userDAO;

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
	
	/**
	 * 店员登录
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public boolean userLogin(User userVo){
		User userDo = this.userDAO.getUserByLoginname(userVo.getLoginname());
		if(userDo == null){
			return false;
		}
		if(!userDo.getPassword().equals(userVo.getPassword())){
			return false;
		}
		if(userDo.getStatus() != 0){
			return false;
		}
		BeanUtils.copyProperties(userDo, userVo);
		return true;
	}
	
	/**
	 * 修改店员个人信息
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean updatePerson(User userVo){
		User userDo = this.userDAO.findById(userVo.getId());
		userDo.setUsername(userVo.getUsername());
		userDo.setEmail(userVo.getEmail());
		userDo.setAddress(userVo.getAddress());
		return true;
	}
	
	/**
	 * 修改密码
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean update(User user, String userPassword,
			String reUserPassword) {
		
		if("".equals(userPassword)){
			return false;
		}else if(userPassword.length()>6 || userPassword.length()<3){
			return false;
		}else{
			User userDo = this.userDAO.findById(user.getId());
			userDo.setPassword(userPassword);
			return true;
		}
	}
}
