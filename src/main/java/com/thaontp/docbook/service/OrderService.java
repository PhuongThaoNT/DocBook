package com.thaontp.docbook.service;

import java.util.List;

import com.thaontp.docbook.model.Cart;
import com.thaontp.docbook.model.Order;
import com.thaontp.docbook.model.Product;
import com.thaontp.docbook.model.Supplier;

public interface OrderService {
	int addOrder(Cart groupMetadata, Product product);

	void addGroupMetadataForParcel(Cart groupMetadata);

	Cart getGroupMetadataLastest();

	Cart getGroupMetadataById(int groupMetadataId);

	Order getOrderById(int id);

	void updateOrder(int id, Order order);

	List<Order> getOrdersBySupplier(Supplier supplier);

	void deleteOrder(Order order);

	void acceptOrder(int id, short status);
}
