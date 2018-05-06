/**
 * 
 */
package com.thaontp.docbook.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * @author thaon
 *
 */

@Entity
@Table(name = "carts")
public class Cart implements Serializable {
	private static final long	serialVersionUID	= 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "order_id")
	private Order				order;

	@Id
	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product				product;

	@Column(name = "size")
	private String				size;

	@Column(name = "color")
	private String				color;

	@Column(name = "no_of_item")
	private int					noOfItem;

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public int getNoOfItem() {
		return noOfItem;
	}

	public void setNoOfItem(int noOfItem) {
		this.noOfItem = noOfItem;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

}
