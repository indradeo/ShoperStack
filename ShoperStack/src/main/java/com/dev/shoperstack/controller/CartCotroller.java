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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dev.shoperstack.entity.Cart;
import com.dev.shoperstack.service.CartService;
import com.dev.shoperstack.util.ResponseStructure;

@RestController
@RequestMapping("/api/version/carts")
public class CartCotroller {
	
	@Autowired
	private CartService service;
	
	@PostMapping("/save/{productId}")
	public ResponseEntity<ResponseStructure<Cart>> saveCart(@RequestBody Cart cart,@RequestParam(required = false,name="productId")int productId) {
		return service.saveCart(cart,productId);
	}
	
	@GetMapping("/findAll")
	public ResponseEntity<ResponseStructure<List<Cart>>> findAll(){
		return service.findAll();
	}
	
	@GetMapping("/findById")
	public ResponseEntity<ResponseStructure<Cart>> findById(@RequestParam int id) {
		return service.findById(id);
	}
	
	@DeleteMapping("/delete")
	public ResponseEntity<ResponseStructure<String>> delete(int id) {
		return  service.delete(id);
	}
	
	@DeleteMapping("/deleteById/{id}")
	public ResponseEntity<ResponseStructure<String>> deleteById(@PathVariable int id) {
		return service.deleteById(id);
	}
	
	
	@PostMapping("/addProduct")
	public ResponseEntity<ResponseStructure<Cart>> addProduct(@RequestParam int cartId,
			@RequestBody List<Integer> productIds) {
		return service.addProduct(cartId, productIds);
	}
}
