package com.thaontp.docbook.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.thaontp.docbook.model.Account;
import com.thaontp.docbook.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService {

	private final AccountRepository accountRepository;

	// private final ForgotPasswordRepository resetpass;
	//
	// private final PasswordEncoder passwordEncoder;

	@Autowired
	public AccountServiceImpl(AccountRepository accountRepository) {
		// PasswordEncoder passwordEncoder) {
		this.accountRepository = accountRepository;
		// this.resetpass = resetpass;
		// this.passwordEncoder = passwordEncoder;
	}

	// @Override
	// public Optional<Account> findByEmail(String email) {
	// return resetpass.findByEmail(email);
	// }
	//
	// @Override
	// public Optional<Account> findByResetToken(String resetToken) {
	// return resetpass.findByResetToken(resetToken);
	// }
	//
	// @Override
	// public void save(Account user) {
	// resetpass.save(user);
	// }
	//
	// @Override
	// public void changePassword(Account user) {
	// String pwd = user.getPassword();
	// String resetoken = user.getResetToken();
	// user.setPassword(passwordEncoder.encode(pwd));
	// user.setResetToken(passwordEncoder.encode(resetoken));
	// accountRepository.changePassword(user);
	// }

	// @Override
	// public void updateInfo(Account account) {
	// accountRepository.updateInfo(account);
	// }
	//
	// @Override
	// public Account getUserById(int id) {
	// return accountRepository.getUserById(id);
	// }

	/* @Override
	 public boolean checkConfirmPwd(Account account){
	return Objects.equals(account.getNewPwd(), account.getConfirmPwd());
	 }*/

	/*@Override
	public boolean checkPwd(Account account) {
	Account a = accountRepository.getUserById(account.getId());
	return Objects.equals(account.getPassword(), a.getPassword());
	}*/

	@Override
	public Account getActiveUser(String username) {
		return accountRepository.getActiveUser(username);
	}
	//
	// @Override
	// public List<Account> getOrderByDoctorinDay(Date day) {
	// return accountRepository.getOrderByDoctorinDay(day);
	// }

	/* @Override
	 public void changePwd(Account account) {
	Account a = accountRepository.getUserById(account.getId());
	a.setPassword(account.getNewPwd());
	a.setEmail(account.getEmail());
	a.setUserName(account.getUserName());
	
	 }*/
}
