package com.didier.carmanagementsystem.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class tryy {

    @GetMapping("/trryy")
    public String trryy() {
        return "trryy";
    }

}
