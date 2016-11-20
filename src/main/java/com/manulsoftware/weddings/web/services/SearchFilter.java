package com.manulsoftware.weddings.web.services;

import java.math.BigDecimal;

public class SearchFilter {

  private Integer page;
  private String sortField;
  private String sortDirection;

  private Short countryId;
  private BigDecimal maxPrice;

  public SearchFilter(Integer page, String sortField, String sortDirection, Short countryId, BigDecimal maxPrice) {
    this.page = page;
    this.sortField = sortField;
    this.sortDirection = sortDirection;
    this.countryId = countryId;
    this.maxPrice = maxPrice;
  }

  public Integer getPage() {
    return page;
  }

  public void setPage(Integer page) {
    this.page = page;
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

  public Short getCountryId() {
    return countryId;
  }

  public void setCountryId(Short countryId) {
    this.countryId = countryId;
  }

  public BigDecimal getMaxPrice() {
    return maxPrice;
  }

  public void setMaxPrice(BigDecimal maxPrice) {
    this.maxPrice = maxPrice;
  }
}
