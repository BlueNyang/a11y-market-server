package com.multicampus.gamesungcoding.a11ymarketserver.connect.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ConnectionController {
    @GetMapping("/connection/test")
    public String test(){
        return "test success";
    }
}
