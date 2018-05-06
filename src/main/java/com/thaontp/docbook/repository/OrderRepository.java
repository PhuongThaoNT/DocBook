package com.thaontp.docbook.repository;

import java.util.Date;
import java.util.List;

import com.thaontp.docbook.model.Account;
import com.thaontp.docbook.model.Cart;
import com.thaontp.docbook.model.Order;

public interface OrderRepository {
	List<Order> findOrderInDay(Date day, Account account);

	int addOrder(Order order);

	void addGroupMetadataForParcel(Cart groupMetadata);

	Cart getGroupMetadataIdLastest();

	Cart getGroupMetadataById(int groupMetadataId);

	int getOrderLastest();

	Order getOrderById(int id);

	void updateOrder(Order newOrder);

	void delteOrder(Order order);

	void acceptOrder(int id, short status);
}
