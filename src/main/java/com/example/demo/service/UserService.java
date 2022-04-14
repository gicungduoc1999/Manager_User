package com.example.demo.service;


import com.example.demo.request.UserRequest;
import com.example.demo.response.EntityCustomResponse;


import java.sql.SQLException;


/*
 * @author: mrd
 * @description: manager user
 */
public interface UserService {

    /*
     * @param: UserRequest
     * @description: add new user
     */
    EntityCustomResponse addUser(UserRequest userRequest);

    /*
     * @param: userId
     * @description: delete new user
     */
    EntityCustomResponse deleteUser(Long userId);

    /*
     * @param: UserRequest
     * @description: edit new user
     */
    EntityCustomResponse editUser(UserRequest userRequest);

    /*
     * @param: UserSearchRequest
     * @description: search user
     */
    EntityCustomResponse searchUser(UserRequest userRequest);

    /*
     * @description: add 5tr user
     */
    EntityCustomResponse add5tr();
}
