package com.thaontp.docbook.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.thaontp.docbook.common.LinkRequest;
import com.thaontp.docbook.model.Account;
import com.thaontp.docbook.model.Order;
import com.thaontp.docbook.model.Product;
import com.thaontp.docbook.model.Supplier;
import com.thaontp.docbook.service.AccountService;
import com.thaontp.docbook.service.OrderService;
import com.thaontp.docbook.service.ProductService;

@Controller
public class BaseController {

	private final ProductService	productService;
	private final OrderService		orderService;
	private final AccountService	accountService;

	@Autowired
	public BaseController(ProductService productService, OrderService orderService,
	                      AccountService accountService) {
		this.productService = productService;
		this.orderService = orderService;
		this.accountService = accountService;
	}

	@RequestMapping(value = LinkRequest.INDEX,
	                method = RequestMethod.GET)
	public ModelAndView index(Model model) {
		ModelAndView mav = new ModelAndView();
		List<Product> products = productService.getAllProduct();

		mav.addObject("products", products);
		mav.setViewName("index");
		return mav;
	}

	@RequestMapping(value = LinkRequest.LOGIN,
	                method = RequestMethod.GET)
	public ModelAndView login() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("login");
		return mav;
	}

	@RequestMapping(value = LinkRequest.DEFAULT,
	                method = RequestMethod.GET)
	public String defaultAfterLogin(HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (request.isUserInRole("SHIPPER")) {
			return "redirect:shipper/" + String.valueOf(auth.getName());
		}
		else if (request.isUserInRole("MERCHANT")) {
			return "redirect:merchant/" + String.valueOf(auth.getName());
		}
		else {
			return "redirect:index";
		}
	}

	@RequestMapping(value = LinkRequest.REGISTER,
	                method = RequestMethod.GET)
	public ModelAndView openRegister() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("register");
		return mav;
	}

	@RequestMapping(value = LinkRequest.CART,
	                method = RequestMethod.GET)
	public ModelAndView checkCart() {
		ModelAndView mav = new ModelAndView();
		mav.setViewName("cart");
		return mav;
	}

	@RequestMapping(value = LinkRequest.SHIPPER_DASHBOARD,
	                method = RequestMethod.GET)
	public ModelAndView openShipperDashboard(@PathVariable String name) {
		Account account = accountService.getActiveUser(name);
		ModelAndView mav = new ModelAndView();
		mav.addObject("shipper", account);
		mav.setViewName("shipperDashboard");
		return mav;
	}

	@RequestMapping(value = LinkRequest.MERCHANT_DASHBOARD,
	                method = RequestMethod.GET)
	public ModelAndView openMerchantDashboard(@PathVariable String name) {
		Account account = accountService.getActiveUser(name);
		Supplier suppliers = account.getSuppliers().get(0);

		List<Order> orders = orderService.getOrdersBySupplier(suppliers);

		ModelAndView mav = new ModelAndView();
		mav.addObject("merchant", account);
		mav.addObject("orders", orders);
		mav.setViewName("merchantDashboard");
		return mav;
	}
}