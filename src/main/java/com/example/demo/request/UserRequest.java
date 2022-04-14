package com.example.demo.request;

import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@RequiredArgsConstructor
public class UserRequest {

    private Long userId;

    private String name;

    private String role;

    private String address;

    private Long age;

    /*
     * 1 : start with H
     * 2 : contain H
     * 3 : search name
     */
    private int operation;
}
