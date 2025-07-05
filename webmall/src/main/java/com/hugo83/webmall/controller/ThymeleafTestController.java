package com.hugo83.webmall.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.GetMapping;



@Controller
@RequestMapping(value="/thymeleaf")
public class ThymeleafTestController {

    @GetMapping("/test01")
    public String getThymeleaftTest01(Model model) {
        model.addAttribute("data", "백엔드에서 타임리프로~");

        return "thymeleafs/test01";
    }
    
}
