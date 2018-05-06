/**
 * 
 */
package com.thaontp.docbook.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thaontp.docbook.model.Cart;
import com.thaontp.docbook.model.Product;
import com.thaontp.docbook.model.ProductDetail;
import com.thaontp.docbook.repository.ProductRepository;

/**
 * @author thaon
 *
 */

@Service
public class ProductServiceImpl implements ProductService {

	private final ProductRepository productRepository;

	@Autowired
	public ProductServiceImpl(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}

	@Override
	public List<Product> getAllProduct() {
		return productRepository.getAllProduct();
	}

	@Override
	public Product getProductById(int id) {
		return productRepository.getProductById(id);
	}

	@Override
	public boolean checkGroupMetadata(int productId, Cart groupMetadata) {
		Product product = getProductById(productId);
		List<ProductDetail> groupMetadatas = product.getProductDetails();
		for (ProductDetail element : groupMetadatas) {
			if (element.getSize().equalsIgnoreCase(groupMetadata.getSize())
			    && element.getColor().equalsIgnoreCase(groupMetadata.getColor())) {
				if (element.getNoOfItem() < groupMetadata.getNoOfItem()) {
					return false;
				}
				else {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public void addProduct(Product product) {
		productRepository.addProduct(product);
	}

}
