package com.lucenesearchecommerce.service;

import java.util.List;

import com.lucenesearchecommerce.entity.Product;
import com.lucenesearchecommerce.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

enum GroupBy {

	BRAND("brand"), COLOR("color"), SIZE("size");
	String value;

	GroupBy(String name) {
		value = name;
	}

	String getValue() {
		return value;
	}
}

@Service
@Transactional
public class ProductServiceImpl implements ProductService {

	@Autowired
	ProductRepository productRepository;

	@Override
	public List<Product> findAll() {
		return productRepository.findAll();
	}

	@Override
	public List<Product> getProducts(String groupByValue, String actualvalue) {
		GroupBy groupBy = GroupBy.valueOf(groupByValue.toUpperCase());
		switch (groupBy) {
		case BRAND:
			return productRepository.findByBrandId(Integer.valueOf(actualvalue));
		case COLOR:
			return productRepository.findByColorId(Integer.valueOf(actualvalue));
		case SIZE:
			return productRepository.findBySize(actualvalue);
		default:
			return null;
		}
	}

public Product findById(String id) {
	// TODO Auto-generated method stub
	return productRepository.findOne(id);
 }

 public Product update(Product product, String l) {
	// TODO Auto-generated method stub
	return productRepository.save(product);
 }

 public void deleteProductById(String id) {
	// TODO Auto-generated method stub
	productRepository.delete(id);
 }

 public Product updatePartially(Product product, String id) {
	// TODO Auto-generated method stub
	Product usr = findById(id);
	usr.setPrice(product.getPrice());
	return productRepository.save(usr);
 }

}
