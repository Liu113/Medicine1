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

import com.medicine.bean.ReturnMessage;
import com.medicine.entity.Admin;
import com.medicine.entity.Roles;
import com.medicine.entity.User;
import com.medicine.service.AdminRoleService;
import com.medicine.service.UserService;

@Controller
@RequestMapping("/user/manager")
public class UserController {

	@Autowired
	private UserService userService;
	@Autowired
	private AdminRoleService adminRoleService;
	
	public AdminRoleService getAdminRoleService() {
		return adminRoleService;
	}

	public void setAdminRoleService(AdminRoleService adminRoleService) {
		this.adminRoleService = adminRoleService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	/**
	 * 登陆操作
	 * @return
	 */
	@RequestMapping("/login")
	public @ResponseBody ReturnMessage login(HttpServletRequest request,User user){
		
		//判读登陆是否完成
		if(userService.userLogin(user)){
			request.getSession().setAttribute("user", user);
			return new ReturnMessage<Admin>(1, "登陆成功");
		}else{
			return new ReturnMessage<Admin>(0, "登陆失败");
		}
	}
	
	/**
	 * 登陆成功进入管理员首页
	 */
	@RequestMapping("/index")
	public String index(){
		return "user/index";
	}
	
	/**
	 * 进入欢迎页面
	 * @return
	 */
	@RequestMapping("/welcome")
	public String welcome(){
		return "user/welcome";
	}
	
	/**
	 * 退出登录
	 */
	@RequestMapping("/logout")
	public @ResponseBody Map<String, Object> logout(HttpServletRequest request){
		request.getSession().removeAttribute("user");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("mes", 1);
		return map;
	}
	
	/**
	 * 查看个人信息  
	 */
	@RequestMapping("/user/person/queryVo")
	public String personQueryVo(HttpServletRequest request,Model model){
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		List<Roles> roleList = this.adminRoleService.getAllRoleList();
		model.addAttribute("roleList", roleList);
		return "user/personVo";
	}
	
	/**
	 * 进入修改个人信息  
	 */
	@RequestMapping("/user/person/updateVo")
	public String personUpdateVo(HttpServletRequest request,Model model){
		User user = (User) request.getSession().getAttribute("user");
		model.addAttribute("user", user);
		List<Roles> roleList = this.adminRoleService.getAllRoleList();
		model.addAttribute("roleList", roleList);
		return "user/personVo-edit";
	}
	
	/**
	 * 修改个人信息
	 */
	@RequestMapping("user/person/update")
	public @ResponseBody Map<String, Object> personUpdateVo(HttpServletRequest request,User user){
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.userService.updatePerson(user)){
			request.getSession().setAttribute("user", user);
			map.put("code", 1);
		}
		return map;
	}
	
	/**
	 * 修改密码
	 */
	@RequestMapping("/user/pwd/updateVo")
	public String updateVo(){
		return "/user/pwd";
	}
	
	@RequestMapping("/update")
	public @ResponseBody Map<String, Object> updatePwd(HttpServletRequest request,String userPassword,String reUserPassword){
		User user = (User) request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.userService.update(user,userPassword,reUserPassword)){
			map.put("mes", 1);
		}else{
			map.put("mes", 0);
		}
		return map;
	}
}
