package com.springboot.architectural.repository;

import com.springboot.architectural.entity.ElectricWaterPrice;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ElectricWaterPriceRepository extends JpaRepository<ElectricWaterPrice, Integer> {
}
