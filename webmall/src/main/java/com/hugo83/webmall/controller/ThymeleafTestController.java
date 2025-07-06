package com.hugo83.webmall.controller;

import java.time.LocalDateTime;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hugo83.webmall.dto.ItemDto;
import com.hugo83.webmall.entity.ItemSellStatus;

import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping(value="/thymeleaf")
public class ThymeleafTestController {

    @GetMapping("/test01")
    public String getThymeleafTest01(Model model) {
        model.addAttribute("data", "백엔드에서 타임리프로~");

        return "thymeleafs/test01";
    }

    @GetMapping("/test02")
    public String getThymeleafTest02(Model model) {
        ItemDto itemDto = ItemDto.builder()
                                 .itemName("타임리프 테스트상품1")
                                 .itemDetail("상품상세 설명")
                                 .price(100000)
                                 .createdAt(LocalDateTime.now())
                                 .stockQuantity(10)
                                 .itemSellStatus(ItemSellStatus.SELL)
                                 .build();
        
        model.addAttribute("itemDto", itemDto);
        return "thymeleafs/test02";
    }
    
}
