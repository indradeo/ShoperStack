package com.dev.shoperstack.dao;

import java.util.List;
import java.util.Optional;

import com.dev.shoperstack.entity.Cart;

public interface CartDao {
	
	Cart saveCart(Cart cart);
	List<Cart> findAll();
	Optional<Cart> findById(int id);
	void delete(int id);

}
   