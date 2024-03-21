package com.springboot.architectural.service.imp;

import com.springboot.architectural.repository.ItemRepository;
import com.springboot.architectural.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ItemServiceImp implements ItemService {
    @Autowired
    private ItemRepository itemRepository;
}
