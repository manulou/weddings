package com.manulsoftware.weddings.service;

import java.util.List;

import org.springframework.data.domain.Page;

public class PaginatedList<T> {
	private Integer currentPage;
	private Integer lastPage;
    private Integer pageSize;
    private Long totalResults;
 
    private String sortField;
    private String sortDirection;
    private List<T> list;
    
    public PaginatedList(final Page<T> page, final String sortField, final String sortDirection) {
    	this.currentPage = page.getNumber();
    	this.lastPage = page.getTotalPages();
    	this.pageSize = page.getSize();
    	this.totalResults = page.getTotalElements();
    	this.sortField = sortField;
    	this.sortField = sortDirection;
    	this.list = page.getContent();
    }
    
	public Integer getCurrentPage() {
		return currentPage;
	}
	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage;
	}
	public Integer getPageSize() {
		return pageSize;
	}
	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}
	public Long getTotalResults() {
		return totalResults;
	}
	public void setTotalResults(Long totalResults) {
		this.totalResults = totalResults;
	}
	public String getSortField() {
		return sortField;
	}
	public void setSortField(String sortField) {
		this.sortField = sortField;
	}
	public String getSortDirection() {
		return sortDirection;
	}
	public void setSortDirection(String sortDirection) {
		this.sortDirection = sortDirection;
	}
	public List<T> getList() {
		return list;
	}
	public void setList(List<T> list) {
		this.list = list;
	}
	public Integer getLastPage() {
		return lastPage;
	}
	public void setLastPage(Integer lastPage) {
		this.lastPage = lastPage;
	}
}
