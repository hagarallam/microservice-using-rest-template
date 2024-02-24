package com.example.usersservice.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;;
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserDto extends ApiResponseDto  {
    private Integer id;
    private String name ;
    private String age ;

    public UserDto(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
