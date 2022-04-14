package com.example.demo.response;


import com.example.demo.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse {
    private Long id;

    private String name;

    private String role;

    private String address;

    private Long age;

    public static UserResponse toUserResponse(User user) {
        return UserResponse.builder().id(user.getId())
                .name(user.getName())
                .role(user.getRole())
                .address(user.getAddress())
                .age(user.getAge())
                .build();
    }
}
