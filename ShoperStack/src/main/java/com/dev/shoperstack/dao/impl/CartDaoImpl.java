package com.dev.shoperstack.dao.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dev.shoperstack.dao.CartDao;
import com.dev.shoperstack.entity.Cart;
import com.dev.shoperstack.repository.CartRepo;

@Service
public class CartDaoImpl implements CartDao {
	
	@Autowired
	private CartRepo repo;

	@Override
	public Cart saveCart(Cart cart) {
		
		return repo.save(cart);
	}

	@Override
	public List<Cart> findAll() {
		
		return repo.findAll();
	}

	@Override
	public Optional<Cart> findById(int id) {
		
		return repo.findById(id);
	}

	@Override
	public void delete(int id) {
		repo.deleteById(id);;
	}

}
