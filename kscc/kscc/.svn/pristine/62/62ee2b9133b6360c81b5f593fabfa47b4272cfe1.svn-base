package com.fable.kscc.api.utils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 封装分页;
 * @author duyang
 * @param <T>
 */
public class Page<T> {
	
	
	private int pageSize = 10;  //一页多少条记录
 	
	private int pageCurrent = 1; //当前页数
	
	@SuppressWarnings("unused")
	private int pageTotal; //总页数
	
	private int totalRecord; //总记录数
	
	private List<? extends T> results; //分页结果集
	
	private Map<String,Object> paramsMap = new HashMap<String, Object>();  //查询参数封装

	private int pageStart;  //从第几条记录开始
	
	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getPageCurrent() {
		return pageCurrent;
	}

	public void setPageCurrent(int pageCurrent) {
		this.pageCurrent = pageCurrent;
	}

	public int getPageTotal() {
		//计算总页数
		return totalRecord%pageSize == 0 ? totalRecord/pageSize : (totalRecord%pageSize)+1;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public List<? extends T> getResults() {
		return results;
	}

	public void setResults(List<? extends T> results) {
		this.results = results;
	}

	public Map<String, Object> getParamsMap() {
		return paramsMap;
	}

	public void setParamsMap(Map<String, Object> paramsMap) {
		this.paramsMap = paramsMap;
	}

	public int getPageStart() {
		return (pageCurrent-1)*pageSize;
	}
	
}
