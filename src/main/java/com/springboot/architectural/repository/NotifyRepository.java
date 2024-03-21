package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Notify;
import org.springframework.data.jpa.repository.JpaRepository;

public interface NotifyRepository extends JpaRepository<Notify, Integer> {
}
