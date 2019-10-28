package com.medicine.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.medicine.bean.Page;
import com.medicine.entity.Admin;
import com.medicine.entity.Roles;
import com.medicine.entity.User;
import com.medicine.service.AdminRoleService;
import com.medicine.service.AdminUserService;

@Controller
@RequestMapping("admin/manager/user")
public class AdminUserController {
	
	@Autowired
	private AdminUserService adminUserService;
	@Autowired
	private AdminRoleService adminRoleService;
	
	public AdminRoleService getAdminRoleService() {
		return adminRoleService;
	}

	public void setAdminRoleService(AdminRoleService adminRoleService) {
		this.adminRoleService = adminRoleService;
	}

	public AdminUserService getAdminUserService() {
		return adminUserService;
	}

	public void setAdminUserService(AdminUserService adminUserService) {
		this.adminUserService = adminUserService;
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
		
		Integer pageSize = 3;
		Page<User> pageInfo = this.adminUserService.getUserList(currentPage,pageSize);
		List<Roles> roleList = this.adminRoleService.getAllRoleList();
		model.addAttribute("pageInfo", pageInfo);
		model.addAttribute("roleList",roleList);
		return "admin/user-list";
	}
	
	/**
	 * 前往更改角色信息页面
	 */
	@RequestMapping("/userUpdateVo")
	public String roleUpdateVo(Model model,Integer id){
		User user = this.adminUserService.getUserById(id);
		List<Roles> roleList = this.adminRoleService.getAllRoleList();
		model.addAttribute("user", user);
		model.addAttribute("roleList", roleList);
		return "/admin/user-edit";
	}
	
	/**
	 * 修改角色信息
	 */
	@RequestMapping("/userUpdate")
	@ResponseBody
	public Map<String, Object> roleUpdate(User user){
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminUserService.addOrUpdate(user)){
			map.put("mes", 1);
		}else{
			map.put("mes", 0);
		}
		return map;
	}
	
	/**
	 * 删除用户信息
	 */
	@RequestMapping("/delById")
	public @ResponseBody Map<String, Object> delById(Integer id){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminUserService.delById(id)){
			map.put("mes", 1);
		}else{
			map.put("mes", 0);
		}
		return map;
	}
	
	/**
	 * 前往用户添加页面
	 */
	@RequestMapping("/userAddVo")
	public String userAdd(Model model){
		List<Roles> roleList = this.adminRoleService.getAllRoleList();
		model.addAttribute("roleList", roleList);
		return "/admin/user-add";
	}
	
	/**
	 * 进行添加操作
	 */
	@RequestMapping("/userAdd")
	public @ResponseBody Map<String, Object> roleAdd(User user){
		
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminUserService.addOrUpdate(user)){
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
	public String queryKwList(Model model,Integer roleId,String username,Integer pageNumber){
		
		Integer pageSize = 3;
		
		Page pageInfo = this.adminUserService.getUserKwList(roleId,username,pageNumber,pageSize);
		model.addAttribute("roleId", roleId);
		model.addAttribute("username", username);
		model.addAttribute("pageInfo", pageInfo);
		
		List<Roles> roleList = this.adminRoleService.getAllRoleList();
		model.addAttribute("roleList", roleList);
		
		return "admin/user-list";
	}
	
	/**
	 * 分页查询
	 */
	@RequestMapping("/queryKw")
	public String queryKw(Model model,Integer roleId,String username,Integer pageNumber){
		
		Integer pageSize = 3;
		
		Page pageInfo = this.adminUserService.getUserKwList(roleId,username,pageNumber,pageSize);
		model.addAttribute("roleId", roleId);
		model.addAttribute("username", username);
		model.addAttribute("pageInfo", pageInfo);
		
		List<Roles> roleList = this.adminRoleService.getAllRoleList();
		model.addAttribute("roleList", roleList);
		
		return "admin/user-list";
	}
}
