package com.example.demo.repository;

import com.example.demo.model.User;
import com.example.demo.request.UserRequest;

import org.springframework.stereotype.Repository;

import java.sql.SQLException;
import java.util.List;

@Repository
public interface UserRepository {

    User findUserById(Long userId) throws SQLException;

    void addUser(UserRequest userRequest) throws SQLException;

    void deleteUser(Long userId) throws SQLException;

    void editUser(UserRequest userRequest) throws SQLException;

    List<User> searchUser(UserRequest userRequest) throws SQLException;

    void add5tr() throws SQLException;
}
