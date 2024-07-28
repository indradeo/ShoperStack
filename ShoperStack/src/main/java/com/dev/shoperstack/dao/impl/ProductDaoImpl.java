package com.dev.shoperstack.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.dev.shoperstack.dao.ProductDao;
import com.dev.shoperstack.entity.Product;
import com.dev.shoperstack.repository.ProductRepo;

@Repository
public class ProductDaoImpl implements ProductDao {

	@Autowired
	private ProductRepo repo;

	@Override
	public Product saveProduct(Product product) {

		return repo.save(product);
	}

	@Override
	public Optional<Product> findById(int productId) {

		return repo.findById(productId);
	}

	@Override
	public List<Product> findAll() {
		return repo.findAll();
	}

	@Override
	public void deleteProduct(int productId) {
		repo.deleteById(productId);

	}

	@Override
	public boolean isPresent(int id) {
		return repo.existsById(id);
	}

}
