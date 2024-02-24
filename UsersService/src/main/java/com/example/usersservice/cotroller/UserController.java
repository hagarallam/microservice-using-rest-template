package com.example.usersservice.cotroller;

import com.example.usersservice.dto.UserDto;
import com.example.usersservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Objects;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.OK;


@RestController
@RequestMapping("/users-service")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    @GetMapping("/{id}")
    public ResponseEntity<UserDto> getUser(@PathVariable int id){
        UserDto userDto;
        userDto = userService.getUser(id);
        return new ResponseEntity<>(userDto, OK);
    }


    @GetMapping("")
    public ResponseEntity<List<UserDto>> getAllUser(){
        List<UserDto> userDtoList;
        userDtoList= userService.getAllUsers();
        return new ResponseEntity<>(userDtoList, OK);
    }
    @PostMapping("")
    public ResponseEntity<UserDto> addUser(@RequestBody UserDto userDto){
        if(Objects.nonNull(userDto)){
            UserDto newUserDto = userService.addUser(userDto);
            return new ResponseEntity<>(newUserDto,OK);
        }
        return new ResponseEntity<>(null,BAD_REQUEST);
    }


    @PutMapping("/{id}")
    public ResponseEntity<UserDto> updateUser(@PathVariable int id,@RequestBody UserDto userDto){
        UserDto newUserDto = userService.updateUser(id,userDto);
        return new ResponseEntity<>(newUserDto,OK);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable int id){
       userService.deleteUser(id);
        return new ResponseEntity<>("DELETE USER DONE !",OK);
    }
}
