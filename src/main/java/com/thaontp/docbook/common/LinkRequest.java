package com.thaontp.docbook.common;

public class LinkRequest {
	public static final String	INDEX						= "/bookDoctor/index";
	public static final String	OPEN_FORM_BOOK_APPOINTMENT	= "/bookDoctor/openBookAppointmentForm";

	public static final String	ORDER						= "/bookDoctor/order";
	public static final String	OPEN_FORM_BOOK_APPOINTMENT2	=
	                                                        "/bookDoctor/openBookAppointmentForm-2";
	public static final String	LOGIN						= "/bookDoctor/login";
	public static final String	DEFAULT						= "/bookDoctor/default";
	public static final String	REGISTER					= "/bookDoctor/register";

	public static final String	QUESTION					= "/bookDoctor/question";
	public static final String	WELCOME_ADMIN				= "/bookDoctor/admin";
	public static final String	OPEN_PROFILE				= "/bookDoctor/admin/profile";
	public static final String	UPDATE_PROFILE				= "/bookDoctor/admin/changeProfile";
	public static final String	OPEN_FORM_CHANGE_PASSWORD	= "/bookDoctor/OpenChangePwd";
	public static final String	CHANGE_PASSWORD				= "/bookDoctor/changePassword";

	public static final String	CHECK_QUESTION				= "/bookDoctor/admin/ask";
	public static final String	OPEN_FORM_SEND_MAIL			= "/bookDoctor/admin/mailForAsk/{id}";
	public static final String	SEND_MAIL_FOR_ASK			= "/bookDoctor/admin/sendmail";
	public static final String	SELECT_DOCTOR				= "/bookDoctor/admin/mailForAsk/{id}";

	public static final String	DONE_ORDER					= "/bookDoctor/done/{id}/{day}";

	public static final String	SINGLE_PRODUCT				= "/bookDoctor/product/{id}";
	public static final String	CART						= "/bookDoctor/cart";

	public static final String	SHIPPER_DASHBOARD			= "/bookDoctor/shipper/{name}";
	public static final String	MERCHANT_DASHBOARD			= "/bookDoctor/merchant/{name}";

	public static final String	ADD_GROUP_METADATA			= "/bookDoctor/addGroupMetadata/{id}";

	public static final String	CHECK_OUT					= "/bookDoctor/checkOut/{id}";

	public static final String	DELETE_ORDER				=
	                                         "bookDoctor/merchant/deleteOrder/{id}";
	public static final String	ACCEPT_ORDER				=
	                                         "bookDoctor/merchant/acceptOrder/{id}";
	public static final String	ORDER_READY_TO_SHIP			=
	                                                "bookDoctor/merchant/setOrderReady/{id}";
}
