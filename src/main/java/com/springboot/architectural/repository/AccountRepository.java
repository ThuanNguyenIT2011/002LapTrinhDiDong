package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, String> {
    public List<Account> findByUsernameAndPassword(String username, String password);
    public Optional<Account> findByUsername(String username);
}
