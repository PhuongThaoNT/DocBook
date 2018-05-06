package com.thaontp.docbook.service;

import org.springframework.mail.SimpleMailMessage;

import com.thaontp.docbook.model.Account;
import com.thaontp.docbook.model.Cart;
import com.thaontp.docbook.model.Order;

public interface EmailService {
	void sendEmail(SimpleMailMessage email);

	void sendMailConfirmOrder(Account customer, Cart cart);

	void sendMailInfomDeletedOrder(Account customer, Order oder, Account merchant);
}
