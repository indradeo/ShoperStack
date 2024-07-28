package com.dev.shoperstack.dao;

import java.util.List;
import java.util.Optional;

import com.dev.shoperstack.entity.Product;

public interface ProductDao {
	
	Product saveProduct(Product product);
	Optional<Product> findById(int productId);
	List<Product> findAll();
	void deleteProduct(int productId);
	boolean isPresent(int id);

}
