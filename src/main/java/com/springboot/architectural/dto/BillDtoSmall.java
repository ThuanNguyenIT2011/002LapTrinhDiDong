package com.springboot.architectural.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BillDtoSmall {
    private Integer id;
    private String title;
    private String content;
    private Boolean pay;
    private Integer roomId;
    private String username;

    public static BillDtoSmall BillDto2Small(BillDto billDto){
        try {
            BillDtoSmall billDtoSmall = new BillDtoSmall();
        billDtoSmall.setId(billDto.getId());
        billDtoSmall.setTitle(billDto.getTitle());
        billDtoSmall.setContent(billDto.getContent());
        billDtoSmall.setPay(billDto.getPay());
        if(billDto.getElectricWaterPrice() != null){
            billDtoSmall.setRoomId(billDto.getElectricWaterPrice().getRoomRegis().getRoom().getId());
        }
        else{
            billDtoSmall.setUsername(billDto.getBoardingHistory().getAccount().getUsername());
        }
        return billDtoSmall;
        } catch (Exception e) {
            System.out.println("\n\n\n" + e.getMessage() + "\n\n\n");
        }
        return null;
    }
}
