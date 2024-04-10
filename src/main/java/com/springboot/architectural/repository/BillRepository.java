package com.springboot.architectural.repository;

import com.springboot.architectural.dto.ChartDto;
import com.springboot.architectural.entity.Bill;
import com.springboot.architectural.entity.ElectricWaterPrice;
import com.springboot.architectural.entity.Regis;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;
import java.util.Optional;

public interface BillRepository extends JpaRepository<Bill, Integer> {
    List<Bill> findByCreateAtBetween(Date from, Date to);
    List<Bill>findByCreateAtBefore(Date to);
    List<Bill>findByCreateAtAfter(Date from);
    @Query("SELECT r FROM Bill r WHERE r.electricWaterPrice.id = ?1")
    public Optional<Bill> findByEWPId(int electricWaterPriceId);
    @Query("SELECT r FROM Bill r WHERE r.boardingHistory.account.username = ?1")
    public List<Bill> findAllByStudent(String studentId);
    @Query("SELECT r FROM Bill r WHERE r.electricWaterPrice.roomRegis.room.id = ?1")
    public List<Bill> findAllByRoomId(Integer roomId);
    @Query("SELECT r FROM Bill r WHERE r.electricWaterPrice.id is null")
    public List<Bill> findAllByRoom();
    @Query("SELECT r FROM Bill r WHERE r.boardingHistory.id is null")
    public List<Bill> findAllByElectricWater();


//    @Query(value = "select count(BoardingHistory.student_id) as sum, Room.name\n" +
//            "from Bill,BoardingHistory,RoomRegis,Room\n" +
//            "where Bill.pay = 1 and \n" +
//            "\t  Bill.create_at >= ?1 and Bill.create_at <= ?2 and\n" +
//            "\t  BoardingHistory.id = Bill.boarding_id and\n" +
//            "\t  BoardingHistory.room_regis_id = RoomRegis.id and\n" +
//            "\t  RoomRegis.room_id = Room.id and\n" +
//            "\t  Room.type like '%?3%'\n" +
//            "group by  Room.name\n" +
//            "order by Room.name", nativeQuery = true)
//    public List<ChartDto> summaryBoardingPrice(Date from, Date to,String typeRoom );
//
//    @Query(value = "select sum(Bill.price) as sum, Room.name\n" +
//            "from Bill,ElectricWaterPrice,RoomRegis,Room\n" +
//            "where Bill.pay = 1 and \n" +
//            "\t  Bill.create_at >= ?1 and Bill.create_at <= ?2 and\n" +
//            "\t  ElectricWaterPrice.id = Bill.boarding_id and\n" +
//            "\t  ElectricWaterPrice.room_regis_id = RoomRegis.id and\n" +
//            "\t  RoomRegis.room_id = Room.id and\n" +
//            "\t  Room.type like %?3%\n" +
//            "group by  Room.name\n" +
//            "order by Room.name", nativeQuery = true)
//    public List<ChartDto> summaryElectricWaterPrice(Date from, Date to,String typeRoom);
//
//    @Query(value = "select count(BoardingHistory.student_id) as sum, Room.name\n" +
//            "from Bill,BoardingHistory,RoomRegis,Room\n" +
//            "where Bill.pay = 1 and \n" +
//            "\t  Bill.create_at <= ?1 and Bill.create_at >= ?2 and\n" +
//            "\t  BoardingHistory.id = Bill.boarding_id and\n" +
//            "\t  BoardingHistory.room_regis_id = RoomRegis.id and\n" +
//            "\t  RoomRegis.room_id = Room.id and\n" +
//            "\t  Room.type like %?3%\n" +
//            "group by  Room.name\n" +
//            "order by Room.name", nativeQuery = true)
//    public List<ChartDto> summaryStudent(Date from, Date to,String typeRoom );

}
