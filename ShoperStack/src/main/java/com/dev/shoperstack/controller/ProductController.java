package com.dev.shoperstack.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dev.shoperstack.entity.Product;
import com.dev.shoperstack.service.ProductService;
import com.dev.shoperstack.util.ResponseStructure;

@RestController
@RequestMapping("/api/version/products")
public class ProductController {
	@Autowired
	private ProductService service;
	
	@PostMapping("/save")
	public ResponseEntity<ResponseStructure<Product>> saveProduct(@RequestBody Product product) {
		return service.saveProduct(product);
	}

}
