package com.example.demo.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class UserSearchRequest {

    private Long userId;

    private String name;

    private String role;

    private String address;

    private Long age;
}
