package com.thaontp.docbook.service;

import java.util.List;

import com.thaontp.docbook.model.Cart;
import com.thaontp.docbook.model.Product;

public interface ProductService {
	List<Product> getAllProduct();

	Product getProductById(int id);

	boolean checkGroupMetadata(int productId, Cart groupMetadata);

	void addProduct(Product product);
}