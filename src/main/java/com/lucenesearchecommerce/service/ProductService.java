package com.lucenesearchecommerce.service;

import java.util.List;

import com.lucenesearchecommerce.entity.Product;

public interface ProductService {
	List<Product> findAll();
	List<Product> getProducts(String groupbyvalue, String actualvalue);
	public void createProduct(Product product);
	public List<Product> getProduct();
	public Product findById(String id);
	public Product update(Product product, String l);
	public void deleteProductById(String id);
	public Product updatePartially(Product product, String id);
	
}
