package com.erich.dev.userservice.dto;

import com.erich.dev.userservice.entity.User;

import lombok.Builder;
import lombok.Data;


@Data
@Builder
public class UserDto {

    private Long id;

    private String name;

    private String lastname;

    private Integer balance;


    public static UserDto fromEntity(User user) {
        if(user == null) {
            return null;
        }
        return UserDto.builder()
                .id(user.getId())
                .name(user.getName())
                .lastname(user.getLastname())
                .balance(user.getBalance())
                .build();
    }

    public static User toEntity(UserDto userDto) {
        if(userDto == null) {
            return null;
        }
        return User.builder()
                .id(userDto.getId())
                .name(userDto.getName())
                .lastname(userDto.getLastname())
                .balance(userDto.getBalance())
                .build();
    }
}
