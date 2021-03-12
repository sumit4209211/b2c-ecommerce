package com.lucenesearchecommerce.controller;

import java.util.List;

import com.lucenesearchecommerce.entity.Brand;
import com.lucenesearchecommerce.service.BrandService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/brands")
public class BrandController {
	@Autowired
	BrandService brandService;

	@GetMapping
	public ResponseEntity<List<Brand>> findAll() {
		List<Brand> list = brandService.findAll();

		return new ResponseEntity<List<Brand>>(list, new HttpHeaders(), HttpStatus.OK);
	}
}