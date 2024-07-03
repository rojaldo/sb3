package com.example.demo.user;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    Iterable<UserDto> getUsers(){
        ArrayList<UserDto> usersDto = new ArrayList<>();
        List<UserEntity> users = userRepository.findAll();
        for (UserEntity userEntity : users) {
            usersDto.add(UserDto.builder().name(userEntity.getName()).email(userEntity.getEmail()).id(userEntity.getId()).build());
        }
        return usersDto;
    }

    public UserDto addUSer(UserDto userDto){
        Optional<UserEntity> userEntity = userRepository.findByEmail(userDto.getEmail());
        if (userEntity.isPresent()) {
            return null;
        }
        UserEntity res = userRepository.save(UserEntity.builder().name(userDto.getName()).email(userDto.getEmail()).build());
        return UserDto.builder().name(res.getName()).email(res.getEmail()).id(res.getId()).build();
    }

    public boolean deleteUser(Long id){
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (!userEntity.isPresent()) {
            return false;
        }
        userRepository.deleteById(id);
        return true;
    }

    public boolean updateUser(Long id, UserDto userDto){
        Optional<UserEntity> userEntity = userRepository.findById(id);
        if (!userEntity.isPresent()) {
            return false;
        }
        UserEntity user = userEntity.get();
        user.setEmail(userDto.getEmail());
        userRepository.save(user);
        return true;
    }

    public void initUsers(){
        userRepository.save(UserEntity.builder().name("John Doe").email("email@email.com").build());
    }
}
