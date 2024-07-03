package com.example.demo.user;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findByName(String name);
    Optional<UserEntity> findByEmail(String email);
    List<UserEntity> findByNameAndEmail(String name, String email);
    List<UserEntity> findByNameOrEmail(String name, String email);
    
    
}
