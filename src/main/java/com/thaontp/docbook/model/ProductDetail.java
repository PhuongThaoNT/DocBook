package com.thaontp.docbook.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "product_details")
public class ProductDetail implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int					id;

	@Column(name = "size")
	private String				size;
	@Column(name = "color")
	private String				color;
	@Column(name = "no_of_item")
	private int					noOfItem;

	@Column(name = "type_of_metadata")
	private String				typeOfMetadata;

	@ManyToOne
	@JoinColumn(name = "product_id")
	private Product				product;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Product getProduct() {
		return product;
	}

	public void setProduct(Product product) {
		this.product = product;
	}

	public String getTypeOfMetadata() {
		return typeOfMetadata;
	}

	public void setTypeOfMetadata(String typeOfMetadata) {
		this.typeOfMetadata = typeOfMetadata;
	}

}
