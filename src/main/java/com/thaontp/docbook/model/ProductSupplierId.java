/**
 * 
 */
package com.thaontp.docbook.model;

import java.io.Serializable;

import javax.persistence.ManyToOne;

/**
 * @author thaon
 *
 */
public class ProductSupplierId implements Serializable {
	private static final long	serialVersionUID	= 1L;

	@ManyToOne
	private Supplier			supplier;

	@ManyToOne
	private Product				product;

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
