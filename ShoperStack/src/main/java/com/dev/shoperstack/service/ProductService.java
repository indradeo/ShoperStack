package com.dev.shoperstack.service;

import java.util.List;
import java.util.Optional;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.shoperstack.dao.ProductDao;
import com.dev.shoperstack.entity.Product;
import com.dev.shoperstack.exception.ProductNotFoundException;
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

	public ResponseEntity<ResponseStructure<Product>> findById(int id) {
		
		Optional<Product>p=dao.findById(id);
		if(p!=null) {
			ResponseStructure<Product> structure=new ResponseStructure<>();
			structure.setMessage("Accepted");
			structure.setData(p.get());
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.ACCEPTED);
		}
		
		throw new ProductNotFoundException("Product ot Found with id "+id);
		
	}

	public ResponseEntity<ResponseStructure<List<Product>>> findAll() {
		
		ResponseStructure<List<Product>> structure= new ResponseStructure<>();
		structure.setMessage("Accepted");
		structure.setData(dao.findAll());
		
		return new ResponseEntity<ResponseStructure<List<Product>>>(structure, HttpStatus.ACCEPTED);
		
	}

	public ResponseEntity<ResponseStructure<Product>> deleteById(int id) {
		if(dao.isPresent(id)) {
			dao.deleteProduct(id);
			ResponseStructure<Product> structure=new ResponseStructure<>();
			structure.setMessage("recorde deteled with id"+id);
			structure.setData(null);
			return new ResponseEntity<ResponseStructure<Product>>(structure, HttpStatus.NO_CONTENT);

		}
		throw new ProductNotFoundException("Cannot Found Product with id "+ id);
		
	}

	public ResponseEntity<ResponseStructure<String>> delete(int id) {
		
		if(dao.isPresent(id)){
			dao.deleteProduct(id);
			ResponseStructure<String> structure = new ResponseStructure<>();
			structure.setMessage("Record Deleted");
			structure.setData("");
			return new  ResponseEntity<ResponseStructure<String>>(structure, HttpStatus.NO_CONTENT);
		}
		throw new ProductNotFoundException("Product Not Found with id "+id);
	}

	public ResponseEntity<ResponseStructure<Product>> updateProduct(Product product) {
			if(dao.isPresent(product.getId())) {
				dao.saveProduct(product);
				ResponseStructure<Product> structure= new ResponseStructure<>();
				structure.setMessage("Product Modified");
				structure.setData(product);
				return new ResponseEntity<ResponseStructure<Product>>(structure,HttpStatus.OK);
			}
		throw new ProductNotFoundException("Product not found");
	}
	
	
	
	
}
