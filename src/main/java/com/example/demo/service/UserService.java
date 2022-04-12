package com.example.demo.service;

import com.example.demo.request.UserRequest;

/*
 * @author: mrd
 * @description: manager user
 */
public interface UserService {

    /*
     * @param: userId
     * @description: add new user
     */
    public void addUser(UserRequest userRequest);
}
