package com.medicine.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medicine.bean.In;
import com.medicine.bean.Page;
import com.medicine.dao.IndepotbodyDAO;
import com.medicine.dao.IsdepotheaderDAO;
import com.medicine.dao.MedicineDAO;
import com.medicine.dao.SaleDAO;
import com.medicine.dao.SaledescDAO;
import com.medicine.dao.UserDAO;
import com.medicine.entity.Indepotbody;
import com.medicine.entity.Isdepotheader;
import com.medicine.entity.Medicine;
import com.medicine.entity.User;

@Service
public class AdminInService {

	@Autowired
	private IsdepotheaderDAO isdepotheaderDAO;
	@Autowired
	private IndepotbodyDAO indepotbodyDAO;
	@Autowired
	private UserDAO userDAO;
	@Autowired
	private MedicineDAO medicineDAO;
	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public IsdepotheaderDAO getIsdepotheaderDAO() {
		return isdepotheaderDAO;
	}

	public void setIsdepotheaderDAO(IsdepotheaderDAO isdepotheaderDAO) {
		this.isdepotheaderDAO = isdepotheaderDAO;
	}

	public IndepotbodyDAO getIndepotbodyDAO() {
		return indepotbodyDAO;
	}

	public void setIndepotbodyDAO(IndepotbodyDAO indepotbodyDAO) {
		this.indepotbodyDAO = indepotbodyDAO;
	}


	/**
	 * 查询入库单列表
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<In> getInList(Integer currentPage,Integer pageSize){
		//获取入库单总数
		Integer count = this.isdepotheaderDAO.getCountAll();
		//获取总页数
		//Integer totalPage = Page.countPage(count, pageSize);
		Integer totalPage = 1 ;
		//offset length
		Integer offset = Page.countOffSet(pageSize, currentPage);
		Integer length = Page.countMySQLLength(pageSize);

		List<Isdepotheader> listIth = this.isdepotheaderDAO.AdminfindAllList();
		List<In> list = new ArrayList<In>();
		
			for (Isdepotheader ith : listIth ) {
				In<Indepotbody> in = new In<Indepotbody>();
				in.setInDepotId(ith.getInDepotId());
				in.setInTime(ith.getInTime());
				in.setTabMan(ith.getTabMan());
				in.setTabManName(this.userDAO.findById(ith.getTabMan()).getUsername());
				in.setStatus(ith.getStatus());
				in.setRemark(ith.getRemark());
				Indepotbody indepotbody = this.indepotbodyDAO.findByDepotId(ith.getInDepotId());
				in.setMnum(indepotbody.getInNum());
				in.setMedicineName(this.medicineDAO.findById(indepotbody.getMedicineId()).getMname());
				in.setT(indepotbody);
				list.add(in);
			}
		
		Page<In> page = new Page<In>(count, totalPage, pageSize, currentPage, list);
		return page;
	}

	/**
	 * 入库单审核通过
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean pass(Integer id) {
		Isdepotheader isdepotheaderDo = this.isdepotheaderDAO.findById(id);
		isdepotheaderDo.setStatus(1);
		Indepotbody indepotbodyDo = this.indepotbodyDAO.findById(isdepotheaderDo.getInDepotId());
		Medicine medicineDo = this.medicineDAO.findById(indepotbodyDo.getMedicineId());
		medicineDo.setMnum(medicineDo.getMnum()+indepotbodyDo.getInNum());
		return true;
	}
	
	/**
	 * 入库单审核不通过
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean notPass(Integer id) {
		Isdepotheader isdepotheaderDo = this.isdepotheaderDAO.findById(id);
		isdepotheaderDo.setStatus(2);
		return true;
	}

	
}
