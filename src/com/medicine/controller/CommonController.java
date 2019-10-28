package com.medicine.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class CommonController {

	@RequestMapping("/common/admin")
	public String home(){
		return "admin/login";
	}
	
	@RequestMapping("user/common/user")
	public String userHome(){
		return "user/login";
	}
}
