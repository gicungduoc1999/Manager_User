package com.example.demo.controller;

import com.example.demo.request.UserRequest;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private UserService userService;

    @PutMapping(value = "/add-user")
    @Description(value = "add User")
    public ResponseEntity<String> addUser(@Valid @RequestBody UserRequest userRequest) throws SQLException {
        userService.addUser(userRequest);
        return new ResponseEntity<String>("add successes", HttpStatus.OK);
    }
}
