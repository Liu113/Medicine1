package com.medicine.controller.admin;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medicine.bean.Page;
import com.medicine.entity.Admin;
import com.medicine.entity.Provider;
import com.medicine.entity.Roles;
import com.medicine.service.AdminRoleService;

@Controller
@RequestMapping("admin/manager/role")
public class AdminRoleController {
	
	@Autowired
	private AdminRoleService adminRoleService;

	public AdminRoleService getAdminRoleService() {
		return adminRoleService;
	}

	public void setAdminRoleService(AdminRoleService adminRoleService) {
		this.adminRoleService = adminRoleService;
	}
	

	/**
	 * 分页查询所有角色列表
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
		Page<Roles> pageInfo = this.adminRoleService.getRoleList(currentPage,pageSize);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/role-list";
	}
	
	/**
	 * 前往更改角色信息页面
	 */
	@RequestMapping("/roleUpdateVo")
	public String roleUpdateVo(Model model,Integer id){
		Roles roles = this.adminRoleService.getRoleById(id);
		model.addAttribute("role", roles);
		return "/admin/role-edit";
	}
	
	/**
	 * 修改角色信息
	 */
	@RequestMapping("/roleUpdate")
	@ResponseBody
	public Map<String, Object> roleUpdate(Roles role){
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminRoleService.addOrUpdate(role)){
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
	public @ResponseBody Map<String, Object> delById(Integer id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminRoleService.delById(id)){
			map.put("mes", 0);
		}
		return map;
	}
	
	/**
	 * 前往角色添加页面
	 */
	@RequestMapping("/roleAddVo")
	public String roleAdd(){
		
		return "/admin/role-add";
	}
	
	/**
	 * 进行添加操作
	 */
	@RequestMapping("/roleAdd")
	public @ResponseBody Map<String, Object> roleAdd(Roles role){
		//输出测试
		System.out.println("输出测试="+role.getRolename()+"/"+role.getRoledesc());
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminRoleService.addOrUpdate(role)){
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
	public String queryKwList(Model model,Integer id,String rolename,Integer pageNumber){
		
		Integer pageSize = 3;
		
		Page pageInfo = this.adminRoleService.getRoleKwList(id,rolename,pageNumber,pageSize);
		model.addAttribute("pid", id);
		model.addAttribute("pname", rolename);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/role-list";
	}
	
	/**
	 * 分页查询
	 */
	@RequestMapping("/queryKw")
	public String queryKw(Model model,Integer id,String rolename,Integer pageNumber){
		
		Integer pageSize = 3;
		
		Page pageInfo = this.adminRoleService.getRoleKwList(id,rolename,pageNumber,pageSize);
		model.addAttribute("roleCode", id);
		model.addAttribute("roleName", rolename);
		model.addAttribute("pageInfo", pageInfo);
		return "admin/role-list";
	}
}
