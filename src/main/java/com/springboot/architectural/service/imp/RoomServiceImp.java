package com.springboot.architectural.service.imp;

import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Item;
import com.springboot.architectural.entity.Room;
import com.springboot.architectural.mapper.RoomMapper;
import com.springboot.architectural.dto.RoomDto;
import com.springboot.architectural.repository.AccountRepository;
import com.springboot.architectural.repository.RoomRepository;
import com.springboot.architectural.service.FileService;
import com.springboot.architectural.service.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
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
    @Override
    public RoomDto getRoomById(int id) {
        Optional<Room> room = roomRepository.findById(id);
        if (!room.isPresent()) return  null;
        return RoomMapper.INSTANCE.roomToRoomDto(room.get());
    }

    @Override
    public List<RoomDto> getAllRoom(String searchContent, String disable, String typeSort) {
        String sortField = "createAt";
        boolean status = disable.equals("true") || disable.equals("1") ? true : false;
        Sort sorted = Sort.by(sortField);
        sorted = typeSort.equals("asc") ? sorted.ascending() : sorted.descending();
        List<Room> rooms = new ArrayList<>();
        if (disable == "") {
            rooms = roomRepository.findAllFilter(searchContent, sorted);
        }
        else {
            rooms = roomRepository.findFilterByDisable(status, searchContent, sorted);
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
}
