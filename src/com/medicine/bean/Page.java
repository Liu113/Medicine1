package com.medicine.bean;

import java.util.List;

public class Page<T> {

	
	private Integer count;//总记录数
	private Integer totalPage;//总页数
	private Integer pageSize;//每页显示的条数
	private Integer currentPage;//当前页
	private List<T> list;//分页之后的数据
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Integer getTotalPage() {
		return totalPage;
	}
	public void setTotalPage(Integer totalPage) {
		this.totalPage = totalPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	
	public Page() {
		// TODO Auto-generated constructor stub
	}
	public Page(Integer count, Integer totalPage, Integer pageSize,
			Integer currentPage, List<T> list) {
		super();
		this.count = count;
		this.totalPage = totalPage;
		this.pageSize = pageSize;
		this.currentPage = currentPage;
		this.list = list;
	}
	
	
	/**
	 * 计算总页数
	 * @param count 总记录数
	 * @param pageSize 每页显示的条数
	 * @return 总页数
	 */
	public static int  countPage(final int count,final int pageSize){
		return count % pageSize==0?count/pageSize:count/pageSize+1;
	}
	
	
	/**
	 * 从哪条开始取(当前记录数)
	 * @param pageSize 每页显示条数
	 * @param currentPage 当前页
	 * @return 当前记录数
	 */
	public static int countOffSet(final int pageSize,final int currentPage){
		return pageSize*(currentPage-1);
	}
	
	
	/**
	 * 
	 * MYSQL
	 * 取几条
	 * @param pageSize 每页显示的记录数
	 * @return 取几条
	 */
	public static int countMySQLLength(final int pageSize){
		return pageSize;
	}
	
	/**
	 * 
	 * ORACLE
	 * 取几条
	 * @param pageSize 每页显示的记录数
	 * @param currentPage 当前页
	 * @return 取几条
	 */
	public static int countOracleLength(final int pageSize,final int currentPage){
		return pageSize*currentPage;
	}
}
