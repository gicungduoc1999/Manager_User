package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MainController {

    @GetMapping(value = "/contact/")
    public ResponseEntity<String> listAllContact() {
        return new ResponseEntity<String>("acc", HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
