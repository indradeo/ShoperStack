package com.dev.shoperstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.shoperstack.entity.Product;

public interface ProductRepo extends JpaRepository<Product, Integer>{

}
