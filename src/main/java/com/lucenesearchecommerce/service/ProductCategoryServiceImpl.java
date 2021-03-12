package com.lucenesearchecommerce.service;

import java.util.List;

import com.lucenesearchecommerce.entity.ProductCategory;
import com.lucenesearchecommerce.repository.ProductCategoryRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductCategoryServiceImpl implements ProductCategoryService {
	
	@Autowired
	ProductCategoryRepository productCategoryRepository;

	@Override
	public List<ProductCategory> findAll() {
		return productCategoryRepository.findAll();
	}

}
