/****/
package com.thaontp.docbook.model;

import java.io.Serializable;
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

/***
 * 
 * @author thaon
 *
 */

@Entity
@Table(name = "suppliers")
public class Supplier implements Serializable {
	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int					id;

	@Column(name = "name")
	private String				name;

	@Column(name = "phone")
	private String				phone;

	@Column(name = "email")
	private String				email;

	@Column(name = "address")
	private String				address;

	@Column(name = "description")
	private String				description;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private Account				account;

	@OneToMany(mappedBy = "supplier",
	           cascade = CascadeType.ALL)
	private List<ShipOrder>		shipOrders;

	@OneToMany(mappedBy = "supplier",
	           cascade = CascadeType.ALL)
	private List<Product>		product;

	@ManyToOne
	@JoinColumn(name = "user_id",
	            insertable = false,
	            updatable = false)
	private Account				merchant;

	public List<Product> getProduct() {
		return product;
	}

	public void setProduct(List<Product> product) {
		this.product = product;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
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

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public List<ShipOrder> getShipOrders() {
		return shipOrders;
	}

	public void setShipOrders(List<ShipOrder> shipOrders) {
		this.shipOrders = shipOrders;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public Account getMerchant() {
		return merchant;
	}

	public void setMerchant(Account merchant) {
		this.merchant = merchant;
	}

}
