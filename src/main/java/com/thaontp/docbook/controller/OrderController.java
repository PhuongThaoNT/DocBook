package com.thaontp.docbook.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.thaontp.docbook.common.LinkRequest;
import com.thaontp.docbook.model.Cart;
import com.thaontp.docbook.model.Order;
import com.thaontp.docbook.model.Product;
import com.thaontp.docbook.service.AccountService;
import com.thaontp.docbook.service.OrderService;
import com.thaontp.docbook.service.ProductService;

@Controller
public class OrderController {

	private final OrderService		orderService;

	private final AccountService	accountService;

	private final ProductService	productService;

	@Autowired
	public OrderController(OrderService orderService, AccountService accountService,
	                       ProductService productService) {
		this.orderService = orderService;
		this.accountService = accountService;
		this.productService = productService;
	}

	@RequestMapping(value = LinkRequest.CHECK_OUT,
	                method = RequestMethod.POST)
	public String addOrder(@PathVariable int id, Order newOrder, BindingResult result) {
		// orderService.addOrder(order);
		// mav.addObject("account", accountService.getOrderByDoctorinDay(patient.getDay()));
		orderService.updateOrder(id, newOrder);

		return "redirect:/bookDoctor/index";
	}

	@RequestMapping(value = LinkRequest.DONE_ORDER,
	                method = RequestMethod.POST)
	public String doneOrder(@PathVariable int id, @PathVariable Date day) {
		return "redirect:/bookDoctor/index";
	}

	@RequestMapping(value = LinkRequest.ADD_GROUP_METADATA,
	                method = RequestMethod.POST,
	                params = "action=check-out")
	public String addParcel(@PathVariable int id, Cart productDetails, BindingResult result) {

		if (productService.checkGroupMetadata(id, productDetails)) {
			Product product = productService.getProductById(id);

			// orderService.addOrder(productDetails, product);
			// mav.addObject("order", );
			productDetails.setProduct(product);
			Order order = new Order();
			List<Cart> carts = new ArrayList<>();
			carts.add(productDetails);
			order.setCarts(carts);
			int orderId = orderService.addOrder(productDetails, product);
			// mav.addObject("newOrder", order);
			// mav.setViewName("checkout");
			// return mav;

			return "redirect:/bookDoctor/checkOut/" + String.valueOf(orderId);
		}
		else {
			return "redirect:/bookDoctor/product/" + String.valueOf(id);
			// mav.setViewName("index");
			// return mav;
		}
	}

	@RequestMapping(value = LinkRequest.CHECK_OUT,
	                method = RequestMethod.GET)
	public ModelAndView openCheckOut(@PathVariable int id) {
		ModelAndView mav = new ModelAndView();
		Order order = orderService.getOrderById(id);
		mav.addObject("newOrder", order);
		// mav.setViewName("checkout");
		// mav.addObject("product", productService.getProductById(id));
		mav.setViewName("checkout");
		return mav;
	}

	@RequestMapping(value = LinkRequest.ADD_GROUP_METADATA,
	                method = RequestMethod.POST,
	                params = "action=add-cart")
	public String addCart(@PathVariable int productId, Cart groupMetadata, BindingResult result) {
		productService.checkGroupMetadata(productId, groupMetadata);

		return "redirect:/bookDoctor/index";
	}

	@RequestMapping(value = LinkRequest.SINGLE_PRODUCT,
	                method = RequestMethod.GET)
	public ModelAndView getSingleProduct(@PathVariable int id) {
		ModelAndView mav = new ModelAndView();
		mav.addObject("product", productService.getProductById(id));
		mav.setViewName("product-info");
		return mav;
	}

	@RequestMapping(value = LinkRequest.DELETE_ORDER,
	                method = RequestMethod.GET)
	public String deleteOrder(@PathVariable int id) {
		Order order = orderService.getOrderById(id);
		orderService.deleteOrder(order);
		return "redirect:/bookDoctor/merchant/merchant";
	}

	@RequestMapping(value = LinkRequest.ACCEPT_ORDER,
	                method = RequestMethod.GET)
	public String acceptOrder(@PathVariable int id) {
		orderService.acceptOrder(id, (short) 2);
		return "redirect:/bookDoctor/merchant/merchant";
	}

	@RequestMapping(value = LinkRequest.ORDER_READY_TO_SHIP,
	                method = RequestMethod.GET)
	public String setOrderReadyToShip(@PathVariable int id) {
		orderService.acceptOrder(id, (short) 4);
		return "redirect:/bookDoctor/merchant/merchant";
	}

}
