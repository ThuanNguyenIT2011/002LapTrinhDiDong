package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Item;
import com.springboot.architectural.entity.Notify;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface NotifyRepository extends JpaRepository<Notify, Integer> {
    public List<Notify> findByAccount(Account account);


//    @Query("SELECT i FROM Notify i WHERE i.disable = ?1 AND CONCAT(i.id, ' ', i.title, ' ', i.content) LIKE %?2%")
//    public List<Notify> findFilterByDisable(boolean disable, String searchContent, Sort pageable);

    @Query("SELECT i FROM Notify i WHERE CONCAT(i.id, ' ', i.title, ' ', i.content) LIKE %?1%")
    public List<Notify> findAllFilter(String searchContent, Sort pageable);
}
