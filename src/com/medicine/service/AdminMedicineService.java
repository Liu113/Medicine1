package com.medicine.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medicine.bean.Page;
import com.medicine.dao.MedicineDAO;
import com.medicine.entity.Medicine;
import com.medicine.entity.Provider;
import com.medicine.entity.Roles;

@Service
public class AdminMedicineService {

	@Autowired
	private MedicineDAO medicineDAO;

	public MedicineDAO getMedicineDAO() {
		return medicineDAO;
	}

	public void setMedicineDAO(MedicineDAO medicineDAO) {
		this.medicineDAO = medicineDAO;
	}
	
	
	/**
	 * 查询药品列表
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<Medicine> getMedicineList(Integer currentPage,Integer pageSize){
		//获取供应商总数
		Integer count = this.medicineDAO.getCount();
		//获取总页数
		Integer totalPage = Page.countPage(count, pageSize);
		//offset length
		Integer offset = Page.countOffSet(pageSize, currentPage);
		Integer length = Page.countMySQLLength(pageSize);

		List<Medicine> list = this.medicineDAO.findAllList(offset,length);
		Page<Medicine> page = new Page<Medicine>(count, totalPage, pageSize, currentPage, list);
		return page;
	}

	/**
	 * 根据药品ID获取药品信息 
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Medicine getMedicineById(Integer id) {
		// TODO Auto-generated method stub
		Medicine medicine = this.medicineDAO.findById(id);
		return medicine;
	}
	
	/**
	 * 添加药品信息或者修改药品信息 
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean addOrUpdate(Medicine medicine) {
		System.out.println("cehsi"+medicine.getMdes());
		//根据mid判断添加或者更新
		if("".equals(medicine.getMid()) || medicine.getMid() == null){
			if("".equals(medicine.getClass_()) || medicine.getClass_() == null){
				return false;
			}else if("".equals(medicine.getPid()) || medicine.getPid() == null){
				return false;
			}
			medicine.setCreateTime(new Date());
			medicine.setMstatus(0);
			this.medicineDAO.saveOrUpdate(medicine);//保存新的药品信息
			return true;
			
		}else{
			Medicine medicineDo = this.medicineDAO.findById(medicine.getMid());//根据id获取药品信息
			medicineDo.setMname(medicine.getMname());//设置药品名称
			medicineDo.setClass_(medicine.getClass_());//设置药品类别
			medicineDo.setMimg(medicine.getMimg());//设置图片
			medicineDo.setMdes(medicine.getMdes());//设置药品描述
			medicineDo.setInPrice(medicine.getInPrice());//设置药品进价
			medicineDo.setOutPrice(medicine.getOutPrice());//设置药品零售价
			medicineDo.setMnum(medicine.getMnum());//设置药品数量
			medicineDo.setPid(medicine.getPid());//设置药品厂商
			medicineDo.setUpdateTime(new Date());//这是药品信息更新时间
			return true;
		}
	}
	
	/**
	 * 根据Id删除 
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean delById(Integer mid) {
		Medicine medicineDo = this.medicineDAO.findById(mid);
		medicineDo.setMstatus(1);
		return true;
	}

	/**
	 * 修改药品状态
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean updateMStatus(Integer mid) {
		Medicine medicineDo = this.medicineDAO.findById(mid);
		medicineDo.setMstatus(0);
		return true;
	}
	
	/**
	 *  按关键字查询药品信息
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page getMedicineKwList(Integer mid, String medicineName, Integer pageNumber,
			Integer pageSize) {
		//获取符合药品的总数
		Integer count = this.medicineDAO.getCountKw(mid,medicineName);
		//获取总页数
		Integer totalPage = Page.countPage(count, pageSize);
		//offset length
		Integer offset = Page.countOffSet(pageSize, pageNumber);
		Integer length = Page.countMySQLLength(pageSize);

		List<Medicine> list = this.medicineDAO.findAllKwList(offset,length,mid,medicineName);
		Page<Medicine> page = new Page<Medicine>(count, totalPage, pageSize, pageNumber, list);
		return page;
	}
	
	
}
