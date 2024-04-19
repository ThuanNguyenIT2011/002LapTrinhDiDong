package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.ItemDto;
import com.springboot.architectural.dto.NotifyDto;
import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Item;
import com.springboot.architectural.entity.Notify;
import com.springboot.architectural.exception.NotifyNotfoundException;
import com.springboot.architectural.mapper.ItemMapper;
import com.springboot.architectural.mapper.NotifyMapper;
import com.springboot.architectural.mapper.NotifyRequestMapper;
import com.springboot.architectural.payload.Request.NotifyRequest;
import com.springboot.architectural.repository.NotifyRepository;
import com.springboot.architectural.service.NotifyService;
import org.aspectj.weaver.ast.Not;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class NotifyServiceImp implements NotifyService {
    @Autowired
    private NotifyRepository notifyRepository;

    @Override
    public void saveNotify(NotifyRequest notifyRequest) {
        Notify notify = new Notify();
        notify = NotifyRequestMapper.mappToNotify(notifyRequest, notify);
        if (notify.getId() == null) {
            notify.setCreateAt(new Date());
        }
        notifyRepository.save(notify);
    }

    @Override
    public boolean deleteNotify(Integer id) throws NotifyNotfoundException {
        Optional<Notify> notify = notifyRepository.findById(id);
        if (notify.isEmpty()) {
            throw new NotifyNotfoundException("Notify not found");
        }
        notifyRepository.delete(notify.get());
        return true;
    }

    @Override
    public List<NotifyDto> getAllNotify(String searchContent, String disable, String typeSort) {

        String sortField = "createAt";
        boolean status = disable.equals("true") ? true : false;
        Sort sorted = Sort.by(sortField);
        sorted = typeSort.equals("asc") ? sorted.ascending() : sorted.descending();

        List<Notify> notifies =null;

        notifies = notifyRepository.findAllFilter(searchContent, sorted);

        List<NotifyDto> notifyDtos = new ArrayList<>();

        notifies.forEach(notify -> {
            NotifyDto notifyDto = new NotifyDto();
            notifyDtos.add(NotifyMapper.mapToNotifyDto(notify, notifyDto));
        });

        return notifyDtos;
    }

    @Override
    public NotifyDto getNotifyById(Integer id) throws NotifyNotfoundException {
        Optional<Notify> notify = notifyRepository.findById(id);
        if (notify.isEmpty()) {
            throw new NotifyNotfoundException("Notify not found");
        }
        NotifyDto notifyDto = new NotifyDto();
        return NotifyMapper.mapToNotifyDto(notify.get(), notifyDto);
    }

    @Override
    public boolean updateNotify(NotifyRequest notifyRequest) throws NotifyNotfoundException {
        Optional<Notify> notifyOptional = notifyRepository.findById(notifyRequest.getId());

        if (notifyOptional.isEmpty()) {
            throw new NotifyNotfoundException("Notify not found");
        }

        Notify notifyDB = notifyOptional.get();

        notifyDB.setContent(notifyRequest.getContent());
        notifyDB.setTitle(notifyRequest.getTitle());

        notifyRepository.save(notifyDB);

        return true;
    }

    @Override
    public List<NotifyDto> getNotifyByUsername(String username) {
        Account account = new Account();
        account.setUsername(username);
        List<Notify> notifies = notifyRepository.findByAccount(account);

        List<NotifyDto> notifyDtos = new ArrayList<>();

        notifies.forEach(notify -> {
            NotifyDto notifyDto = new NotifyDto();
            notifyDtos.add(NotifyMapper.mapToNotifyDto(notify, notifyDto));
        });

        return notifyDtos;
    }


}
