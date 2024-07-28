package com.dev.shoperstack.entity;

import java.time.LocalDateTime;
import java.util.List;

import org.hibernate.annotations.CreationTimestamp;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Cart {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int cartId;
	private int numberOfProducts;
	private double totalprice;
	@CreationTimestamp
	@Column(columnDefinition = "TIMESTAMP")
	private LocalDateTime creationDateTime;
	
	@OneToMany(mappedBy = "cart",cascade = CascadeType.ALL)
	private List<Product> products;

}
