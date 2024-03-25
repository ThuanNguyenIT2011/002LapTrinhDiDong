package com.springboot.architectural.service;

import com.springboot.architectural.dto.ItemDto;
import com.springboot.architectural.exception.FileIsExistException;
import com.springboot.architectural.exception.ItemNotfoundException;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ItemService {
    public boolean createItem(MultipartFile multipartFile, String name, int count, String username, boolean disable);

    public ItemDto getItemById(Integer id) throws ItemNotfoundException;
    public List<ItemDto> getItems(String searchContent, String disable, String typeSort);

    public boolean deleteItem(Integer id) throws ItemNotfoundException;

    public boolean updateItem(MultipartFile multipartFile, Integer id, String name, int count, String username, boolean disable) throws ItemNotfoundException, FileIsExistException;

}
