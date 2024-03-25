package com.springboot.architectural.service.imp;

import com.springboot.architectural.dto.ItemDto;
import com.springboot.architectural.dto.NotifyDto;
import com.springboot.architectural.entity.Account;
import com.springboot.architectural.entity.Item;
import com.springboot.architectural.entity.Notify;
import com.springboot.architectural.exception.FileIsExistException;
import com.springboot.architectural.exception.ItemNotfoundException;
import com.springboot.architectural.mapper.ItemMapper;
import com.springboot.architectural.mapper.NotifyMapper;
import com.springboot.architectural.repository.ItemRepository;
import com.springboot.architectural.service.FileService;
import com.springboot.architectural.service.ItemService;
import org.hibernate.boot.model.source.spi.Sortable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ItemServiceImp implements ItemService {
    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private FileService fileService;


    @Override
    public boolean createItem(MultipartFile multipartFile, String name, int count, String username, boolean disable) {
        if (fileService.save(multipartFile)) {
            Item item = new Item();
            item.setAccount(new Account(username));
            item.setName(name);
            item.setCount(count);
            item.setDisable(disable);

            item.setCreateAt(new Date());

            item.setImg(multipartFile.getOriginalFilename());

            itemRepository.save(item);

            return true;
        }
        return false;
    }

    @Override
    public ItemDto getItemById(Integer id) throws ItemNotfoundException {
        Optional<Item> itemDb = itemRepository.findById(id);
        if (itemDb.isEmpty()) {
            throw new ItemNotfoundException("Item not found");
        }

        ItemDto itemDto = new ItemDto();
        itemDto = ItemMapper.mapToItemDto(itemDb.get(), itemDto);

        return itemDto;
    }

    @Override
    public List<ItemDto> getItems(String searchContent, String disable, String typeSort) {
        String sortField = "createAt";
        boolean status = disable.equals("true") ? true : false;
        Sort sorted = Sort.by(sortField);
        sorted = typeSort.equals("asc") ? sorted.ascending() : sorted.descending();

        List<Item> items = null;

        if (disable == "") {
            items = itemRepository.findAllFilter(searchContent, sorted);
        }
        else {
            items = itemRepository.findFilterByDisable(status, searchContent, sorted);
        }

        List<ItemDto> itemDtos = new ArrayList<>();

        items.forEach(item -> {
           ItemDto itemDto = new ItemDto();
            itemDtos.add(ItemMapper.mapToItemDto(item, itemDto));
        });

        return itemDtos;
    }

    @Override
    public boolean deleteItem(Integer id) throws ItemNotfoundException {
        Optional<Item> itemDb = itemRepository.findById(id);
        if (itemDb.isEmpty()) {
            throw new ItemNotfoundException("Item not found");
        }
        itemRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean updateItem(MultipartFile multipartFile, Integer id,  String name, int count, String username, boolean disable) throws ItemNotfoundException, FileIsExistException {
        Optional<Item> itemDb = itemRepository.findById(id);
        if (itemDb.isEmpty()) {
            throw new ItemNotfoundException("Item not found");
        }

        Item item = itemDb.get();

        if (item.getImg() != multipartFile.getOriginalFilename()) {
            item.setImg(multipartFile.getOriginalFilename());
            if (!fileService.save(multipartFile)) {
                throw new FileIsExistException("Name file is exist");
            }
        }
        item.setAccount(new Account(username));
        item.setName(name);
        item.setCount(count);
        item.setDisable(disable);

        itemRepository.save(item);
        return true;
    }
}
