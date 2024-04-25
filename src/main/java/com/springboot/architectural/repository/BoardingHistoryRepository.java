package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.BoardingHistory;
import com.springboot.architectural.entity.Regis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface BoardingHistoryRepository extends JpaRepository<BoardingHistory, Integer> {
    @Query("SELECT e FROM BoardingHistory e WHERE e.account = :account AND e.created >= :start AND e.created <= :end")
    List<BoardingHistory> findEntitiesByDateRange(@Param("start") Date start, @Param("end") Date end,
                                                  @Param("account") Account account);
}
