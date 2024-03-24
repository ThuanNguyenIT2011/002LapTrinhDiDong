package com.springboot.architectural.controller;


import com.springboot.architectural.dto.ItemDto;
import com.springboot.architectural.dto.NotifyDto;
import com.springboot.architectural.exception.FileIsExistException;
import com.springboot.architectural.exception.ItemNotfoundException;
import com.springboot.architectural.exception.NotifyNotfoundException;
import com.springboot.architectural.payload.ResponseData;
import com.springboot.architectural.service.FileService;
import com.springboot.architectural.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.function.EntityResponse;

import javax.swing.text.html.parser.Entity;
import java.util.List;

@RestController
@RequestMapping("/api/item")
public class ItemController {
    @Autowired
    private ItemService itemService;

    @Autowired
    private FileService fileService;

    @PostMapping()
    public ResponseEntity<?> createItem(@RequestParam(name = "fileUpload")MultipartFile multipartFile,
                                        @RequestParam String name,
                                        @RequestParam int count,
                                        @RequestParam String username,
                                        @RequestParam boolean disable) {
        ResponseData responseData = new ResponseData();
        if (itemService.createItem(multipartFile, name, count, username, disable)) {
            responseData.setData(true);
        } else {
            responseData.setData(false);
            responseData.setDesc("File is exist");
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getItemById(@PathVariable(name = "id") Integer id) {
        ResponseData responseData = new ResponseData();
        try {
            ItemDto itemDto =  itemService.getItemById(id);
            responseData.setData(itemDto);
        } catch (ItemNotfoundException e) {
            responseData.setData(false);
            responseData.setSuccess(false);
            responseData.setDesc(e.getMessage());
        }
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }


    @GetMapping("/files/{filename:.+}")
    public ResponseEntity<?>  getFile(@PathVariable(name = "filename") String fileName){
        Resource resource = fileService.loadFile(fileName);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + resource.getFilename() + "\"").body(resource);
    }


    @PutMapping()
    public ResponseEntity<?> updateItem(@RequestParam(name = "fileUpload")MultipartFile multipartFile,
                                        @RequestParam Integer id,
                                        @RequestParam String name,
                                        @RequestParam int count,
                                        @RequestParam String username,
                                        @RequestParam boolean disable) {
        ResponseData responseData = new ResponseData();
        try {
            if (itemService.updateItem(multipartFile, id, name, count, username, disable)) {
                responseData.setData(true);
            }
        } catch (ItemNotfoundException | FileIsExistException e) {
            responseData.setData(false);
            responseData.setSuccess(false);
            responseData.setDesc(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteItem(@PathVariable(name = "id") Integer id){
        ResponseData responseData = new ResponseData();

        try {
            itemService.deleteItem(id);
            responseData.setData(true);

        } catch (ItemNotfoundException e) {
            responseData.setSuccess(false);
            responseData.setData(false);
            responseData.setDesc(e.getMessage());
        }

        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

    @GetMapping("/all")
    public  ResponseEntity<?> getItems(@RequestParam(defaultValue = "") String disable,
                                       @RequestParam(defaultValue = "DESC") String typeSort,
                                       @RequestParam(defaultValue = "") String searchContent){
        System.out.println(disable);
        ResponseData responseData = new ResponseData();
        List<ItemDto> itemDtos = itemService.getItems(searchContent, disable, typeSort);
        responseData.setData(itemDtos);
        return ResponseEntity.status(HttpStatus.OK).body(responseData);
    }

}
