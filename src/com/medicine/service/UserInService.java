package com.medicine.service;

import java.util.ArrayList;
import java.util.Date;
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
import com.medicine.entity.Indepotbody;
import com.medicine.entity.Isdepotheader;
import com.medicine.entity.Medicine;
import com.medicine.entity.Sale;
import com.medicine.entity.Saledesc;
import com.medicine.entity.User;

@Service
public class UserInService {

	@Autowired
	private MedicineDAO medicineDAO;
	@Autowired
	private IsdepotheaderDAO isdepotheaderDAO;
	@Autowired
	private IndepotbodyDAO indepotbodyDAO;
	public MedicineDAO getMedicineDAO() {
		return medicineDAO;
	}
	public void setMedicineDAO(MedicineDAO medicineDAO) {
		this.medicineDAO = medicineDAO;
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
	 * 添加入库单
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean addNum(Integer mid, Integer addNum, User user) {
		Medicine medicineDo = this.medicineDAO.findById(mid);
		Isdepotheader ith = new Isdepotheader();//入库单主单
		Indepotbody itb = new Indepotbody();//入库单从单
		ith.setInTime(new Date());
		ith.setTabMan(user.getId());
		ith.setStatus(0);
		this.isdepotheaderDAO.save(ith);
		itb.setInDepotId(ith.getInDepotId());
		itb.setInNum(addNum);
		itb.setMedicineId(medicineDo.getMid());
		itb.setInPrice(medicineDo.getInPrice());
		itb.setOutPrice(medicineDo.getOutPrice());
		itb.setProviderId(medicineDo.getPid());
		this.indepotbodyDAO.save(itb);
		return true;
	}
	
	/**
	 * 查询入库单列表
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<In> getInList(Integer currentPage,Integer pageSize,User user){
		//获取入库单总数
		Integer count = this.isdepotheaderDAO.getCount(user.getId());
		//获取总页数
		//Integer totalPage = Page.countPage(count, pageSize);
		Integer totalPage = 1 ;
		//offset length
		Integer offset = Page.countOffSet(pageSize, currentPage);
		Integer length = Page.countMySQLLength(pageSize);

		List<Isdepotheader> listIth = this.isdepotheaderDAO.findAllList(user.getId());
		List<In> list = new ArrayList<In>();
		
			for (Isdepotheader ith : listIth ) {
				In<Indepotbody> in = new In<Indepotbody>();
				in.setInDepotId(ith.getInDepotId());
				in.setInTime(ith.getInTime());
				in.setTabMan(ith.getTabMan());
				in.setTabManName(user.getUsername());
				in.setStatus(ith.getStatus());
				in.setRemark(ith.getRemark());
				in.setT(this.indepotbodyDAO.findByDepotId(ith.getInDepotId()));
				list.add(in);
			}
		
		Page<In> page = new Page<In>(count, totalPage, pageSize, currentPage, list);
		return page;
	}
	
	/**
	 * 根据搜索条件进行分页查询
	 */
	public Page getInKwList(Integer inDepotId, Integer pageNumber, Integer pageSize,User user) {
		// TODO Auto-generated method stub
		//获取入库单总数
		Integer count = this.isdepotheaderDAO.getkwCount(user.getId(),inDepotId);
		//获取总页数
		Integer totalPage = Page.countPage(count, pageSize);
		//offset length
		Integer offset = Page.countOffSet(pageSize, pageNumber);
		Integer length = Page.countMySQLLength(pageSize);

		List<Isdepotheader> listIth = this.isdepotheaderDAO.findAllKwList(offset,length,user.getId(),inDepotId);
		List<In> list = new ArrayList<In>();
			for (Isdepotheader ith : listIth ) {
				In<Indepotbody> in = new In<Indepotbody>();
				in.setInDepotId(ith.getInDepotId());
				in.setInTime(ith.getInTime());
				in.setTabMan(user.getId());
				in.setStatus(ith.getStatus());
				in.setRemark(ith.getRemark());
				in.setT(this.indepotbodyDAO.findByDepotId(ith.getInDepotId()));
				list.add(in);
			}
		
		Page<In> page = new Page<In>(count, totalPage, pageSize, pageNumber, list);
		return page;
	}
	
	
}
