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

@Entity
@Table(name = "orders")
public class Order implements Serializable {
	private static final long	serialVersionUID	= 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int					id;

	@ManyToOne
	@JoinColumn(name = "consignor_id")
	private Account				account;

	@Column(name = "consignee_name")
	private String				consigneeName;

	@Column(name = "consignee_phone")
	private String				consigneePhone;

	@Column(name = "consignee_address")
	private String				consigneeAddress;

	@Column(name = "service_delivery")
	private String				kindOfDelivery;

	@Column(name = "status_order")
	private short				status;

	@Column(name = "is_regist_to_ship")
	private boolean				isRegistedToShip;

	@OneToMany(mappedBy = "order",
	           cascade = CascadeType.ALL)
	private List<Cart>			carts;

	public List<Cart> getCarts() {
		return carts;
	}

	public void setCarts(List<Cart> carts) {
		this.carts = carts;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public String getKindOfDelivery() {
		return kindOfDelivery;
	}

	public void setKindOfDelivery(String kindOfDelivery) {
		this.kindOfDelivery = kindOfDelivery;

	}

	public short getStatus() {
		return status;
	}

	public String getConsigneeName() {
		return consigneeName;
	}

	public void setConsigneeName(String consigneeName) {
		this.consigneeName = consigneeName;
	}

	public String getConsigneePhone() {
		return consigneePhone;
	}

	public void setConsigneePhone(String consigneePhone) {
		this.consigneePhone = consigneePhone;
	}

	public String getConsigneeAddress() {
		return consigneeAddress;
	}

	public void setConsigneeAddress(String consigneeAddress) {
		this.consigneeAddress = consigneeAddress;
	}

	public void setStatus(short status) {
		this.status = status;
	}

	public boolean isRegistedToShip() {
		return isRegistedToShip;
	}

	public void setRegistedToShip(boolean isRegistedToShip) {
		this.isRegistedToShip = isRegistedToShip;
	}

}
