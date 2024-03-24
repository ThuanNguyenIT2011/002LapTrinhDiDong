package com.springboot.architectural.repository;

import com.springboot.architectural.entity.ElectricWaterPrice;
import com.springboot.architectural.entity.Regis;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RegisRepository extends JpaRepository<Regis, Integer> {
}
