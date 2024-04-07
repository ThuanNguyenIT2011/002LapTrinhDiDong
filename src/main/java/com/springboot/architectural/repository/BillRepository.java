package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Bill;
import com.springboot.architectural.entity.ElectricWaterPrice;
import com.springboot.architectural.entity.Regis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByCreateAtBetween(Date from, Date to);
    List<Bill>findByCreateAtBefore(Date to);
    List<Bill>findByCreateAtAfter(Date from);
    @Query("SELECT r FROM Bill r WHERE r.electricWaterPrice.id = ?1")
    public Optional<Bill> findByEWPId(int electricWaterPriceId);
    @Query("SELECT r FROM Bill r WHERE r.boardingHistory.account.username = ?1")
    public List<Bill> findAllByStudent(String studentId);
    @Query("SELECT r FROM Bill r WHERE r.electricWaterPrice.roomRegis.room.id = ?1")
    public List<Bill> findAllByRoomId(Integer roomId);
    @Query("SELECT r FROM Bill r WHERE r.electricWaterPrice.id is null")
    public List<Bill> findAllByRoom();
    @Query("SELECT r FROM Bill r WHERE r.boardingHistory.id is null")
    public List<Bill> findAllByElectricWater();

}
