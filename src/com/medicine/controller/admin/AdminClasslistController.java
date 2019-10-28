package com.medicine.controller.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

import com.medicine.service.AdminClasslistService;

@Controller
public class AdminClasslistController {

	@Autowired
	private AdminClasslistService adminClasslistService;

	public AdminClasslistService getAdminClasslistService() {
		return adminClasslistService;
	}

	public void setAdminClasslistService(AdminClasslistService adminClasslistService) {
		this.adminClasslistService = adminClasslistService;
	}
	
	/**
	 * 查询所有分类列表
	 */
}
