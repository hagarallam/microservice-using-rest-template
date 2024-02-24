package com.example.clientservice.service;

import com.example.clientservice.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("restUsingEntity")
public class UserServiceImplUsingEntity implements UserService{
    private RestTemplate restTemplate;

    @Value("${base-url}")
    private String baseUrl ;

    public UserServiceImplUsingEntity() {
        this.restTemplate = new RestTemplate();
    }

    @Override
    public User getUser(int id) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> userHttpEntity = restTemplate.getForEntity(baseUrl+"/"+id, User.class);
        return userHttpEntity.getBody();
    }

    @Override
    public List<User> getAllUsers() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List> userHttpEntity = restTemplate.getForEntity(baseUrl, List.class);
        return (List<User>) userHttpEntity.getBody();
    }

    @Override
    public User addUser(User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user,httpHeaders);
        userHttpEntity = restTemplate.postForEntity(baseUrl,userHttpEntity, User.class);
        return userHttpEntity.getBody();
    }

    @Override
    public User updateUser(int id, User user) {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user,httpHeaders);
        restTemplate.put(baseUrl+"/"+id,userHttpEntity);
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
