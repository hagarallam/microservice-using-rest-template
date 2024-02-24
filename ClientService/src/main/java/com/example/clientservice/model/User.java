package com.example.clientservice.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class User {


    private Integer id ;
    private String name;
    private String age ;


    public User(String name, String age) {
        this.name = name;
        this.age = age;
    }
}
