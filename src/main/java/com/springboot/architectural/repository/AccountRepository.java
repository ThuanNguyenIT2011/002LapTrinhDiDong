package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
@Repository
public interface AccountRepository extends JpaRepository<Account, String> {
    public List<Account> findByUsernameAndPassword(String username, String password);
    public Optional<Account> findByUsername(String username);

    @Query("UPDATE Account a SET a.disable = ?2 WHERE a.username = ?1")
    @Modifying
    public void updateDisableAccount(String username, boolean disable);
}
