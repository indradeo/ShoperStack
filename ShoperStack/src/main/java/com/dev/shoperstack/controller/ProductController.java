package com.dev.shoperstack.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@GetMapping("/findById/{id}")
	public ResponseEntity<ResponseStructure<Product>> findById(@PathVariable int id) {	
		return service.findById(id);	
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		return service.findAll();
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<ResponseStructure<Product>> deleteById(@PathVariable int id) {
		return service.deleteById(id);
	}
}
