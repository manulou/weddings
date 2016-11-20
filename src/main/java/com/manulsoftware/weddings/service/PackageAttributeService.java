package com.manulsoftware.weddings.service;

import com.manulsoftware.weddings.entity.PackageAttribute;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

/**
 * Created by Vlad on 08/05/2016.
 */
@Service
public interface PackageAttributeService extends Repository<PackageAttribute, Integer> {

//    @PreAuthorize("hasRole('ADMIN')")
    PackageAttribute save(final PackageAttribute packageAttribute);
}
