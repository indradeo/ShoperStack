package com.dev.shoperstack.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.shoperstack.dao.ProductDao;
import com.dev.shoperstack.entity.Product;
import com.dev.shoperstack.util.ResponseStructure;

@Service
public class ProductService {
	
	@Autowired
	private ProductDao dao;
	
	public ResponseEntity<ResponseStructure<Product>> saveProduct(Product product) {
		
		Product saveProduct=dao.saveProduct(product);
		ResponseStructure<Product> structure = new ResponseStructure<>();
		structure.setMessage("Created");
		structure.setData(saveProduct);
		return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.CREATED);
	}
	
	
	
}
