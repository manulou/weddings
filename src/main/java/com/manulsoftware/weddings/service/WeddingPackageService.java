package com.manulsoftware.weddings.service;

import com.manulsoftware.weddings.entity.WeddingPackage;
import com.manulsoftware.weddings.web.services.SearchFilter;
import org.springframework.data.domain.Page;

public interface WeddingPackageService {

  public Page<WeddingPackage> searchPackages(SearchFilter searchFilter);
}
