package com.medicine.listener;

import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.medicine.service.AdminRoleService;



@WebListener
public class InitListener implements ServletContextListener{

	private static final String ProductService = null;

	@Override
	public void contextDestroyed(ServletContextEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void contextInitialized(ServletContextEvent sce) {
		// TODO Auto-generated method stub
		ServletContext sc = sce.getServletContext();
		sc.setAttribute("root", sc.getContextPath());
		sc.setAttribute("js", sc.getContextPath()+"/resources/js");
		sc.setAttribute("css", sc.getContextPath()+"/resources/css");
		sc.setAttribute("img", sc.getContextPath()+"/resources/img");
		sc.setAttribute("layer", sc.getContextPath()+"/resources/layer");
		sc.setAttribute("upload", sc.getContextPath()+"/resources/upload");
		ApplicationContext ac = new ClassPathXmlApplicationContext("applicationContext.xml");
		AdminRoleService adminRoleService = ac.getBean(AdminRoleService.class);
		sc.setAttribute("LoginRoleList", adminRoleService.getAllRoleList());
		
	}

}
