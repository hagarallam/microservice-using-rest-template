package com.example.usersservice.service;

import com.example.usersservice.dto.UserDto;
import com.example.usersservice.model.User;
import com.example.usersservice.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService {

    private UserRepository userRepository ;


    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDto getUser(int id){
        UserDto userDto = new UserDto();
        Optional<User> user =  userRepository.findById(id);
        if(user.isPresent()){
            mapUserToDto(user.get(),userDto);
        }
        return userDto;
    }

    public List<UserDto> getAllUsers() {
        List<User> userList;
        List<UserDto> userDtoList = new ArrayList<>();
        userList = userRepository.findAll();
        if(Objects.nonNull(userList) && !userList.isEmpty()){
            userDtoList =mapUserListToDto(userList);
        }
        return userDtoList;
    }



    public UserDto addUser(UserDto userDto) {
        User user = mapUserDtoToUser(userDto);
        User newUser = userRepository.save(user);
        mapUserToDto(newUser,userDto);
        return userDto;
    }
    public UserDto updateUser(int id, UserDto userDto) {
        Optional<User> user = userRepository.findById(id);
        if(user.isPresent()){
            user.get().setName(userDto.getName());
            user.get().setAge(userDto.getAge());
            User updatedUser = userRepository.save(user.get());
            mapUserToDto(updatedUser,userDto);
        }
        return userDto;
    }

    public void deleteUser(int id) {
        userRepository.deleteById(id);
    }
    private User mapUserDtoToUser(UserDto userDto) {
        User user = new User();
        user.setName(userDto.getName());
        user.setAge(userDto.getAge());
        user.setDescription("TEST");
        return user;
    }

    private void mapUserToDto(User user, UserDto userDto) {
        userDto.setId(user.getId());
        userDto.setName(user.getName());
        userDto.setAge(user.getAge());
    }

    private List<UserDto> mapUserListToDto(List<User> userList) {
        List<UserDto> userDtoList = userList.stream().map(user -> {
            UserDto userDto = new UserDto(user.getId(), user.getName(), user.getAge());
            return userDto;
        }).collect(Collectors.toList());
        return userDtoList;
    }



}
