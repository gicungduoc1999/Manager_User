package com.example.demo.service;


import com.example.demo.request.UserRequest;
import com.example.demo.response.EntityCustomResponse;


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

    /*
     * @param: UserSearchRequest
     * @description: search user by name
     */
    EntityCustomResponse searchUserByName(UserRequest userRequest);

    /*
     * @param: Long
     * @description: add money for users
     */
    EntityCustomResponse addMoney(Long id, Long numberMoney);

    /*
     * @param: Long
     * @description: transfer money for users
     */
    EntityCustomResponse transferMoney(Long userIdA, Long userIdB, Long numberMoney);
}
