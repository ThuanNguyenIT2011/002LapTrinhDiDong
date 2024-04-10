package com.springboot.architectural.repository;

import com.springboot.architectural.dto.ChartDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Date;
import java.util.List;

public interface ChartRepository extends  JpaRepository<ChartDto, String>{
    @Query(value = "select sum(Bill.price) as sum, Room.name\n" +
            "from Bill,BoardingHistory,RoomRegis,Room\n" +
            "where Bill.pay = 1 and \n" +
            "\t  Bill.create_at >= ?1 and Bill.create_at <= ?2 and\n" +
            "\t  BoardingHistory.id = Bill.boarding_id and\n" +
            "\t  BoardingHistory.room_regis_id = RoomRegis.id and\n" +
            "\t  RoomRegis.room_id = Room.id and\n" +
            "\t  Room.type like %?3%\n" +
            "group by  Room.name\n" +
            "order by Room.name", nativeQuery = true)
    public List<ChartDto> summaryBoardingPrice(Date from, Date to, String typeRoom );

    @Query(value = "select sum(Bill.price) as sum, Room.name\n" +
            "from Bill,ElectricWaterPrice,RoomRegis,Room\n" +
            "where Bill.pay = 1 and \n" +
            "\t  Bill.create_at >= ?1 and Bill.create_at <= ?2 and\n" +
            "\t  ElectricWaterPrice.id = Bill.boarding_id and\n" +
            "\t  ElectricWaterPrice.room_regis_id = RoomRegis.id and\n" +
            "\t  RoomRegis.room_id = Room.id and\n" +
            "\t  Room.type like %?3%\n" +
            "group by  Room.name\n" +
            "order by Room.name", nativeQuery = true)
    public List<ChartDto> summaryElectricWaterPrice(Date from, Date to,String typeRoom);

    @Query(value = "select count(BoardingHistory.student_id) as sum, Room.name\n" +
            "from Bill,BoardingHistory,RoomRegis,Room\n" +
            "where Bill.pay = 1 and \n" +
            "\t  Bill.create_at >= ?1 and Bill.create_at <= ?2 and\n" +
            "\t  BoardingHistory.id = Bill.boarding_id and\n" +
            "\t  BoardingHistory.room_regis_id = RoomRegis.id and\n" +
            "\t  RoomRegis.room_id = Room.id and\n" +
            "\t  Room.type like %?3%\n" +
            "group by  Room.name\n" +
            "order by Room.name", nativeQuery = true)
    public List<ChartDto> summaryStudent(Date from, Date to,String typeRoom );
}
