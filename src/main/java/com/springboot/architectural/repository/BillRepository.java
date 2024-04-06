package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Bill;
import com.springboot.architectural.entity.Regis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByCreateAtBetween(Date from, Date to);
    List<Bill>findByCreateAtBefore(Date to);
    List<Bill>findByCreateAtAfter(Date from);
}
