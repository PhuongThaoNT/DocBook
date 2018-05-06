//package com.thaontp.docbook.controller;
//
//import com.thaontp.docbook.common.LinkRequest;
//import com.thaontp.docbook.model.Account;
//import com.thaontp.docbook.service.AccountService;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.servlet.ModelAndView;
//
//@Controller
//public class AccountController {
//  private final AccountService accountService;
//
//  @Autowired
//  public AccountController(AccountService accountService) {
//    this.accountService = accountService;
//  }
//
//  @RequestMapping(value = LinkRequest.WELCOME_ADMIN, method = RequestMethod.GET)
//  public ModelAndView welcomeAdmin(){
//    ModelAndView mav = new ModelAndView();
//    mav.setViewName("adminIndex");
//    return mav;
//  }
////  @RequestMapping(value = LinkRequest.OPEN_PROFILE, method = RequestMethod.GET)
////  public ModelAndView openProfileForm(HttpServletRequest httpServletRequest){
////    ModelAndView mav = new ModelAndView();
////    Account account = accountService.getActiveUser(httpServletRequest.getRemoteUser());
////    mav.addObject("account", account);
////    mav.setViewName("profile");
////    return mav;
////  }
////  @RequestMapping(value = LinkRequest.UPDATE_PROFILE, method = RequestMethod.POST)
////  public String updateProfile(Account account, BindingResult result){
////    if (result.hasErrors()) {
////      return "redirect:/bookDoctor/admin/profile";
////    }
////    accountService.updateInfo(account);
////    return "redirect:/bookDoctor/admin";
////  }
////  @RequestMapping(value = LinkRequest.OPEN_FORM_CHANGE_PASSWORD, method = RequestMethod.GET)
////  public ModelAndView openFormChangePwd(HttpServletRequest httpServletRequest){
////    ModelAndView mav = new ModelAndView();
////    Account account = accountService.getActiveUser(httpServletRequest.getRemoteUser());
////    mav.addObject("account", account);
////    mav.setViewName("changePwd");
////    return mav;
////  }
//
// /* @RequestMapping(value = LinkRequest.CHANGE_PASSWORD, method = RequestMethod.POST)
//  public String changePwd(Account account, BindingResult result){
//    if(result.hasErrors()){
//      return "changePwd";
//    }
//    if(!accountService.checkConfirmPwd(account)){
//      return "changePwd";
//    }
//    if(!accountService.checkPwd(account)){
//      return "changePwd";
//    }
//    return "adminIndex";
//  }*/
//}
