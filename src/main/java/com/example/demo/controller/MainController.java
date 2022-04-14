package com.example.demo.controller;


import com.example.demo.request.UserRequest;
import com.example.demo.response.EntityCustomResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private UserService userService;

    @PutMapping(value = "/add-user")
    @Description(value = "add User")
    public ResponseEntity<EntityCustomResponse> addUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.addUser(userRequest));
    }

    @PostMapping(value = "/delete-user")
    @Description(value = "delete User")
    public ResponseEntity<EntityCustomResponse> deleteUser(@Valid Long userId) {
        return ResponseEntity.ok(userService.deleteUser(userId));
    }

    @PutMapping(value = "/edit-user")
    @Description(value = "edit User")
    public ResponseEntity<EntityCustomResponse> editUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.editUser(userRequest));
    }

    @GetMapping(value = "/search-user")
    @Description(value = "search User")
    public ResponseEntity<EntityCustomResponse> searchUser(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.searchUser(userRequest));
    }

    @PostMapping(value = "/add-5tr")
    @Description(value = "add random 5.000.000 user")
    public ResponseEntity<EntityCustomResponse> add5tr() {
        return ResponseEntity.ok(userService.add5tr());
    }

    @GetMapping(value = "/search-user-by-name")
    @Description(value = "search User by name")
    public ResponseEntity<EntityCustomResponse> searchUserByName(@RequestBody UserRequest userRequest) {
        return ResponseEntity.ok(userService.searchUserByName(userRequest));
    }
}
