package com.lucenesearchecommerce.service;

import java.util.List;

import com.lucenesearchecommerce.entity.Brand;
import com.lucenesearchecommerce.repository.BrandRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class BrandServiceImpl implements BrandService {
	
	@Autowired
	BrandRepository brandRepository;

	@Override
	public List<Brand> findAll() {
		return brandRepository.findAll();
	}

}
