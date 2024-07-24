package com.dev.shoperstack.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dev.shoperstack.entity.Cart;

public interface CartRepo extends JpaRepository<Cart, Integer> {

}
