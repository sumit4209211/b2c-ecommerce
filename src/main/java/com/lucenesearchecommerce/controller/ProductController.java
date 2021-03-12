package com.lucenesearchecommerce.controller;

import java.util.List;
import com.lucenesearchecommerce.entity.Product;
import com.lucenesearchecommerce.exception.B2CException;
import com.lucenesearchecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("/api/products")
public class ProductController {
	@Autowired
	ProductService productService;

	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		List<Product> products = productService.findAll();
		if(products==null || products.isEmpty()) 
			throw new B2CException("Product not found!");
			
		return new ResponseEntity<List<Product>>(products, new HttpHeaders(), HttpStatus.OK);
	}

	@GetMapping("/filter/{filter}/{value}")
	public ResponseEntity<List<Product>> getProducts(@PathVariable String filter,
			@PathVariable String value) {
		List<Product> products = productService.getProducts(filter, value);
		if(products==null || products.isEmpty()) 
			throw new B2CException("Product not found!");
		
		return new ResponseEntity<List<Product>>(products,
				new HttpHeaders(), HttpStatus.OK);
	}
    
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Product> getProductById(@PathVariable("id") String id) {
        System.out.println("Fetching Product with id " + id);
        Product product = productService.findById(id);
        if (product == null) {
            return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Product>(product, HttpStatus.OK);
    }
    
    @PostMapping(value="/create",headers="Accept=application/json")
    public ResponseEntity<Void> createProduct(@RequestBody Product product, UriComponentsBuilder ucBuilder){
        System.out.println("Creating Product "+product.getName());
        productService.createProduct(product);
        HttpHeaders headers = new HttpHeaders();
        headers.setLocation(ucBuilder.path("/product/{id}").buildAndExpand(product.getId()).toUri());
        return new ResponseEntity<Void>(headers, HttpStatus.CREATED);
    }

    @GetMapping(value="/get", headers="Accept=application/json")
    public List<Product> getAllProduct() {   
     List<Product> tasks=productService.getProduct();
     return tasks;
   
    }

   @PutMapping(value="/update", headers="Accept=application/json")
   public ResponseEntity<String> updateProduct(@RequestBody Product currentProduct)
   {
      System.out.println("sd");
   Product product = productService.findById(currentProduct.getId());
   if (product==null) {
      return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
   }
   productService.update(currentProduct, currentProduct.getId());
   return new ResponseEntity<String>(HttpStatus.OK);
   }
   
   @DeleteMapping(value="/{id}", headers ="Accept=application/json")
   public ResponseEntity<Product> deleteProduct(@PathVariable("id") String id){
      Product product = productService.findById(id);
      if (product == null) {
         return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
      }
      productService.deleteProductById(id);
      return new ResponseEntity<Product>(HttpStatus.NO_CONTENT);
   }
   
   @PatchMapping(value="/{id}", headers="Accept=application/json")
   public ResponseEntity<Product> updateProductPartially(@PathVariable("id") String id, @RequestBody Product currentProduct){
      Product product = productService.findById(id);
      if(product ==null){
         return new ResponseEntity<Product>(HttpStatus.NOT_FOUND);
      }
      Product usr = productService.updatePartially(currentProduct, id);
      return new ResponseEntity<Product>(usr, HttpStatus.OK);
   }

}