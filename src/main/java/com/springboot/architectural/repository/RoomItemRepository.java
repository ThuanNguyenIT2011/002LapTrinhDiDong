package com.springboot.architectural.repository;

import com.springboot.architectural.entity.RoomItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomItemRepository extends JpaRepository<RoomItem, Integer> {
}
