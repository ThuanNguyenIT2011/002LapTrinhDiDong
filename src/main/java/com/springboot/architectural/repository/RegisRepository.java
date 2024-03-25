package com.springboot.architectural.repository;

import com.springboot.architectural.entity.ElectricWaterPrice;
import com.springboot.architectural.entity.Regis;
import com.springboot.architectural.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RegisRepository extends JpaRepository<Regis, Integer> {
    List<Regis>findByEndRegisAtBetween(Date from,Date to);
    List<Regis>findByEndRegisAtBefore(Date to);
    List<Regis>findByEndRegisAtAfter(Date from);
}
