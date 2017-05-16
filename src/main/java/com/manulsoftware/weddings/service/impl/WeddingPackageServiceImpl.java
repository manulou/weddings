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

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Service
public class WeddingPackageServiceImpl implements WeddingPackageService {

	@Autowired
	private WeddingPackageCRUDService weddingPackageService;

	@Override
	public List<WeddingPackage> getMostRecentPackages() {
		return weddingPackageService.findFirst10ByVisibleOrderByCreatedDesc(true);
	}

	@Override
	public Page<WeddingPackage> searchPackages(SearchFilter searchFilter) {
		final PageRequest pageRequest = new PageRequest(searchFilter
				.getPage(), 20, Sort.Direction.fromString(searchFilter.getSortDirection()), searchFilter.getSortField());
		final Page<WeddingPackage> packagesPage = weddingPackageService.findAll(filterBuilder(searchFilter), pageRequest);

		return packagesPage;
	}

	private static Specification<WeddingPackage> filterBuilder(final SearchFilter searchFilter) {
		final List<Specification<WeddingPackage>> filters = new ArrayList<>();
		filters.add(visible());
		filters.add(agencyVisible());
		if (searchFilter.getCountryId() != null && searchFilter.getCountryId() > 0) {
			filters.add(countryId(searchFilter.getCountryId()));
		}
		if (searchFilter.getMaxPrice() != null) {
			filters.add(maxPrice(searchFilter.getMaxPrice()));
		}
		if (searchFilter.getKeyword() != null) {
			final String keyword = "%" + searchFilter.getKeyword().toUpperCase() + "%";
			filters.add(
					Specifications.where(
							keywordInName(keyword))
								.or(
							keywordInAgencyName(keyword))
								.or(
							keywordInAgencyDescription(keyword))
								.or(
							keywordInDescription(keyword))
								.or(
							keywordInAttributes(keyword)));
		}
		if (searchFilter.getLocation() != null) {
			filters.add(location(searchFilter.getLocation()));
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

	public static Specification<WeddingPackage> visible() {
		return (root, query, builder) -> builder.equal(root.get("visible"), true);
	}

	public static Specification<WeddingPackage> agencyVisible() {
		return (root, query, builder) -> {
            Join<WeddingPackage, WeddingAgency> join = root.join("weddingAgency", JoinType.LEFT);

            return builder.equal(join.get("visible"), true);
        };
	}

	public static Specification<WeddingPackage> countryId(final Short countryId) {
		return (root, query, builder) -> {
            Join<WeddingPackage, WeddingAgency> join = root.join("weddingAgency", JoinType.LEFT);
            Join<WeddingAgency, Country> join2 = join.join("country", JoinType.LEFT);

            return builder.equal(join2.get("id"), countryId);
        };
	}

	public static Specification<WeddingPackage> maxPrice(final BigDecimal maxPrice) {
		return (root, query, builder) -> builder.lessThanOrEqualTo(root.get("price"), maxPrice);
	}

	public static Specification<WeddingPackage> keywordInAgencyName(final String keyword) {
		return (root, query, builder) -> {
            Join<WeddingPackage, WeddingAgency> join = root.join("weddingAgency", JoinType.LEFT);

            return builder.like(builder.upper(join.get("name")), keyword);
        };
	}

	public static Specification<WeddingPackage> keywordInAgencyDescription(final String keyword) {
		return (root, query, builder) -> {
            Join<WeddingPackage, WeddingAgency> join = root.join("weddingAgency", JoinType.LEFT);

            return builder.like(builder.upper(join.get("description")), keyword);
        };
	}

	public static Specification<WeddingPackage> keywordInName(final String keyword) {
		return (root, query, builder) -> builder.like(builder.upper(root.get("name")), keyword);
	}

	public static Specification<WeddingPackage> keywordInDescription(final String keyword) {
		return (root, query, builder) -> builder.like(builder.upper(root.get("description")), keyword);
	}

	public static Specification<WeddingPackage> keywordInAttributes(final String keyword) {
		return (root, query, builder) -> builder.like(builder.upper(root.get("allAttributes")), keyword);
	}

	public static Specification<WeddingPackage> location(final Integer locationId) {
		return (root, query, builder) -> builder.equal(root.get("locationId"), locationId);
	}
}
