package com.manulsoftware.weddings.web.services;

import java.math.BigDecimal;

public class SearchFilter {

  private Integer page;
  private String sortField;
  private String sortDirection;

  private String keyword;
  private Short countryId;
  private Integer location;
  private BigDecimal maxPrice;

  public SearchFilter(Integer page,
                      String sortField,
                      String sortDirection,
                      String keyword,
                      Short countryId,
                      Integer location,
                      BigDecimal maxPrice) {
    this.page = page;
    this.sortField = sortField;
    this.sortDirection = sortDirection;
    this.keyword = keyword;
    this.countryId = countryId;
    this.location = location;
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

  public String getKeyword() {
    return keyword;
  }

  public void setKeyword(String keyword) {
    this.keyword = keyword;
  }

  public Integer getLocation() {
    return location;
  }

  public void setLocation(Integer location) {
    this.location = location;
  }
}
