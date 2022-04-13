package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.request.UserRequest;
import com.example.demo.request.UserSearchRequest;
import com.example.demo.response.EntityCustomResponse;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Description;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController {

    @Autowired
    private UserService userService;

    @PutMapping(value = "/add-user")
    @Description(value = "add User")
    public ResponseEntity<EntityCustomResponse> addUser(@Valid @RequestBody UserRequest userRequest) {
        try {
            userService.addUser(userRequest);
        } catch (SQLException E) {
            return ResponseEntity.ok(new EntityCustomResponse(1, "user exist", 500, null));
        }
        return ResponseEntity.ok(new EntityCustomResponse(1, "add success", 200, null));
    }

    @PostMapping(value = "/delete-user")
    @Description(value = "delete User")
    public ResponseEntity<EntityCustomResponse> deleteUser(@Valid Long userId) {
        try {
            userService.deleteUser(userId);
        } catch (SQLException E) {
            return ResponseEntity.ok(new EntityCustomResponse(1, "user not found", 404, null));
        }
        return ResponseEntity.ok(new EntityCustomResponse(1, "delete successes", 200, null));
    }

    @PutMapping(value = "/edit-user")
    @Description(value = "edit User")
    public ResponseEntity<EntityCustomResponse> editUser(@Valid @RequestBody UserRequest userRequest) {
        try {
            userService.editUser(userRequest);
        } catch (SQLException E) {
            return ResponseEntity.ok(new EntityCustomResponse(1, "user not found", 404, null));
        }
        return ResponseEntity.ok(new EntityCustomResponse(1, "edit successes", 200, null));
    }

    @GetMapping(value = "/search-user")
    @Description(value = "search User")
    public ResponseEntity<EntityCustomResponse> searchUser(@Valid @RequestBody UserSearchRequest userSearchRequest) {
        List<User> users = null;
        try {
            users= userService.searchUser(userSearchRequest);
        } catch (SQLException E) {
            return ResponseEntity.ok(new EntityCustomResponse(0, "Error system", 500, null));
        }
        return ResponseEntity.ok(new EntityCustomResponse(1, "user information", 200, Collections.singletonList(users)));
    }

    @PostMapping(value = "/add-5tr")
    @Description(value = "add random 5.000.000 user")
    public ResponseEntity<EntityCustomResponse> add5tr() {
        try {
            userService.add5tr();
        } catch (SQLException E) {
            return ResponseEntity.ok(new EntityCustomResponse(1, "Error system ", 404, null));
        }
        return ResponseEntity.ok(new EntityCustomResponse(1, "add 5000000 successes", 200, null));
    }
}
