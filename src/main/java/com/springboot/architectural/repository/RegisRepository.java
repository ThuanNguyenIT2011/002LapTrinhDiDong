package com.springboot.architectural.repository;

import com.springboot.architectural.entity.ElectricWaterPrice;
import com.springboot.architectural.entity.Regis;
import com.springboot.architectural.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RegisRepository extends JpaRepository<Regis, Integer> {
    List<Regis>findByEndRegisAtBetween(Date from,Date to);
    List<Regis>findByEndRegisAtBefore(Date to);
    List<Regis>findByEndRegisAtAfter(Date from);

    @Query("SELECT e FROM Regis e WHERE e.startRegisAt <= :now AND e.endRegisAt >= :now")
    List<Regis> findEntitiesByDateRange(@Param("now") LocalDate now);
}
