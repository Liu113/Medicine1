package com.medicine.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.medicine.bean.Page;
import com.medicine.dao.MedicineDAO;
import com.medicine.dao.ProviderDAO;
import com.medicine.entity.Provider;

@Service
public class AdminProviderService {

	@Autowired
	private ProviderDAO providerDAO;
	@Autowired
	private MedicineDAO medicineDAO;

	public MedicineDAO getMedicineDAO() {
		return medicineDAO;
	}

	public void setMedicineDAO(MedicineDAO medicineDAO) {
		this.medicineDAO = medicineDAO;
	}

	public ProviderDAO getProviderDAO() {
		return providerDAO;
	}

	public void setProviderDAO(ProviderDAO providerDAO) {
		this.providerDAO = providerDAO;
	}
	
	/**
	 * 查询供应商列表
	 * @return
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<Provider> getProviderList(Integer currentPage,Integer pageSize,Integer is_delete){
		//获取供应商总数
		Integer count = this.providerDAO.getCount(is_delete);
		//获取总页数
		Integer totalPage = Page.countPage(count, pageSize);
		//offset length
		Integer offset = Page.countOffSet(pageSize, currentPage);
		Integer length = Page.countMySQLLength(pageSize);

		List<Provider> list = this.providerDAO.findAllList(offset,length);
		Page<Provider> page = new Page<Provider>(count, totalPage, pageSize, currentPage, list);
		return page;
	}

	/**
	 * 根据关键字相应的分页查询 
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page getProviderKwList(Provider provider, Integer pageNumber,Integer pageSize) {
		//Integer is_delete = 0;
		//获取供应商总数
		Integer count = this.providerDAO.getCountKw(provider.getPid(),provider.getPname());
		//获取总页数
		Integer totalPage = Page.countPage(count, pageSize);
		//offset length
		Integer offset = Page.countOffSet(pageSize, pageNumber);
		Integer length = Page.countMySQLLength(pageSize);

		List<Provider> list = this.providerDAO.findAllList(offset,length);
		Page<Provider> page = new Page<Provider>(count, totalPage, pageSize, pageNumber, list);
		return page;
	}

	/**
	 *	获取根据关键词搜索到厂商信息 
	 */
	public Page<Provider> getKwProviderList(Integer currentPage,
			Integer pageSize, Integer is_delete, Integer pid, String pname) {
		if("".equals(is_delete) || is_delete == null){
			is_delete = 0;
		}
		
		//获取供应商总数
		Integer count = this.providerDAO.getCountKw(pid,pname);
		System.out.println("测试符合添加供应商数量"+count);
		//获取总页数
		Integer totalPage = Page.countPage(count, pageSize);
		//offset length
		Integer offset = Page.countOffSet(pageSize, currentPage);
		Integer length = Page.countMySQLLength(pageSize);

		List<Provider> list = this.providerDAO.findAllKwList(offset,length,is_delete,pid,pname);
		Page<Provider> page = new Page<Provider>(count, totalPage, pageSize, currentPage, list);
		return page;
	}

	/**
	 * 添加或更新供应商信息
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean addOrUpdate(Provider provider) {
		//根据pid判断添加或者更新
		if("".equals(provider.getPid()) || provider.getPid() == null){
			provider.setCreatetime(new Date());
			provider.setIsDelete(0);
			providerDAO.saveOrUpdate(provider);
			return true;
		}else{
			Provider providerDo = this.providerDAO.findById(provider.getPid());
			providerDo.setPname(provider.getPname());
			providerDo.setPrincipal(provider.getPrincipal());
			providerDo.setTelephone(provider.getTelephone());
			providerDo.setAddress(provider.getAddress());
			providerDo.setSpare1(provider.getSpare1());
			return true;
		}
		
	}

	/**
	 * 修改厂商状态
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean change(Integer pid) {
		// TODO Auto-generated method stub
		//查询该厂商身份下的药品数量
		Integer count = medicineDAO.getCountByPid(pid);
		if(count == 0){
			//根据pid获取厂商
			Provider provider = this.providerDAO.findById(pid);
			if(provider.getIsDelete() == 1){
				provider.setIsDelete(0);
			}else{
				provider.setIsDelete(1);
			}
			this.providerDAO.saveOrUpdate(provider);
			return true;
		}else{
			return false;
		}
	}

	/**
	 *	根据ID获取厂商信息 
	 */
	public Provider getProviderById(Integer pid) {
		// TODO Auto-generated method stub
		Provider provider = this.providerDAO.findById(pid);
		return provider;
	}

	/**
	 *  根据厂商ID删除厂商信息
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean delById(Integer pid) {
		// TODO Auto-generated method stub
		Provider providerDo = this.providerDAO.findById(pid);
		if(providerDo.getIsDelete() == 1){
			this.providerDAO.delete(providerDo);
			return true;
		}else{
			return false;
		}
		
	}

	/**
	 * 获取供应商列表信息(不分页)
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public List getPList() {
		List providerList = this.providerDAO.findAll();
		return providerList;
	}
	
	
}
