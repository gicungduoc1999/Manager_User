package com.example.demo.request;

import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;


@Data
@RequiredArgsConstructor
public class UserRequest {

    private Long userId;

    @NotBlank(message = "Name is mandatory")
    private String name;

    private String role;

    @NotBlank(message = "Address is mandatory")
    private String address;

    @Min(value = 1, message = "Age must be greater than 1 and less than 100")
    @Max(value = 100, message = "Age must be greater than 1 and less than 100")
    private Long age;
}
