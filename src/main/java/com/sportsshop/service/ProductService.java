package com.sportsshop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sportsshop.dao.ProductRepository;
import com.sportsshop.model.Products;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;

	public void save(Products product) throws Exception {
		try {
			productRepository.save(product);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public List<Products> findAll() throws Exception {
		List<Products> listProduct = null;
		try {
			listProduct = productRepository.findAll();

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
		return listProduct;
	}

	public void deleteById(Integer id) throws Exception {
		try {
			productRepository.deleteById(id);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public void update(Integer id, Products product) throws Exception {
		try {
			product.setId(id);
			productRepository.save(product);

		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}
	}

	public Products findById(Integer id) {

		Optional<Products> product = productRepository.findById(id);
		if (product.isPresent()) {
			Products productObj = product.get();
			return productObj;
		} else {
			return null;
		}
	}

}
