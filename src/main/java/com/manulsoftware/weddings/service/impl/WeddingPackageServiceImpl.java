package com.manulsoftware.weddings.service.impl;

import com.manulsoftware.weddings.entity.Country;
import com.manulsoftware.weddings.entity.WeddingAgency;
import com.manulsoftware.weddings.entity.WeddingPackage;
import com.manulsoftware.weddings.service.WeddingPackageService;
import com.manulsoftware.weddings.web.services.SearchFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;
import org.springframework.stereotype.Service;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeddingPackageServiceImpl implements WeddingPackageService {

	@Autowired
	private WeddingPackageCRUDService weddingPackageService;

	@Override
	public Page<WeddingPackage> searchPackages(SearchFilter searchFilter) {
		final PageRequest pageRequest = new PageRequest(searchFilter
				.getPage(), 20, Sort.Direction.fromString(searchFilter.getSortDirection()), searchFilter.getSortField());
		final Page<WeddingPackage> packagesPage = weddingPackageService.findAll(filterBuilder(searchFilter), pageRequest);

		return packagesPage;
	}

	private static Specification<WeddingPackage> filterBuilder(final SearchFilter searchFilter) {
		final List<Specification<WeddingPackage>> filters = new ArrayList<>();
		if (searchFilter.getCountryId() != null) {
			filters.add(countryId(searchFilter.getCountryId()));
		}
		if (searchFilter.getMaxPrice() != null) {
			filters.add(maxPrice(searchFilter.getMaxPrice()));
		}
		if (filters.size() > 0) {
			Specifications<WeddingPackage> filter = Specifications.where(filters.get(0));
			for (int i = 1; i < filters.size(); i++) {
				filter = filter.and(filters.get(i));
			}
			return filter;
		}
		return null;
	}

	public static Specification<WeddingPackage> countryId(final Short countryId) {
		return new Specification<WeddingPackage>() {
			@Override
			public Predicate toPredicate(Root<WeddingPackage> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				Join<WeddingPackage, WeddingAgency> join = root.join("weddingAgency", JoinType.LEFT);
				Join<WeddingAgency, Country> join2 = join.join("country", JoinType.LEFT);

				return builder.equal(join2.get("id"), countryId);
			}
		};
	}

	public static Specification<WeddingPackage> maxPrice(final BigDecimal maxPrice) {
		return new Specification<WeddingPackage>() {
			@Override
			public Predicate toPredicate(Root<WeddingPackage> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
				return builder.lessThanOrEqualTo(root.get("price"), maxPrice);
			}
		};
	}
}
