package com.thaontp.docbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.thaontp.docbook.model.Account;
import com.thaontp.docbook.model.Cart;
import com.thaontp.docbook.model.Order;

@Service("emailService")
public class EmailServiceImpl implements EmailService {
	private final JavaMailSender mailSender;

	@Autowired
	public EmailServiceImpl(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}

	@Async
	public void sendEmail(SimpleMailMessage email) {
		mailSender.send(email);
	}

	@Override
	public void sendMailConfirmOrder(Account customer, Cart cart) {
		SimpleMailMessage confirmOrderEmail = new SimpleMailMessage();
		confirmOrderEmail.setFrom("shippingsystemmailler@gmail.com");
		confirmOrderEmail.setTo(customer.getEmail());

		confirmOrderEmail.setSubject("Xác nhận đơn hàng");
		confirmOrderEmail.setText("Chào " + customer.getUserName()
		                          + ",\n\nBạn vừa đặt một đơn hàng trên trang Shipping System, Thông tin đơn hàng:\n"
		                          + "Tên sản phẩm: " + cart.getProduct().getName() + "\nSố lượng: "
		                          + cart.getNoOfItem() + "\nMàu sắc: " + cart.getColor()
		                          + "\nKích thước: " + cart.getSize() + "\nGía:"
		                          + cart.getProduct().getPrice()
		                          + "\n\nCảm ơn bạn đã tin tưởng sử dụng hệ thống của chúng tôi. Chúc bạn mua sắm vui vẻ!");

		sendEmail(confirmOrderEmail);

	}

	@Override
	public void sendMailInfomDeletedOrder(Account customer, Order order, Account merchant) {
		SimpleMailMessage confirmOrderEmail = new SimpleMailMessage();
		confirmOrderEmail.setFrom("shippingsystemmailler@gmail.com");
		confirmOrderEmail.setTo(customer.getEmail());

		confirmOrderEmail.setSubject("Từ chối đơn hàng");
		Cart cart = order.getCarts().get(0);
		confirmOrderEmail.setText("Chào " + customer.getUserName()
		                          + ",\n\nĐơn hàng của bạn đặt trên trang Shipping System đã bị người bán từ chối. "
		                          + "Để biết thêm chi tiết vui lòng liên hệ với người bán theo thông tin dưới đây hoặc đặt một đơn hàng khác:\n"
		                          + "Người bán: " + merchant.getUserName() + ". Số điện thoại: "
		                          + merchant.getPhone() + ". Địa chỉ cửa hàng: "
		                          + merchant.getSuppliers().get(0).getAddress()
		                          + "\n\nThông tin đơn hàng bạn đã đặt:" + "\nTên sản phẩm: "
		                          + cart.getProduct().getName() + "\nSố lượng: "
		                          + cart.getNoOfItem() + "\nMàu sắc: " + cart.getColor() + "\nGía:"
		                          + cart.getProduct().getPrice() + "\nNơi nhận: "
		                          + order.getConsigneeAddress() + "\nNgười nhận: "
		                          + order.getConsigneeName() + "\nĐiện thoại: "
		                          + order.getConsigneePhone()
		                          + "\n\nCảm ơn bạn đã tin tưởng sử dụng hệ thống của chúng tôi. Chúc bạn mua sắm vui vẻ!");

		sendEmail(confirmOrderEmail);
	}

}