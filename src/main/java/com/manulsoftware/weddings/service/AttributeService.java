package com.manulsoftware.weddings.service;

import com.manulsoftware.weddings.entity.Attribute;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vlad on 08/05/2016.
 */
@Service
public interface AttributeService extends Repository<Attribute, Integer> {

    List<Attribute> findAllByOrderByName();

    List<Attribute> findByCategoryIdOrderByName(final Integer categoryId);

//    @PreAuthorize("hasRole('ADMIN')")
    Attribute save(final Attribute attribute);
}
