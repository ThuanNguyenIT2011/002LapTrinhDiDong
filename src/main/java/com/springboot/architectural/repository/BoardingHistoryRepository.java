package com.springboot.architectural.repository;

import com.springboot.architectural.entity.BoardingHistory;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BoardingHistoryRepository extends JpaRepository<BoardingHistory, Integer> {
}
