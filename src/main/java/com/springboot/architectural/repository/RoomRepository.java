package com.springboot.architectural.repository;

import com.springboot.architectural.entity.Room;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.time.LocalDate;
import java.util.List;

public interface RoomRepository extends JpaRepository<Room, Integer> {
    @Query("SELECT r FROM Room r WHERE (r.disable = ?1 and r.type = ?2) AND CONCAT(r.id, ' ', r.name) LIKE %?3%")
    public List<Room> findFilterByDisable(boolean disable, String type,String searchContent, Sort pageable);

//    @Query("SELECT r FROM Room r WHERE CONCAT(r.id, ' ', r.name) LIKE %?1% and r.type = ?2")
    @Query("SELECT r FROM Room r WHERE CONCAT(r.id, ' ', r.name) LIKE %?1%")
    public List<Room> findAllFilter(String searchContent,String type, Sort pageable);

    @Query("SELECT r FROM Room r WHERE r.id in ?1")
    public List<Room> findRangeId(List<Integer> listId);


    @Query("SELECT r FROM Room r WHERE (r.disable = ?1 and r.type = ?2 and r.id in ?4) AND CONCAT(r.id, ' ', r.name) LIKE %?3%")
    public List<Room> findFilterByDisableAndRangeID(boolean disable, String type,String searchContent, List<Integer> listId, Sort pageable);

    @Query("SELECT r FROM Room r WHERE (r.type = ?2 and r.id in ?3) and CONCAT(r.id, ' ', r.name) LIKE %?1%")
    public List<Room> findAllFilterAndRangeID(String searchContent,String type, List<Integer> listId, Sort pageable);


    @Query("SELECT r FROM Room r WHERE r.id in ?4 AND r.disable = ?2 AND CONCAT(r.id, ' ', r.name) LIKE %?1% AND r.type LIKE %?3%")
    public List<Room> filterRoomByStatusAndSearchAndType(String contentSearch, boolean status, String type, List<Integer> listId);

    @Query("SELECT r FROM Room r WHERE r.id in ?3 AND r.type LIKE %?1% AND  CONCAT(r.id, ' ', r.name) LIKE %?2%")
    public List<Room> filterRoomBySearchAndType(String type, String contentSearch, List<Integer> listId);
}
