package com.thaontp.docbook.service;

import com.thaontp.docbook.model.Account;

public interface AccountService {
	//
	// Optional<Account> findByEmail(String userEmail);
	//
	// void save(Account user);
	//
	// Optional<Account> findByResetToken(String token);
	//
	// void changePassword(Account account);
	//
	// void updateInfo(Account account);
	//
	// Account getUserById(int id);
	//
	//// boolean checkConfirmPwd(Account account);
	//
	//// boolean checkPwd(Account account);
	//
	Account getActiveUser(String username);
	//
	// // void changePwd(Account account);
	//
	// List<Account> getOrderByDoctorinDay(Date day);
}
