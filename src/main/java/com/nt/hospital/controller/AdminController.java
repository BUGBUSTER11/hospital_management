package com.nt.hospital.controller;

import ch.qos.logback.core.CoreConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdminController {

    @PostMapping("/adminLogin")
    public String adminLogin(){
        return "index";
    }
}
