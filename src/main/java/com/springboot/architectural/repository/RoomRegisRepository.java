package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Regis;
import com.springboot.architectural.entity.Room;
import com.springboot.architectural.entity.RoomRegis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

@Repository
public interface RoomRegisRepository extends JpaRepository<RoomRegis, Integer> {


}
