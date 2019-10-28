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
import com.medicine.bean.Sales;
import com.medicine.entity.Medicine;
import com.medicine.entity.User;
import com.medicine.service.AdminClasslistService;
import com.medicine.service.AdminMedicineService;
import com.medicine.service.UserInService;
import com.medicine.service.UserSaleService;

@Controller
@RequestMapping("user/manager/sale")
public class UserSaleController {

	@Autowired
	private UserSaleService userSaleService;
	@Autowired
	private AdminMedicineService adminMedicineService;
	@Autowired
	private AdminClasslistService adminClasslistService;
	
	/**
	 * 进入销售情况登记页面 
	 */
	@RequestMapping("/medicine/saleNumVo")
	public String saleNumVo(Model model,Integer mid){
		Medicine medicine = this.adminMedicineService.getMedicineById(mid);
		List classList = this.adminClasslistService.getClassList();
		model.addAttribute("classList", classList);
		
		model.addAttribute("medicine", medicine);
		return "user/medicine-saleNum";
	}
	
	/**
	 * 添加销售记录
	 */
	@RequestMapping("/medicine/saleNum")
	public @ResponseBody Map<String, Object> saleNum(HttpServletRequest request,Medicine medicine,Integer saleNum,String remark){
		System.out.println("ceshi"+saleNum);
		User user = (User) request.getSession().getAttribute("user");
		Map<String, Object> map = new HashMap<String, Object>();
		if(this.userSaleService.saleNum(medicine.getMid(),saleNum,user,remark)){
			map.put("mes", 1);
		}else{
			map.put("mes", 0);
		}
		return map;
	}
	
	/**
	 * 查询销售单信息
	 */
	@RequestMapping("/queryList")
	public String List(Model model,HttpServletRequest request,Integer currentPage){
		User user = (User) request.getSession().getAttribute("user");
		if(currentPage== null){
			currentPage = 1; 
		}
		Integer pageSize = 3;
		Page<Sales> pageInfo = this.userSaleService.getSaleList(currentPage,pageSize,user);
		
		model.addAttribute("pageInfo", pageInfo);
		List classList = this.adminClasslistService.getClassList();
		model.addAttribute("classList", classList);
		/*List providerList = this.adminProviderService.getPList();
		model.addAttribute("providerList", providerList);*/
		return "user/sale-list";
	}
}
