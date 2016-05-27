package com.manulsoftware.weddings.service;

import com.manulsoftware.weddings.entity.Category;
import com.manulsoftware.weddings.entity.WeddingAgency;
import com.manulsoftware.weddings.entity.WeddingPackage;
import org.springframework.data.repository.Repository;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Vlad on 08/05/2016.
 */
@Service
public interface CategoryService extends Repository<Category, Integer> {

    List<Category> findAllByOrderByPriority();

    Category save(final Category category);
}
