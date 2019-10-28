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
import com.medicine.bean.Sales;
import com.medicine.dao.MedicineDAO;
import com.medicine.dao.SaleDAO;
import com.medicine.dao.SaledescDAO;
import com.medicine.entity.Indepotbody;
import com.medicine.entity.Isdepotheader;
import com.medicine.entity.Medicine;
import com.medicine.entity.Sale;
import com.medicine.entity.Saledesc;
import com.medicine.entity.User;

@Service
public class AdminSaleService {

	@Autowired
	private SaleDAO saleDAO;
	@Autowired
	private SaledescDAO saledescDAO;
	@Autowired
	private MedicineDAO medicineDAO;
	public SaleDAO getSaleDAO() {
		return saleDAO;
	}
	public void setSaleDAO(SaleDAO saleDAO) {
		this.saleDAO = saleDAO;
	}
	public SaledescDAO getSaledescDAO() {
		return saledescDAO;
	}
	public void setSaledescDAO(SaledescDAO saledescDAO) {
		this.saledescDAO = saledescDAO;
	}
	
	
	/**
	 * 查询入库单列表
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<Sales> getSaleList(Integer currentPage,Integer pageSize){
		//获取销售单总数
		Integer count = this.saleDAO.getCountAll();
		//获取总页数
		//Integer totalPage = Page.countPage(count, pageSize);
		Integer totalPage = 1;
		//offset length
		Integer offset = Page.countOffSet(pageSize, currentPage);
		Integer length = Page.countMySQLLength(pageSize);

		List<Sale> saleList = this.saleDAO.AdminfindAllList();
		List<Sales> list = new ArrayList<Sales>();
		
			for (Sale sal : saleList ) {
				Sales<Saledesc> in = new Sales<Saledesc>();
				in.setId(sal.getId());
				in.setSaleManId(sal.getSaleManId());
				in.setSaleMan(sal.getSaleMan());
				in.setSaleTime(sal.getSaleTime());
				Saledesc saledesc = this.saledescDAO.findBySId(sal.getId());
				Medicine medicine = this.medicineDAO.findById(saledesc.getMedicineId());
				in.setSalePrice(saledesc.getAmout()*medicine.getOutPrice());
				in.setT(saledesc);
				in.setMedicineName(medicine.getMname());
				list.add(in);
			}
		
		Page<Sales> page = new Page<Sales>(count, totalPage, pageSize, currentPage, list);
		return page;
	}
	
	/**
	 * 删除销售单 
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean del(Integer id) {
		// TODO Auto-generated method stub
		List<Saledesc> saleDescDo = this.saledescDAO.findBySaleId(id);
		/*List<Sale> saleDo = */
		Sale SaleDo = this.saleDAO.findById(id);
		this.saledescDAO.delete(saleDescDo.get(0));
		this.saleDAO.delete(SaleDo);
		return false;
	}
}
