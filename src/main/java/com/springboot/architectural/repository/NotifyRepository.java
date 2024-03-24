package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Notify;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface NotifyRepository extends JpaRepository<Notify, Integer> {
    public List<Notify> findByAccount(Account account);
}
