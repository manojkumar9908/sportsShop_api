package com.sportsshop.model;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor

@Entity(name = "sports_orders")
public class Order {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY) // AutoIncrement
	@Column(name = "id")
	private Integer id;

	@Column(name = "user_id")
	private Integer userId;

	@Column(name = "product_id")
	private Integer productId;

	@Column(name = "purchased_on")
	private LocalDate purchasedOn;

	@Column(name = "status")
	private String status;

	@Column(name = "price")
	private Integer price;

}