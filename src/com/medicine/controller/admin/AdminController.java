package com.medicine.controller.admin;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.portlet.bind.annotation.ResourceMapping;

import com.medicine.bean.In;
import com.medicine.bean.Page;
import com.medicine.bean.ReturnMessage;
import com.medicine.bean.Sales;
import com.medicine.entity.Admin;
import com.medicine.entity.User;
import com.medicine.service.AdminInService;
import com.medicine.service.AdminSaleService;
import com.medicine.service.AdminService;
import com.medicine.service.UserSaleService;


@Controller
@RequestMapping("/admin")
public class AdminController {

	@Autowired
	private AdminService adminService;
	@Autowired
	private UserSaleService userSaleService;
	@Autowired
	private AdminSaleService adminSaleService;
	@Autowired
	private AdminInService adminInService;
	public UserSaleService getUserSaleService() {
		return userSaleService;
	}

	public void setUserSaleService(UserSaleService userSaleService) {
		this.userSaleService = userSaleService;
	}

	public AdminSaleService getAdminSaleService() {
		return adminSaleService;
	}

	public void setAdminSaleService(AdminSaleService adminSaleService) {
		this.adminSaleService = adminSaleService;
	}

	public AdminService getAdminService() {
		return adminService;
	}

	public void setAdminService(AdminService adminService) {
		this.adminService = adminService;
	}

	/**
	 * 登陆操作
	 * @return
	 */
	@RequestMapping("/login")
	public @ResponseBody ReturnMessage login(HttpServletRequest request,Admin admin){
		
		//判读登陆是否完成
		if(adminService.adminLogin(admin)){
			request.getSession().setAttribute("admin", admin);
			return new ReturnMessage<Admin>(1, "登陆成功");
		}else{
			return new ReturnMessage<Admin>(0, "登陆失败");
		}
	}
	
	/**
	 * 登陆成功进入管理员首页
	 */
	@RequestMapping("/manager/index")
	public String index(){
		return "admin/index";
	}
	
	/**
	 * 进入修改密码的页面
	 * @return
	 */
	@RequestMapping("/manager/pwd")
	public String pwd(){
		return "admin/pwd";
	}
	
	/**
	 * 进入欢迎页面
	 * @return
	 */
	@RequestMapping("/manager/welcome")
	public String welcome(){
		return "admin/welcome";
	}
	
	/**
	 * 查看个人信息  
	 */
	@RequestMapping("/manager/admin/person/queryVo")
	public String personQueryVo(HttpServletRequest request,Model model){
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		model.addAttribute("admin", admin);
		return "admin/personVo";
	}
	
	/**
	 * 进入修改个人信息  
	 */
	@RequestMapping("/manager/admin/person/updateVo")
	public String personUpdateVo(HttpServletRequest request,Model model){
		Admin admin = (Admin) request.getSession().getAttribute("admin");
		model.addAttribute("admin", admin);
		return "admin/personVo-edit";
	}
	
	/**
	 * 修改个人信息
	 */
	@RequestMapping("/manager/admin/person/update")
	public @ResponseBody Map<String, Object> personUpdateVo(HttpServletRequest request,Admin admin){
		System.out.println("cehsi="+admin.getAddress());
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminService.updatePerson(admin)){
			request.getSession().setAttribute("admin", admin);
			map.put("mes", 1);
		}
		return map;
	}
	
	/**
	 * 退出登录
	 */
	@RequestMapping("/logout")
	public @ResponseBody Map<String, Object> logout(HttpServletRequest request){
		request.getSession().removeAttribute("admin");
		Map<String,Object> map = new HashMap<String, Object>();
		map.put("mes", 1);
		return map;
	}
	
	/**
	 * 查询销售单信息
	 */
	@RequestMapping("/manager/admin/sale/queryList")
	public String SaleList(Model model,HttpServletRequest request,Integer currentPage){
		if(currentPage== null){
			currentPage = 1; 
		}
		Integer pageSize = 3;
		Page<Sales> pageInfo = this.adminSaleService.getSaleList(currentPage,pageSize);
		
		model.addAttribute("pageInfo", pageInfo);
		System.out.println("cehis"+pageInfo.getList().get(0));
		return "admin/sale-list";
	}
	
	@RequestMapping("/manager/admin/sale/delById")
	public @ResponseBody Map<String, Object> DelSale(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminSaleService.del(id)){
			map.put("mes", 1);
		}else{
			map.put("mes", 0);
		}
		return map;
	}
	
	/**
	 * 查询出库单
	 */
	@RequestMapping("/manager/admin/in/queryList")
	public String InList(Model model,HttpServletRequest request,Integer currentPage){
		if(currentPage== null){
			currentPage = 1; 
		}
		Integer pageSize = 3;
		Page<In> pageInfo = this.adminInService.getInList(currentPage,pageSize);
		
		model.addAttribute("pageInfo", pageInfo);
		return "admin/in-list";
	}
	
	/**
	 * 审核入库单信息(通过)
	 */
	@RequestMapping("manager/admin/in/pass")
	public @ResponseBody Map<String, Object> pass(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminInService.pass(id)){
			map.put("mes", 1);
		}else{
			map.put("mes", 0);
		}
		return map;
	}
	
	/**
	 * 审核入库单信息(不通过)
	 */
	@RequestMapping("manager/admin/in/notPass")
	public @ResponseBody Map<String, Object> notPass(Integer id){
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.adminInService.notPass(id)){
			map.put("mes", 1);
		}else{
			map.put("mes", 0);
		}
		return map;
	}
}
