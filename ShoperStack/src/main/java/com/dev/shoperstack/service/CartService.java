package com.dev.shoperstack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.dev.shoperstack.dao.CartDao;
import com.dev.shoperstack.dao.ProductDao;
import com.dev.shoperstack.entity.Cart;
import com.dev.shoperstack.entity.Product;
import com.dev.shoperstack.exception.ProductNotFoundException;
import com.dev.shoperstack.util.ResponseStructure;

@Service
public class CartService {
	
	@Autowired
	private ProductDao productDao;
	@Autowired
	private CartDao dao;
	public ResponseEntity<ResponseStructure<Cart>> saveCart(Cart cart ,int productId) {
		
		Optional<Product> optional = productDao.findById(productId);
		if (optional.isPresent()) {

			List<Product> listOfProducts = cart.getProducts();

			if (listOfProducts == null) {
				listOfProducts = new ArrayList<Product>();
			}

			listOfProducts.add(optional.get());

			cart.setProducts(listOfProducts);

		}

		
		cart=calculatePriceOfCart(cart);
		ResponseStructure<Cart> structure= new ResponseStructure<>();
		structure.setMessage("Created");
		structure.setData(dao.saveCart(cart));
		return new ResponseEntity<ResponseStructure<Cart>>(structure,HttpStatus.CREATED) ;
	}

	public ResponseEntity<ResponseStructure<List<Cart>>> findAll() {
		ResponseStructure<List<Cart>> structure = new ResponseStructure<>();
		structure.setMessage("Accepted");
		structure.setData(dao.findAll());
		return new ResponseEntity<ResponseStructure<List<Cart>>>(structure,HttpStatus.ACCEPTED);
	}
	
	public ResponseEntity<ResponseStructure<Cart>>  findById(int id) {
		Optional<Cart> optional=dao.findById(id);
		if(!optional.isEmpty()) {
			ResponseStructure<Cart> structure = new ResponseStructure<>();
			structure.setMessage("cart Found");
			structure.setData(optional.get());
			return new ResponseEntity<ResponseStructure<Cart>>(structure, HttpStatus.FOUND);
		}
		
		return null;
	}
	
	
	private Cart calculatePriceOfCart(Cart cart) {
		List<Product> list=cart.getProducts();
		if(list.size()>0) {
			int num=list.size();
			cart.setNumberOfProducts(num);
			for(Product li:list) {
				cart.setTotalprice(cart.getTotalprice()+li.getPrice());
			}
			
		}

		return cart;
	}

	public ResponseEntity<ResponseStructure<String>> delete(int id) {
		if(dao.findById(id)!=null) {
			dao.delete(id);
			ResponseStructure<String> structure=new ResponseStructure<>();
			structure.setMessage("deleted");
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NO_CONTENT);
			
		}
		return null;
	}

	public ResponseEntity<ResponseStructure<String>> deleteById(int id) {
		if(dao.findById(id)!=null) {
			dao.delete(id);
			ResponseStructure<String> structure=new ResponseStructure<>();
			structure.setMessage("deleted");
			return new ResponseEntity<ResponseStructure<String>>(structure,HttpStatus.NO_CONTENT);
			
		}
		return null;
	}
	
	
	public ResponseEntity<ResponseStructure<Cart>> addProduct(int cartId, List<Integer> productIds) {
		Optional<Cart> optional = dao.findById(cartId);

		if (optional.isPresent()) {
			Cart cart = optional.get();

			if (cart.getProducts() == null) {
				cart.setProducts(new ArrayList<Product>());
			}

			for (Integer productId : productIds) {
				Optional<Product> optionalProduct = productDao.findById(productId);
				if (optionalProduct.isPresent()) {
					cart.getProducts().add(optionalProduct.get());
				} else {
					throw new ProductNotFoundException("Prodcut With the Given Id = " + productId + " is Not Present");
				}
			}

			cart = calculatePriceOfCart(cart);
			
			Cart modifiedCart = dao.saveCart(cart);

			ResponseStructure<Cart> structure = new ResponseStructure<>();
			structure.setMessage("Modified");
			structure.setData(modifiedCart);
			return new ResponseEntity<ResponseStructure<Cart>>(structure, HttpStatus.OK);

		}

		return null;
	}

}
