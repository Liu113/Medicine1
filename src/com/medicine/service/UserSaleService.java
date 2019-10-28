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
public class UserSaleService {

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
	 * 添加销售单
	 */
	@Transactional(propagation=Propagation.REQUIRED,rollbackFor=RuntimeException.class)
	public boolean saleNum(Integer mid, Integer saleNum, User user,String remark) {
		Medicine medicineDo = this.medicineDAO.findById(mid);
		if(saleNum > medicineDo.getMnum()){
			return false;
		}
		Sale sale = new Sale();
		Saledesc saledesc = new Saledesc();
		sale.setSaleManId(user.getId());
		sale.setSaleMan(user.getUsername());
		sale.setSaleTime(new Date());
		sale.setSalePrice(medicineDo.getOutPrice()*saleNum);
		sale.setRemark(remark);
		this.saleDAO.save(sale);
		saledesc.setSaleId(sale.getId());
		saledesc.setMedicineId(medicineDo.getMid());
		saledesc.setProviderId(medicineDo.getPid());
		saledesc.setAmout(saleNum);
		this.saledescDAO.save(saledesc);
		medicineDo.setMnum(medicineDo.getMnum()-saleNum);
		return true;
	}
	
	
	/**
	 * 查询入库单列表
	 */
	@Transactional(propagation=Propagation.NOT_SUPPORTED,readOnly=true)
	public Page<Sales> getSaleList(Integer currentPage,Integer pageSize,User user){
		//获取销售单总数
		Integer count = this.saleDAO.getCount(user.getId());
		//获取总页数
		//Integer totalPage = Page.countPage(count, pageSize);
		Integer totalPage = 1;
		//offset length
		Integer offset = Page.countOffSet(pageSize, currentPage);
		Integer length = Page.countMySQLLength(pageSize);

		List<Sale> saleList = this.saleDAO.findAllList(user.getId());
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
}
