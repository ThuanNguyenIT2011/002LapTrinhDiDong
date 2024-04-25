package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.RoomDtoRegis;
import com.springboot.architectural.entity.*;
import com.springboot.architectural.mapper.RoomDtoRegisMapper;
import com.springboot.architectural.mapper.RoomMapper;
import com.springboot.architectural.dto.RoomDto;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.repository.RegisRepository;
import com.springboot.architectural.repository.RoomRepository;
import com.springboot.architectural.service.FileService;
import com.springboot.architectural.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class RoomServiceImp implements RoomService {
    @Value("${fileUpload.rootPatch}")
    private String rootPatch;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private AccountRepository accountRepository;
    @Autowired
    private FileService fileService;

    @Autowired
    private RegisRepository regisRepository;

    @Override
    public RoomDto getRoomById(int id) {
        Optional<Room> room = roomRepository.findById(id);
        if (!room.isPresent()) return  null;
        return RoomMapper.INSTANCE.roomToRoomDto(room.get());
    }

    @Override
    public List<RoomDto> getAllRoom(String searchContent,String typeRoom, String disable, String typeSort) {
        String sortField = "createAt";
        boolean status = disable.equals("true") || disable.equals("1") ? true : false;
        Sort sorted = Sort.by(sortField);
        sorted = typeSort.equals("asc") ? sorted.ascending() : sorted.descending();
        List<Room> rooms = new ArrayList<>();
        if (disable == "") {
            rooms = roomRepository.findAllFilter(searchContent, typeRoom, sorted);
        }
        else {
            rooms = roomRepository.findFilterByDisable(status,typeRoom, searchContent, sorted);
        }
        return rooms.stream().map((room) ->  RoomMapper.INSTANCE.roomToRoomDto(room)).collect(Collectors.toList());
    }

    @Override
    public RoomDto addRoom(RoomDto room) {
        Room roomEntity = RoomMapper.INSTANCE.roomDtotoRoom(room);
        roomEntity.setAccount(accountRepository.findByUsername(room.getCreateBy()).get());
        return  RoomMapper.INSTANCE.roomToRoomDto(roomRepository.save(roomEntity));
    }

    @Override
    public RoomDto updateRoom(RoomDto room) {
        if (room.getId() == null) return null;
        Optional<Room> roomCheck = roomRepository.findById(room.getId());
        Optional<Account> accountCheck = accountRepository.findById(room.getCreateBy());
        if (!roomCheck.isPresent() ||  !accountCheck.isPresent()) return  null;
        Room roomEntity = RoomMapper.INSTANCE.roomDtotoRoom(room);
        roomEntity.setAccount(accountCheck.get());
        return  RoomMapper.INSTANCE.roomToRoomDto(roomRepository.save(roomEntity));
    }

    @Override
    public boolean deleteRoom(Integer id) {
        Optional<Room> r = roomRepository.findById(id);
        if (r.isPresent())
        {
            roomRepository.delete(r.get());
            return true;
        }
        return false;
    }

    @Override
    public boolean uploadImg(MultipartFile multipartFile, Integer room_id) {
        Optional<Room> room = roomRepository.findById(room_id);
        if (room.isEmpty()) return  false;
        String url =  rootPatch + "/" + multipartFile.getOriginalFilename();
        room.get().setImg(url);
        roomRepository.save(room.get());
        return fileService.save(multipartFile);
    }

    @Override
    public List<RoomDtoRegis> getAllRoomForRegister(String status, String searchContent, String type) {
        // Get info register
        //Date date = new Date();
        LocalDate localDate = LocalDate.now();
        List<Regis> regisList = regisRepository.findEntitiesByDateRange(localDate);
        List<Room> rooms = new ArrayList<>();
        List<RoomDtoRegis> roomDtoRegis = new ArrayList<>();

        if (regisList.size() > 0) {
            Regis regis = regisList.get(0);
            List<Integer> roomIdList = new ArrayList<>();

            regis.getRoomRegis().forEach(roomRegis -> {
                roomIdList.add(roomRegis.getRoom().getId());
                System.out.println(roomRegis.getRoom().getId());
            });

            //List<Room> rooms = roomRepository.findRangeId(roomIdList);

            if (status.length() == 0) {
                rooms = roomRepository.filterRoomBySearchAndType(type, searchContent, roomIdList);
            } else {
                boolean enable = status.equals("true") ? true : false;
                rooms = roomRepository.filterRoomByStatusAndSearchAndType(searchContent, enable, type, roomIdList);
            }

            List<RoomDto> roomDtos = new ArrayList<>();

            rooms.forEach(room -> {
                RoomDtoRegis toRoomDtoRegis = RoomDtoRegisMapper.mappToRoomDtoRegis(room);
                int count = 0;
                double priceElectric = 0;
                double priceWater = 0;

                for (RoomRegis roomRegis : regis.getRoomRegis()) {
                    if (roomRegis.getRoom().getId() == room.getId()) {
                        count = roomRegis.getCount();
                        break;
                    }
                }

                toRoomDtoRegis.setCount(count);
                toRoomDtoRegis.setPrice(Double.parseDouble(regis.getRoomPriceVND().toString()));


                roomDtoRegis.add(toRoomDtoRegis);
            });
        }

        return roomDtoRegis;
    }

    @Override
    public RoomDtoRegis getRoomByIdByRegister(Integer id) {
        LocalDate localDate = LocalDate.now();
        List<Regis> regisList = regisRepository.findEntitiesByDateRange(localDate);
        if (regisList.size() > 0) {
            Regis regis = regisList.get(0);
            for (RoomRegis roomRegis : regis.getRoomRegis()) {
                if (roomRegis.getRoom().getId() == id) {
                    int count = 0;
                    RoomDtoRegis toRoomDtoRegis = RoomDtoRegisMapper.mappToRoomDtoRegis(roomRegis.getRoom());
                    toRoomDtoRegis.setCount(roomRegis.getCount());
                    toRoomDtoRegis.setPrice(Double.parseDouble(regis.getRoomPriceVND().toString()));
                    return toRoomDtoRegis;
                }
            }
        }
        return null;
    }
}
