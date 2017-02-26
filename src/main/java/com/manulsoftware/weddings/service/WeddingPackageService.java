package com.manulsoftware.weddings.service;

import com.manulsoftware.weddings.entity.WeddingPackage;
import com.manulsoftware.weddings.web.services.SearchFilter;
import org.springframework.data.domain.Page;

import java.util.List;

public interface WeddingPackageService {

  Page<WeddingPackage> searchPackages(SearchFilter searchFilter);

  List<WeddingPackage> getMostRecentPackages();
}
