package com.thaontp.docbook.model;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "users")
public class Account implements Serializable {

	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int					id;

	@Column(name = "name")
	private String				userName;

	@Column(name = "email")
	private String				email;

	@Column(name = "phone")
	private String				phone;

	@Column(name = "pwd")
	private String				password;

	@Column(name = "role")
	private String				role;

	@Column(name = "enabled")
	private short				enabled;

	@Column(name = "reset_pwd")
	private String				resetToken;

	@Column(name = "address")
	private String				address;

	@Column(name = "credit_card")
	private String				creditCard;

	@OneToMany(mappedBy = "account",
	           cascade = CascadeType.ALL)
	private List<Order>			order;

	@OneToMany(mappedBy = "account",
	           cascade = CascadeType.ALL)
	private List<Supplier>		suppliers;

	@OneToMany(mappedBy = "shipper",
	           cascade = CascadeType.ALL)
	private List<ShipOrder>		shipOrders;

	public List<Order> getOrder() {
		return order;
	}

	public void setOrder(List<Order> order) {
		this.order = order;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String name) {
		this.userName = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public short getEnabled() {
		return enabled;
	}

	public void setEnabled(short enabled) {
		this.enabled = enabled;
	}

	public String getResetToken() {
		return resetToken;
	}

	public void setResetToken(String resetToken) {
		this.resetToken = resetToken;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCreditCard() {
		return creditCard;
	}

	public void setCreditCard(String creditCard) {
		this.creditCard = creditCard;
	}

	public List<Supplier> getSuppliers() {
		return suppliers;
	}

	public void setSuppliers(List<Supplier> suppliers) {
		this.suppliers = suppliers;
	}

	public List<ShipOrder> getShipOrders() {
		return shipOrders;
	}

	public void setShipOrders(List<ShipOrder> shipOrders) {
		this.shipOrders = shipOrders;
	}
}
