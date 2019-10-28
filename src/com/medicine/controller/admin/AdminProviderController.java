package com.medicine.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.medicine.bean.Page;
import com.medicine.entity.Admin;
import com.medicine.entity.Provider;
import com.medicine.service.AdminProviderService;

@Controller
@RequestMapping("/admin/manager/provider")
public class AdminProviderController {
	
	@Autowired
	private AdminProviderService adminProviderService;

	public AdminProviderService getAdminProviderService() {
		return adminProviderService;
	}

	public void setAdminProviderService(AdminProviderService adminProviderService) {
		this.adminProviderService = adminProviderService;
	}
	
	/**
	 * 分页查询所有供应商列表
	 */
	@RequestMapping("/queryList")
	public String List(Model model,HttpServletRequest request,Integer currentPage,Integer is_delete){
		Admin user = (Admin) request.getSession().getAttribute("admin");
		if(currentPage== null){
			currentPage = 1;
		}
		if(is_delete== null){
			is_delete = 0;
		}
		Integer pageSize = 3;
		Page<Provider> pageInfo = this.adminProviderService.getProviderList(currentPage,pageSize,is_delete);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/providerList";
	}
	
	/**
	 * 模糊查询厂商数据列表
	 */
	@RequestMapping("/queryKw")
	public String KwList(Model model,HttpServletRequest request,Integer currentPage,Integer is_delete,Integer pid,String pname){
		System.out.println("测试="+pname);
		Admin user = (Admin) request.getSession().getAttribute("admin");
		if(currentPage== null){
			currentPage = 1;
		}
		/*  */
		Integer pageSize = 3;
		//获取分页数据
		Page<Provider> pageInfo = this.adminProviderService.getKwProviderList(currentPage,pageSize,is_delete,pid,pname);
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("proCode", pid);
		model.addAttribute("proName", pname);
		return "admin/providerList";
	}
	
	/**
	 * 实现分页跳转(包含关键字信息)
	 */
	@RequestMapping("/queryKwList")
	public String KwList(Model model,Provider provider,Integer pageNumber){
		
		//输出测试
		System.out.println("输出测试="+provider.getPname());
		Integer pageSize = 3;
		
		Page pageInfo = this.adminProviderService.getProviderKwList(provider,pageNumber,pageSize);
		model.addAttribute("pid", provider.getPid());
		model.addAttribute("pname", provider.getPname());
		model.addAttribute("pageInfo", pageInfo);
		return "admin/providerList";
	}
	
	/**
	 *	跳转到添加页面
	 */
	@RequestMapping("/providerAddVo")
	public String providerAddVo(){
		return "admin/provider-add";
	}
	
	/**
	 * 添加厂商信息
	 */
	@RequestMapping("/providerAdd")
	public @ResponseBody Map<String,Object> providerAdd(Provider provider){
		//输出测试
		System.out.println("测试厂商添加信息"+provider.getPname());
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminProviderService.addOrUpdate(provider)){
			map.put("mes", 1);
		}else{
			map.put("mes", 0);
		}
		return map;
	}
	
	/**
	 * 改变厂商状态
	 */
	@RequestMapping("/providerChange")
	public @ResponseBody Map<String,Object> providerChange(Integer pid){
		//输出测试
		System.out.println("测试厂商ID显示信息"+pid);
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminProviderService.change(pid)){
			map.put("mes", 0);
		}else{
			map.put("mes", 1);
		}
		return map;
	}
	
	/**
	 * 进入编辑厂商信息页面
	 */
	@RequestMapping("/providerUpdateVo")
	public String providerUpdate(Model model,Integer pid){
		
		Provider provider = this.adminProviderService.getProviderById(pid);
		model.addAttribute("pro", provider);
		return "/admin/provider-edit";
	}
	
	/**
	 * 进行编辑操作
	 */
	@RequestMapping("/providerUpdate")
	public @ResponseBody Map<String, Object> providerUpdate(Provider provider){
		
		Map<String, Object> map = new HashMap<String, Object>();
		
		if(this.adminProviderService.addOrUpdate(provider)){
			map.put("mes", 1);
		}else{
			map.put("mes", 0);
		}
		return map;
	} 
	
	/**
	 * 删除相关厂商信息
	 */
	@RequestMapping("/providerDel")
	public @ResponseBody Map<String, Object> providerDel(Integer pid){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminProviderService.delById(pid)){
			map.put("mes", 0);
		}
		return map;
	}
}
