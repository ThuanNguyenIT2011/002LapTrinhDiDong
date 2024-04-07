package com.springboot.architectural.repository;

import com.springboot.architectural.entity.ElectricWaterPrice;
import com.springboot.architectural.entity.Room;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ElectricWaterPriceRepository extends JpaRepository<ElectricWaterPrice, Integer> {
    @Query("SELECT r FROM ElectricWaterPrice r WHERE r.roomRegis.room.id = ?1 and r.month = ?2")
    public Optional<ElectricWaterPrice> findByRoomMonth(int roomId, int month);
}
