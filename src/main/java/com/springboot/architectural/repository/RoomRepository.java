package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Room;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT r FROM Room r WHERE r.disable = ?1 AND CONCAT(r.id, ' ', r.name) LIKE %?2%")
    public List<Room> findFilterByDisable(boolean disable, String searchContent, Sort pageable);

    @Query("SELECT r FROM Room r WHERE CONCAT(r.id, ' ', r.name) LIKE %?1%")
    public List<Room> findAllFilter(String searchContent, Sort pageable);
}
