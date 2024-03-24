package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Item;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ItemRepository extends JpaRepository<Item, Integer> {

    @Query("SELECT i FROM Item i WHERE i.disable = ?1 AND CONCAT(i.id, ' ', i.name) LIKE %?2%")
    public List<Item> findFilterByDisable(boolean disable, String searchContent, Sort pageable);

    @Query("SELECT i FROM Item i WHERE CONCAT(i.id, ' ', i.name) LIKE %?1%")
    public List<Item> findAllFilter(String searchContent, Sort pageable);
}
