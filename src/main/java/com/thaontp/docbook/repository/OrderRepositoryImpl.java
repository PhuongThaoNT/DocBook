package com.thaontp.docbook.repository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.stereotype.Repository;

import com.thaontp.docbook.model.Account;
import com.thaontp.docbook.model.Cart;
import com.thaontp.docbook.model.Order;

@Repository
@Transactional
public class OrderRepositoryImpl implements OrderRepository {

	@PersistenceContext
	private EntityManager		entityManager;

	private AccountRepository	accountRepository;

	@SuppressWarnings("unchecked")
	@Override
	public List<Order> findOrderInDay(Date day, Account account) {
		List<Order> orders = entityManager.createQuery("FROM Orders").getResultList();
		List<Order> ordersInDay = new ArrayList<>();
		for (Order order : orders) {

		}
		return ordersInDay;
	}

	// status 0 when an order's recently add by customer
	// after customer confirm by email it will change to 1 or -1 if customer delete it
	// after merchant accept the order it will change to 2 or -2 if merchant delete it
	// after shipper register to ship the order, it will change to 3 and return 2 if shipper delete
	// it
	// status the order ready to ship is 4
	// status the order's shipping is 5
	// status the order's shipped success is 6
	// status the order's shipped fail is -6
	@Override
	public int addOrder(Order order) {
		order.setAccount(null);
		entityManager.persist(order);
		return getOrderLastest();
	}

	@Override
	public void addGroupMetadataForParcel(Cart groupMetadata) {
		entityManager.persist(groupMetadata);
	}

	@Override
	public Cart getGroupMetadataIdLastest() {
		Cart groupMetadataLastest = new Cart();
		List<?> list = entityManager.createQuery("FROM GroupMetadata").getResultList();
		if (!list.isEmpty()) {
			groupMetadataLastest = (Cart) list.get(0);
			return groupMetadataLastest;
		}
		return null;
	}

	@Override
	public Cart getGroupMetadataById(int groupMetadataId) {
		Cart groupMetadata = new Cart();
		List<?> list = entityManager.createQuery("FROM GroupMetadata G where G.id=?")
		                            .setParameter(1, groupMetadataId).getResultList();
		if (!list.isEmpty()) {
			groupMetadata = (Cart) list.get(0);
		}
		return groupMetadata;
	}

	@Override
	public int getOrderLastest() {
		Order order = new Order();
		List<?> list = entityManager.createQuery("FROM Order").getResultList();
		if (!list.isEmpty()) {
			order = (Order) list.get(0);
			return order.getId();
		}
		return -1;
	}

	@Override
	public Order getOrderById(int id) {
		Order order = new Order();
		List<?> list = entityManager.createQuery("FROM Order o where o.id=?").setParameter(1, id)
		                            .getResultList();
		if (!list.isEmpty()) {
			order = (Order) list.get(0);
			return order;
		}
		return null;
	}

	@Override
	public void updateOrder(Order newOrder) {
		entityManager.flush();
	}

	@Override
	public void delteOrder(Order order) {
		entityManager.remove(order);
	}

	@Override
	public void acceptOrder(int id, short status) {
		Order order = getOrderById(id);
		order.setStatus(status);
		entityManager.flush();
	}

}