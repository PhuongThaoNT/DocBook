package com.thaontp.docbook.repository;

import java.util.Date;
import java.util.List;
import java.util.Objects;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.thaontp.docbook.model.Account;

@Repository
@Transactional
public class AccountRepositoryImpl implements AccountRepository {
	@PersistenceContext
	private final EntityManager entityManager;

	// /private final OrderRepository orderRepository;

	@Autowired
	public AccountRepositoryImpl(EntityManager entityManager, OrderRepository orderRepository) {
		this.entityManager = entityManager;
		// this.orderRepository = orderRepository;
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<Account> getOrderByDoctorinDay(Date day) {
		String role = "ROLE_DOCTOR";
		List<Account> accounts = entityManager.createQuery("FROM Account a WHERE a.role=?")
		                                      .setParameter(1, role).getResultList();
		// for (Account account : accounts) {
		//
		// entityManager.flush();
		// }
		return accounts;
	}

	@Override
	public Account getActiveUser(String userName) {
		Account activeAccount = new Account();
		short enabled = 1;
		List<?> list =
		             entityManager.createQuery("SELECT u FROM Account u WHERE userName=? and enabled=?")
		                          .setParameter(1, userName).setParameter(2, enabled)
		                          .getResultList();

		if (!list.isEmpty()) {
			activeAccount = (Account) list.get(0);
		}
		return activeAccount;
	}

	@Override
	public void changePassword(Account user) {
		Account us = getActiveUser(user.getUserName());
		if (!checkPassword(user, us)) {
			return;
		}
		us.setPassword(user.getResetToken());
		entityManager.flush();
	}

	@Override
	public Account getUserById(int id) {
		Account activeAccount = new Account();
		List<?> list = entityManager.createQuery("FROM Account u WHERE id=?").setParameter(1, id)
		                            .getResultList();
		if (!list.isEmpty()) {
			activeAccount = (Account) list.get(0);
		}
		return activeAccount;
	}

	@Override
	public void updateInfo(Account account) {
		Account a = getUserById(account.getId());
		a.setUserName(account.getUserName());
		// a.setFullName(account.getFullName());
		a.setPassword(account.getPassword());
		a.setEmail(account.getEmail());
		a.setPhone(account.getPhone());
		a.setEnabled((short) 1);
		entityManager.flush();
	}

	@Override
	public boolean checkPassword(Account user, Account us) {
		return Objects.equals(user.getPassword(), us.getPassword());
	}

	@Override
	public int checkCustomerExists(Account newAccount) {
		Account account = new Account();
		List<?> list =
		             entityManager.createQuery("SELECT u FROM Account u WHERE userName=? and phone=?")
		                          .setParameter(1, newAccount.getUserName())
		                          .setParameter(2, newAccount.getPhone()).getResultList();

		if (!list.isEmpty()) {
			account = (Account) list.get(0);
			return account.getId();
		}
		return 0;
	}

	public void addNewCustomer(Account account) {
		entityManager.persist(account);
	}

	@Override
	public void addNewCustomerOrUpdateInfo(Account newAccount) {

		if (checkCustomerExists(newAccount) == 0) {
			addNewCustomer(newAccount);
		}
		if (checkCustomerExists(newAccount) != 0) {
			Account account = getUserById(checkCustomerExists(newAccount));
			if (account.equals(newAccount)) {
				return;
			}
			else {
				updateInfo(newAccount);
			}
		}

	}

}