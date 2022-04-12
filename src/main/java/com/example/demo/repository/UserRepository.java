package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.request.UserRequest;
import org.springframework.stereotype.Repository;

import java.sql.SQLException;

@Repository
public interface UserRepository  {

     public User findUserById(Long userId) throws SQLException;

     public void addUser(UserRequest userRequest);
}
