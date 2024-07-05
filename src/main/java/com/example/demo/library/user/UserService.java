package com.example.demo.library.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService {

    @Autowired
    private LibraryUserRepository userRepository;
    
    Iterable<UserDto> getUsers(){
        ArrayList<UserDto> usersDto = new ArrayList<>();
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity userEntity : users) {
            usersDto.add(UserDto.builder().name(userEntity.getName()).email(userEntity.getEmail()).id(userEntity.getId()).address(userEntity.getAddress()).build());
        }
        return usersDto;
    }

    public UserDto getUserById (Long id){
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (!userEntity.isPresent()) {
            return null;
        }
        return UserDto.builder().name(userEntity.get().getName()).email(userEntity.get().getEmail()).id(userEntity.get().getId()).address(userEntity.get().getAddress()).build();
    }

    public UserDto addUSer(UserDto userDto){
        Optional<UserEntity> userEntity = userRepository.findByEmail(userDto.getEmail());
        if (userEntity.isPresent()) {
            return null;
        }
        log.info("User added: " + userDto.getAddress().toString());
        UserEntity res = userRepository.save(UserEntity.builder().name(userDto.getName()).email(userDto.getEmail()).address(userDto.getAddress()).build());
        return UserDto.builder().name(res.getName()).email(res.getEmail()).id(res.getId()).address(res.getAddress()).build();
    }

    public UserDto deleteUser(Long id){
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (!userEntity.isPresent()) {
            return null;
        }
        userRepository.deleteById(id);
        return UserDto.builder().name(userEntity.get().getName()).email(userEntity.get().getEmail()).id(userEntity.get().getId()).build();
    }

    public UserDto updateUser(Long id, UserDto userDto){
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (!userEntity.isPresent() || !userEntity.get().getEmail().equals(userDto.getEmail())) {
            return null;
        }
        UserEntity user = userEntity.get();
        user.setName(userDto.getName());
        userRepository.save(user);
        return UserDto.builder().name(user.getName()).email(user.getEmail()).id(user.getId()).build();
    }

    public void initUsers(){
        userRepository.save(UserEntity.builder().name("John Doe").email("email@email.com").build());
    }
}
