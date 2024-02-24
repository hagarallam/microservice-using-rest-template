package com.example.clientservice.service;

import com.example.clientservice.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("restUsingObject")
public class UserServiceUsingObject implements UserService{

    private RestTemplate restTemplate;

    @Value("${base-url}")
    private String baseUrl ;

    public UserServiceUsingObject() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public User getUser(int id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        User user = restTemplate.getForObject(baseUrl+"/"+id, User.class);
        return user;
    }

    @Override
    public List<User> getAllUsers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        List<User> userList = restTemplate.getForObject(baseUrl, List.class);
        return userList;
    }

    @Override
    public User addUser(User user) {
        user = restTemplate.postForObject(baseUrl,user, User.class);
        return user;
    }

    @Override
    public User updateUser(int id, User user) {
        restTemplate.put(baseUrl+"/"+id,user);
        // To return a value
        user.setId(id);
        return user;
    }

    @Override
    public String deleteUser(int id) {
        restTemplate.delete(baseUrl+"/"+id);
        return "Done from Client Side !";
    }
}
