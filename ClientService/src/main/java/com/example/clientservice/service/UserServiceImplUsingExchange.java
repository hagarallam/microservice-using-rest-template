package com.example.clientservice.service;

import com.example.clientservice.model.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service("restUsingExchange")
public class UserServiceImplUsingExchange implements UserService{
    private RestTemplate restTemplate;

    @Value("${base-url}")
    private String baseUrl ;

    public UserServiceImplUsingExchange(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    public User getUser(int id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> userHttpEntity = new HttpEntity<>(httpHeaders);
        userHttpEntity = restTemplate.exchange(
                baseUrl + "/" +id,
                HttpMethod.GET,
                userHttpEntity,
                User.class);
        return userHttpEntity.getBody();
    }

    public List<User> getAllUsers(){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<List<User>> userHttpEntity = new HttpEntity<>(httpHeaders);
        userHttpEntity = restTemplate.exchange(
                baseUrl,
                HttpMethod.GET,
                userHttpEntity,
                new ParameterizedTypeReference<List<User>>() {});
        return userHttpEntity.getBody();
    }


    public User addUser(User user){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user,httpHeaders);
        userHttpEntity = restTemplate.exchange(
                baseUrl,
                HttpMethod.POST,
                userHttpEntity,
                User.class);
        return userHttpEntity.getBody();
    }

    public User updateUser(int id,User user){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<User> userHttpEntity = new HttpEntity<>(user,httpHeaders);
        userHttpEntity = restTemplate.exchange(
                baseUrl + "/" +id,
                HttpMethod.PUT,
                userHttpEntity,
                User.class);
        return userHttpEntity.getBody();
    }
    public String deleteUser(int id){
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> userHttpEntity = new HttpEntity<>(httpHeaders);
        userHttpEntity = restTemplate.exchange(
                baseUrl + "/" +id,
                HttpMethod.DELETE,
                userHttpEntity,
                String.class);
        return userHttpEntity.getBody();
    }
}
