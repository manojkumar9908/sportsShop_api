package com.sportsshop.validation;

import com.sportsshop.model.Products;

public class ProductValidation {

	public static void validate(Products product) throws Exception {

		if (product.getName() == null || product.getName().trim().equals("")) {
			throw new Exception("Invalid Name. Name cannot be empty/blank");
		}
		if (product.getBrand() == null || product.getBrand().trim().equals("")) {
			throw new Exception("Invalid BrandName. BrandName cannot be empty/blank");
		}

		if (product.getCategory() == null || product.getCategory().trim().equals("")) {
			throw new Exception("Invalid CategoryName. CategoryNameame cannot be empty/blank");
		}
		if (product.getPrice() == null || product.getPrice() <= 0) {
			throw new Exception("Price Must not be null");
		}
	}

}
