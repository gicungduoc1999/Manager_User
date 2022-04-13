package com.example.demo.service;

import com.example.demo.model.User;
import com.example.demo.request.UserRequest;
import com.example.demo.request.UserSearchRequest;

import java.sql.SQLException;
import java.util.List;

/*
 * @author: mrd
 * @description: manager user
 */
public interface UserService {

    /*
     * @param: UserRequest
     * @description: add new user
     */
    void addUser(UserRequest userRequest) throws SQLException;

    /*
     * @param: userId
     * @description: delete new user
     */
    void deleteUser(Long userId) throws SQLException;

    /*
     * @param: UserRequest
     * @description: edit new user
     */
    void editUser(UserRequest userRequest) throws SQLException;

    /*
     * @param: UserSearchRequest
     * @description: search user
     */
    List<User> searchUser(UserSearchRequest userSearchRequest) throws SQLException;

    /*
     * @description: add 5tr user
     */
    void add5tr() throws SQLException;
}
