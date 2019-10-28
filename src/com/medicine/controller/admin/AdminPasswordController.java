package com.medicine.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medicine.entity.Admin;
import com.medicine.service.AdminPasswordService;

@Controller
@RequestMapping("/admin/manager/admin/pwd")
public class AdminPasswordController {

	@Autowired
	private AdminPasswordService adminPasswordService;

	public AdminPasswordService getAdminPasswordService() {
		return adminPasswordService;
	}

	public void setAdminPasswordService(AdminPasswordService adminPasswordService) {
		this.adminPasswordService = adminPasswordService;
	}
	
	
	@RequestMapping("/updateVo")
	public String updateVo(){
		return "/admin/pwd";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> updatePwd(HttpServletRequest request,String userPassword,String reUserPassword){
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminPasswordService.update(admin,userPassword,reUserPassword)){
			map.put("mes", 1);
		}else{
			map.put("mes", 0);
		}
		return map;
	}
	
}
