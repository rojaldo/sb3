package com.example.demo.user;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Pattern;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@Table(name = "users")
public class UserEntity {
    
    @Id
    @GeneratedValue (strategy = GenerationType.AUTO)
    private long id;

    @Length(min = 3, max = 20)
    @Column(nullable = false, length = 20, name = "name")
    @Pattern(regexp = "^[a-z A-Z]*$")
    private String name;

    @Length(min = 3, max = 40)
    @Column(unique = true, length = 50, nullable = false, updatable = false)
    @Pattern(regexp = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$")
    private String email;


}
