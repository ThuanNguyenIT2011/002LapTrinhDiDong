package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Bill;
import com.springboot.architectural.entity.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Integer> {
}
