package com.thaontp.docbook.repository;

import com.thaontp.docbook.model.Account;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ForgotPasswordRepository extends JpaRepository<Account, Long> {
  Optional<Account> findByEmail(String email);
  Optional<Account> findByResetToken(String resetToken);
}

