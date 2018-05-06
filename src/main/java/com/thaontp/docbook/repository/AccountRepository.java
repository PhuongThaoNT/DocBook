package com.thaontp.docbook.repository;

import java.util.Date;
import java.util.List;

import com.thaontp.docbook.model.Account;

public interface AccountRepository {
	List<Account> getOrderByDoctorinDay(Date day);

	Account getActiveUser(String userName);

	boolean checkPassword(Account user, Account us);

	void changePassword(Account changepwdUser);

	Account getUserById(int id);

	void updateInfo(Account account);

	int checkCustomerExists(Account account);

	void addNewCustomerOrUpdateInfo(Account account);
}
