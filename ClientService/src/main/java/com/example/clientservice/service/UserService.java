package com.example.clientservice.service;

import com.example.clientservice.model.User;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;

import java.util.List;

public interface UserService {



    public User getUser(int id);

    public List<User> getAllUsers();


    public User addUser(User user);

    public User updateUser(int id,User user);
    public String deleteUser(int id);

}
