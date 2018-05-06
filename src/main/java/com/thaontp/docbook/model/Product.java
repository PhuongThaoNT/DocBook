/**
 * 
 */
package com.thaontp.docbook.model;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 * @author thaon
 *
 */

@Entity
@Table(name = "products")
public class Product implements Serializable {
	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int					id;

	@Column(name = "name")
	private String				name;

	@Column(name = "price")
	private String				price;

	@Column(name = "product_code")
	private String				productCode;

	@ManyToOne
	@JoinColumn(name = "category_id")
	private Category			category;

	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier			supplier;

	@Column(name = "enabled")
	private short				enable;

	@OneToMany(mappedBy = "product",
	           cascade = CascadeType.ALL)
	private List<ProductDetail>	productDetails;

	@OneToMany(mappedBy = "product",
	           cascade = CascadeType.ALL)
	private List<Cart>			carts;

	@Column(name = "discount_end_date")
	private Date				endDiscountDate;

	public Date getEndDiscountDate() {
		return endDiscountDate;
	}

	public List<ProductDetail> getProductDetails() {
		return productDetails;
	}

	public void setProductDetails(List<ProductDetail> productDetails) {
		this.productDetails = productDetails;
	}

	public void setEndDiscountDate(Date endDiscountDate) {
		this.endDiscountDate = endDiscountDate;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public short getEnable() {
		return enable;
	}

	public void setEnable(short enable) {
		this.enable = enable;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

}
