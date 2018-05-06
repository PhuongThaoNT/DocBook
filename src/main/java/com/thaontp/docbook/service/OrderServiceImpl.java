package com.thaontp.docbook.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thaontp.docbook.model.Account;
import com.thaontp.docbook.model.Cart;
import com.thaontp.docbook.model.Order;
import com.thaontp.docbook.model.Product;
import com.thaontp.docbook.model.Supplier;
import com.thaontp.docbook.repository.AccountRepository;
import com.thaontp.docbook.repository.OrderRepository;
import com.thaontp.docbook.repository.ProductRepository;

@Service
public class OrderServiceImpl implements OrderService {

	private final OrderRepository	orderRepository;

	private final AccountRepository	accountRepository;

	private final ProductRepository	productRepostitory;

	private final EmailService		emailService;

	@Autowired
	public OrderServiceImpl(OrderRepository orderRepository, AccountRepository accountRepository,
	                        ProductRepository productRepostitory, EmailService emailService) {
		this.orderRepository = orderRepository;
		this.accountRepository = accountRepository;
		this.productRepostitory = productRepostitory;
		this.emailService = emailService;
	}

	@Override
	public int addOrder(Cart productDetails, Product product) {

		Order order = new Order();
		Account account = new Account();
		account.setUserName("test");
		order.setAccount(account);
		order.setConsigneePhone("default");

		order.setConsigneeAddress("default");
		order.setKindOfDelivery("default");
		order.setConsigneeName("default");
		productDetails.setOrder(order);
		productDetails.setProduct(product);
		// order.getCarts().add(productDetails);
		List<Cart> carts = new ArrayList<>();
		carts.add(productDetails);
		order.setCarts(carts);
		productRepostitory.addProduct(product);
		return orderRepository.addOrder(order);
	}

	@Override
	public void updateOrder(int id, Order newOrder) {
		Account account = newOrder.getAccount();
		accountRepository.addNewCustomerOrUpdateInfo(account);

		Order oldOrder = getOrderById(id);

		oldOrder.setKindOfDelivery(newOrder.getKindOfDelivery());
		oldOrder.setConsigneeAddress(newOrder.getConsigneeAddress());

		oldOrder.setConsigneePhone(newOrder.getConsigneePhone());

		List<Order> orders = new ArrayList<>();
		orders.add(oldOrder);
		account.setOrder(orders);
		oldOrder.setAccount(account);
		orderRepository.updateOrder(oldOrder);
		emailService.sendMailConfirmOrder(account, oldOrder.getCarts().get(0));
	}

	@Override
	public void addGroupMetadataForParcel(Cart groupMetadata) {
		orderRepository.addGroupMetadataForParcel(groupMetadata);
	}

	@Override
	public Cart getGroupMetadataLastest() {
		return orderRepository.getGroupMetadataIdLastest();
	}

	@Override
	public Cart getGroupMetadataById(int groupMetadataId) {
		return orderRepository.getGroupMetadataById(groupMetadataId);
	}

	@Override
	public Order getOrderById(int id) {
		return orderRepository.getOrderById(id);
	}

	@Override
	public List<Order> getOrdersBySupplier(Supplier supplier) {
		List<Product> products = supplier.getProduct();
		List<Order> orders = new ArrayList<>();
		for (Product product : products) {
			List<Cart> carts = product.getCarts();
			for (Cart cart : carts) {
				Order order = cart.getOrder();

				orders.add(order);

			}
		}
		return orders;
	}

	@Override
	public void deleteOrder(Order order) {
		Account merchant = order.getCarts().get(0).getProduct().getSupplier().getAccount();
		Account consignor = order.getAccount();
		orderRepository.delteOrder(order);
		if (consignor != null && consignor.getEmail() != null) {
			emailService.sendMailInfomDeletedOrder(consignor, order, merchant);
		}
	}

	@Override
	public void acceptOrder(int id, short status) {
		orderRepository.acceptOrder(id, status);
	}

}