/**
 * 
 */
package com.thaontp.docbook.repository;

import java.util.List;

import com.thaontp.docbook.model.Product;

/**
 * @author thaon
 *
 */
public interface ProductRepository {

	List<Product> getAllProduct();

	Product getProductById(int id);

	void addProduct(Product product);
}
