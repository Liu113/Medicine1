package com.medicine.controller.user;

import java.util.HashMap;
import java.util.Map;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medicine.bean.Page;
import com.medicine.entity.Admin;
import com.medicine.entity.Medicine;
import com.medicine.entity.Roles;
import com.medicine.service.AdminClasslistService;
import com.medicine.service.AdminMedicineService;
import com.medicine.service.AdminProviderService;

@Controller
@RequestMapping("user/manager/medicine")
public class UserMedicineController {

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
	
	@RequestMapping("/queryList")
	public String List(Model model,HttpServletRequest request,Integer currentPage){
		Admin user = (Admin) request.getSession().getAttribute("admin");
		if(currentPage== null){
			currentPage = 1;
		}
		
		Integer pageSize = 3;
		Page<Medicine> pageInfo = this.adminMedicineService.getMedicineList(currentPage,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		List classList = this.adminClasslistService.getClassList();
		model.addAttribute("classList", classList);
		List providerList = this.adminProviderService.getPList();
		model.addAttribute("providerList", providerList);
		return "user/medicine-list";
	}
	
	/**
	 * 前往更改药品信息页面
	 */
	@RequestMapping("/medicineUpdateVo")
	public String medicineUpdateVo(Model model,Integer id){
		Medicine medicine = this.adminMedicineService.getMedicineById(id);
		List classList = this.adminClasslistService.getClassList();
		model.addAttribute("classList", classList);
		List providerList = this.adminProviderService.getPList();
		model.addAttribute("providerList", providerList);
		model.addAttribute("medicine", medicine);
		return "/admin/medicine-edit";
	}
	
	/**
	 * 修改角色信息
	 */
	@RequestMapping("/medicineUpdate")
	@ResponseBody
	public Map<String, Object> roleUpdate(Medicine medicine){
		//测试
		System.out.println("cehsi"+medicine.getMdes());
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminMedicineService.addOrUpdate(medicine)){
			map.put("mes", 1);
		}else{
			map.put("mes", 0);
		}
		return map;
	}
	
	/**
	 * 删除角色信息
	 */
	@RequestMapping("/delById")
	public @ResponseBody Map<String, Object> delById(Integer mid){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminMedicineService.delById(mid)){
			map.put("mes", 1);
		}
		return map;
	}
	
	/**
	 * 前往角色添加页面
	 */
	@RequestMapping("/medicineAddVo")
	public String medicineAdd(Model model){
		List classList = this.adminClasslistService.getClassList();
		model.addAttribute("classList", classList);
		List providerList = this.adminProviderService.getPList();
		model.addAttribute("providerList", providerList);
		return "/admin/medicine-add";
	}
	
	/**
	 * 修改状态
	 */
	@RequestMapping("/updateMStatus")
	public @ResponseBody  Map<String, Object> updateMStatus(Integer mid){
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminMedicineService.updateMStatus(mid)){
			map.put("mes", 1);
		}
		return map;
	}
	
	/**
	 * 进行添加操作
	 */
	@RequestMapping("/medicineAdd")
	public @ResponseBody Map<String, Object> medicineAdd(Medicine medicine){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminMedicineService.addOrUpdate(medicine)){
			map.put("mes", 1);
		}else{
			map.put("mes", 0);
		}
		return map;
	}
	
	/**
	 * 数据分页跳转
	 */
	@RequestMapping("/queryKwList")
	public String queryKwList(Model model,Integer mid,String medicineName,Integer pageNumber){
		
		Integer pageSize = 3;
		
		Page pageInfo = this.adminMedicineService.getMedicineKwList(mid,medicineName,pageNumber,pageSize);
		model.addAttribute("medicineCode", mid);
		model.addAttribute("medicineName", medicineName);
		model.addAttribute("pageInfo", pageInfo);
		List classList = this.adminClasslistService.getClassList();
		model.addAttribute("classList", classList);
		List providerList = this.adminProviderService.getPList();
		model.addAttribute("providerList", providerList);
		
		return "user/medicine-list";
	}
	
	/**
	 * 分页查询
	 */
	@RequestMapping("/queryKw")
	public String queryKw(Model model,Integer mid,String mname,Integer pageNumber){
		
		Integer pageSize = 3;
		
		Page pageInfo = this.adminMedicineService.getMedicineKwList(mid,mname,pageNumber,pageSize);
		model.addAttribute("medicineCode", mid);
		model.addAttribute("medicineName", mname);
		model.addAttribute("pageInfo", pageInfo);
		return "user/medicine-list";
	}
	
	@RequestMapping("/saleNumVo")
	public String saleNumVo(Model model,Integer mid){
		Medicine medicine = this.adminMedicineService.getMedicineById(mid);
		List classList = this.adminClasslistService.getClassList();
		model.addAttribute("classList", classList);
		
		model.addAttribute("medicine", medicine);
		return "user/medicine-saleNum";
	}
}
