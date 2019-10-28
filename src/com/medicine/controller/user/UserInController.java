package com.medicine.controller.user;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medicine.bean.In;
import com.medicine.bean.Page;
import com.medicine.entity.Admin;
import com.medicine.entity.Isdepotheader;
import com.medicine.entity.Medicine;
import com.medicine.entity.User;
import com.medicine.service.AdminClasslistService;
import com.medicine.service.AdminMedicineService;
import com.medicine.service.AdminProviderService;
import com.medicine.service.UserInService;

@Controller
@RequestMapping("user/manager")
public class UserInController {

	@Autowired
	private UserInService userInService;
	@Autowired
	private AdminMedicineService adminMedicineService;
	@Autowired
	private AdminClasslistService adminClasslistService;
	@Autowired
	private AdminProviderService adminProviderService;
	
	public AdminProviderService getAdminProviderService() {
		return adminProviderService;
	}

	public void setAdminProviderService(AdminProviderService adminProviderService) {
		this.adminProviderService = adminProviderService;
	}

	public AdminClasslistService getAdminClasslistService() {
		return adminClasslistService;
	}

	public void setAdminClasslistService(AdminClasslistService adminClasslistService) {
		this.adminClasslistService = adminClasslistService;
	}

	public AdminMedicineService getAdminMedicineService() {
		return adminMedicineService;
	}

	public void setAdminMedicineService(AdminMedicineService adminMedicineService) {
		this.adminMedicineService = adminMedicineService;
	}

	public UserInService getUserInService() {
		return userInService;
	}

	public void setUserInService(UserInService userInService) {
		this.userInService = userInService;
	}
	
	@RequestMapping("/medicine/addNumVo")
	public String addNumVo(Model model,Integer mid){
		Medicine medicine = this.adminMedicineService.getMedicineById(mid);
		List classList = this.adminClasslistService.getClassList();
		model.addAttribute("classList", classList);
		
		model.addAttribute("medicine", medicine);
		return "user/medicine-addNum";
	}
	
	
	@RequestMapping("user/addNum")
	public @ResponseBody Map<String, Object> addNum(HttpServletRequest request,Medicine medicine,Integer addNum ){
		System.out.println("ceshi"+addNum);
		User user = (User) request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.userInService.addNum(medicine.getMid(),addNum,user)){
			map.put("mes", 1);
		}
		return map;
	}
	
	/**
	 * 查询入库单信息
	 */
	@RequestMapping("/user/in/queryList")
	public String List(Model model,HttpServletRequest request,Integer currentPage){
		User user = (User) request.getSession().getAttribute("user");
		if(currentPage== null){
			currentPage = 1;
		}
		Integer pageSize = 3;
		Page<In> pageInfo = this.userInService.getInList(currentPage,pageSize,user);
		
		model.addAttribute("pageInfo", pageInfo);
		List classList = this.adminClasslistService.getClassList();
		model.addAttribute("classList", classList);
		List providerList = this.adminProviderService.getPList();
		model.addAttribute("providerList", providerList);
		return "user/in-list";
	}
	
	/**
	 * 数据分页跳转
	 */
	@RequestMapping("/in/queryKwList")
	public String queryKwList(HttpServletRequest request,Model model,Integer inDepotId,Integer pageNumber){
		User user = (User) request.getSession().getAttribute("user");
		Integer pageSize = 3;
		
		Page pageInfo = this.userInService.getInKwList(inDepotId,pageNumber,pageSize,user);
		model.addAttribute("inDepotId", inDepotId);
		model.addAttribute("pageInfo", pageInfo);
		List classList = this.adminClasslistService.getClassList();
		model.addAttribute("classList", classList);
		List providerList = this.adminProviderService.getPList();
		model.addAttribute("providerList", providerList);
		
		return "admin/medicine-list";
	}
}
