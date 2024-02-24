package com.example.clientservice.cotroller;

import com.example.clientservice.model.User;
import com.example.clientservice.service.UserService;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/client-service")
public class ClientController {


    private UserService userService;

    public ClientController(@Qualifier("restUsingObject") UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<User> getUser(@PathVariable int id){
        User user;
        user = userService.getUser(id);
        return new ResponseEntity<>(user, OK);
    }



    @GetMapping("")
    public ResponseEntity<List<User>> getAllUser(){
        List<User> userList;
        userList= userService.getAllUsers();
        return new ResponseEntity<>(userList, OK);
    }
    @PostMapping("")
    public ResponseEntity<User> addUser(@RequestBody User user){
        if(Objects.nonNull(user)){
            User newUserDto = userService.addUser(user);
            return new ResponseEntity<>(newUserDto,OK);
        }
        return new ResponseEntity<>(null,BAD_REQUEST);
    }


    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable int id,@RequestBody User user){
        User newUserDto = userService.updateUser(id,user);
        return new ResponseEntity<>(newUserDto,OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
        String response = userService.deleteUser(id);
        return new ResponseEntity<>(response,OK);
    }
}
