package com.thaontp.docbook.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "ship_order")
public class ShipOrder implements Serializable {
	private static final long	serialVersionUID	= 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "supplier_id")
	private Supplier			supplier;

	@Id
	@ManyToOne
	@JoinColumn(name = "user_id")
	private Account				shipper;

	@Column(name = "no_of_order")
	private int					noOfOrders;

	@Column(name = "wage")
	private String				wage;

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Account getShipper() {
		return shipper;
	}

	public void setShipper(Account shipper) {
		this.shipper = shipper;
	}

	public int getNoOfOrders() {
		return noOfOrders;
	}

	public void setNoOfOrders(int noOfOrders) {
		this.noOfOrders = noOfOrders;
	}

	public String getWage() {
		return wage;
	}

	public void setWage(String wage) {
		this.wage = wage;
	}

}
