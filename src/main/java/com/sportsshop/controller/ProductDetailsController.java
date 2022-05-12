package com.sportsshop.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sportsshop.dto.MessageDTO;
import com.sportsshop.model.Products;
import com.sportsshop.service.ProductService;

@RestController
public class ProductDetailsController {
//		@Autowired
//		ProductRepository productRepository;

	@Autowired
	ProductService productService;

	@PostMapping("product/save")
	public ResponseEntity<?> save(@RequestBody Products product) {
		try {
			MessageDTO message = new MessageDTO("Success");
			productService.save(product);
			return new ResponseEntity<>(HttpStatus.OK);
		} catch (Exception e) {
			MessageDTO message = new MessageDTO(e.getMessage());
			return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
		}
	}

	@GetMapping("product/productlist")
	public List<Products> findAll() {
		List<Products> listProduct = null;
		try {

			listProduct = productService.findAll();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return listProduct;
	}

	@DeleteMapping("product/{id}")
	public ResponseEntity<?> deleteById(@PathVariable("id") Integer id) {
		try {

			productService.deleteById(id);
			MessageDTO message = new MessageDTO("success");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			MessageDTO message = new MessageDTO(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

		}

	}

	@PutMapping("product/{id}")
	public ResponseEntity<?> update(@PathVariable("id") Integer id, @RequestBody Products product) {
		try {
			productService.update(id, product);
			MessageDTO message = new MessageDTO("success");
			return new ResponseEntity<>(message, HttpStatus.OK);
		} catch (Exception e) {
			MessageDTO message = new MessageDTO(e.getMessage());
			return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);

		}
	}

	@GetMapping("product/{id}") // find by product Id
	public Products findById(@PathVariable("id") Integer id) {
		System.out.println("findById " + id);
		Products product = productService.findById(id);
		return product;
	}

}
