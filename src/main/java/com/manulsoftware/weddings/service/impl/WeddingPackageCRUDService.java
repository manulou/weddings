package com.manulsoftware.weddings.service.impl;

import com.manulsoftware.weddings.entity.WeddingAgency;
import com.manulsoftware.weddings.entity.WeddingPackage;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by Vlad on 08/05/2016.
 */
@Service
public interface WeddingPackageCRUDService extends Repository<WeddingPackage, Integer> {

    List<WeddingPackage> findAll();

    List<WeddingPackage> findByWeddingAgency(final WeddingAgency agency);

    List<WeddingPackage> findFirst10ByVisibleOrderByCreatedDesc(final boolean visible);

    @Transactional
    Long deleteByWeddingAgency(final WeddingAgency agency);

    WeddingPackage save(final WeddingPackage weddingPackage);

    void delete(final WeddingPackage weddingPackage);

    WeddingPackage findOne(Integer id);

    Page<WeddingPackage> findAll(Specification<WeddingPackage> spec, Pageable pageable);
}
