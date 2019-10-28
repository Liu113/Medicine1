package com.medicine.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medicine.bean.Page;
import com.medicine.dao.ClasslistDAO;
import com.medicine.entity.Classlist;
import com.medicine.entity.Medicine;

@Service
public class AdminClasslistService {

	@Autowired
	private ClasslistDAO classlistDAO;

	public ClasslistDAO getClasslistDAO() {
		return classlistDAO;
	}

	public void setClasslistDAO(ClasslistDAO classlistDAO) {
		this.classlistDAO = classlistDAO;
	}
	
	/**
	 * 查询分类列表
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<Classlist> getMedicineList(Integer currentPage,Integer pageSize){
		//获取分类总数
		Integer count = this.classlistDAO.getCount();
		//获取总页数
		Integer totalPage = Page.countPage(count, pageSize);
		//offset length
		Integer offset = Page.countOffSet(pageSize, currentPage);
		Integer length = Page.countMySQLLength(pageSize);

		List<Classlist> list = this.classlistDAO.findAllList(offset,length);
		Page<Classlist> page = new Page<Classlist>(count, totalPage, pageSize, currentPage, list);
		return page;
	}
	
	/**
	 * 查询分类列表 (不分页)
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List getClassList(){
		List classList = this.classlistDAO.findAll();
		return classList;
	}
}
