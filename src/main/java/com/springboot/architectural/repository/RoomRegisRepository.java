package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Regis;
import com.springboot.architectural.entity.RoomRegis;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface RoomRegisRepository extends JpaRepository<RoomRegis, Integer> {

}
