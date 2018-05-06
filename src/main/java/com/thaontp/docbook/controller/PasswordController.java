//package com.thaontp.docbook.controller;
//
//import com.thaontp.docbook.model.Account;
//import com.thaontp.docbook.service.AccountService;
//import com.thaontp.docbook.service.EmailService;
//import java.util.Optional;
//import java.util.UUID;
//import javax.servlet.http.HttpServletRequest;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.mail.SimpleMailMessage;
//import org.springframework.security.crypto.password.PasswordEncoder;
//import org.springframework.stereotype.Controller;
//import org.springframework.validation.BindingResult;
//import org.springframework.web.bind.MissingServletRequestParameterException;
//import org.springframework.web.bind.annotation.ExceptionHandler;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestMethod;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//import org.springframework.web.servlet.mvc.support.RedirectAttributes;
//
//@Controller
//@RequestMapping("bookDoctor")
//public class PasswordController {
//
//  private final AccountService userService;
//
//  private final EmailService emailService;
//
//  private final PasswordEncoder passwordEncoder;
//
//  @Autowired
//  public PasswordController(AccountService userService, EmailService emailService,
//      PasswordEncoder passwordEncoder) {
//    this.userService = userService;
//    this.emailService = emailService;
//    this.passwordEncoder = passwordEncoder;
//  }
//
//  @RequestMapping(value = "/forgotpwd", method = RequestMethod.POST)
//  public ModelAndView processForgotPasswordForm(ModelAndView modelAndView,
//      @RequestParam("email") String userEmail, HttpServletRequest request) {
//
//    Optional<Account> optional = userService.findByEmail(userEmail);
//
//    if (!optional.isPresent()) {
//      modelAndView.addObject("errorMessage", "We didn't find an account for that e-mail address.");
//    } else {
//
//      Account user = optional.get();
//      user.setResetToken(UUID.randomUUID().toString());
//
//      userService.save(user);
//
//      String appUrl = request.getScheme() + "://" + request.getServerName();
//
//      SimpleMailMessage passwordResetEmail = new SimpleMailMessage();
//      passwordResetEmail.setFrom("thaontp3896@gmail.com");
//      passwordResetEmail.setTo(user.getEmail());
//      passwordResetEmail.setSubject("Password Reset Request");
//      passwordResetEmail.setText("To reset your password, click the link below:\n"
//          + "http://localhost:8080/bookDoctor/resetpwd?token=" + user.getResetToken());
//
//      emailService.sendEmail(passwordResetEmail);
//
//      modelAndView
//          .addObject("successMessage", "A password reset link has been sent to " + userEmail);
//    }
//
//    modelAndView.setViewName("login");
//    return modelAndView;
//
//  }
//
//  @RequestMapping(value = "/resetpwd", method = RequestMethod.GET)
//  public ModelAndView displayResetPasswordPage(ModelAndView modelAndView,
//      @RequestParam("token") String token) {
//
//    Optional<Account> user = userService.findByResetToken(token);
//
//    if (user.isPresent()) {
//      Account resetToken = user.get();
//      modelAndView.addObject("user", resetToken);
//    } else {
//      modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
//    }
//
//    modelAndView.setViewName("resetPassword");
//    return modelAndView;
//  }
//
//  @RequestMapping(value = "/reset", method = RequestMethod.POST)
//  public ModelAndView setNewPassword(ModelAndView modelAndView, @RequestParam("token") String token,
//      @RequestParam("password") String password, RedirectAttributes redir) {
//
//    Optional<Account> user = userService.findByResetToken(token);
//
//    if (user.isPresent()) {
//      Account resetUser = user.get();
//      resetUser.setPassword(passwordEncoder.encode(password));
//      resetUser.setResetToken(null);
//      userService.save(resetUser);
//      redir.addFlashAttribute("successMessage",
//          "You have successfully reset your password.  You may now login.");
//      modelAndView.setViewName("redirect:login");
//      return modelAndView;
//
//    } else {
//      modelAndView.addObject("errorMessage", "Oops!  This is an invalid password reset link.");
//      modelAndView.setViewName("resetPassword");
//    }
//
//    return modelAndView;
//  }
//
//  @RequestMapping(value = "/changePassword", method = RequestMethod.POST)
//  public String changepwd(Account changepwdUser, BindingResult result) {
//    if (result.hasErrors()) {
//      return "profile";
//    }
//    userService.changePassword(changepwdUser);
//    return "profile";
//  }
//
//  @ExceptionHandler(MissingServletRequestParameterException.class)
//  public ModelAndView handleMissingParams() {
//    return new ModelAndView("redirect:login");
//  }
//}