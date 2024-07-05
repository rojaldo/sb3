package com.example.demo.library.user;

import java.util.List;
import java.util.Optional;

import org.springframework.boot.autoconfigure.security.SecurityProperties.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface LibraryUserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByName(String name);
    UserEntity findById(long id);
    Optional<UserEntity> findByEmail(String email);
    Optional<UserEntity> findByNameAndEmail(String name, String email);
    List<UserEntity> findByNameOrEmail(String name, String email);
    
    
}
