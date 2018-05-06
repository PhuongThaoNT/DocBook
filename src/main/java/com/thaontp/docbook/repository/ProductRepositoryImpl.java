/**
 * 
 */
package com.thaontp.docbook.repository;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.thaontp.docbook.model.Product;

/**
 * @author thaon
 *
 */

@Repository
@Transactional
public class ProductRepositoryImpl implements ProductRepository {

	private final EntityManager entityManager;

	@Autowired
	public ProductRepositoryImpl(EntityManager entityManager) {
		this.entityManager = entityManager;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Product> getAllProduct() {
		List<Product> products = new ArrayList<>();
		short enable = 1;
		List<?> list = entityManager.createQuery("FROM Product p WHERE p.enable=?")
		                            .setParameter(1, enable).getResultList();
		if (!list.isEmpty()) {
			products = (List<Product>) list;
		}
		return products;
	}

	@Override
	public Product getProductById(int id) {
		Product product = new Product();
		List<?> list = entityManager.createQuery("FROM Product p WHERE p.id=?").setParameter(1, id)
		                            .getResultList();
		if (!list.isEmpty()) {
			product = (Product) list.get(0);
		}
		return product;
	}

	@Override
	public void addProduct(Product product) {
		entityManager.persist(product);
	}

}
