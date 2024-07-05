package com.example.demo.library.user;

import java.util.List;

import org.hibernate.validator.constraints.Length;
import org.springframework.beans.factory.annotation.Value;

import com.example.demo.library.lends.LendEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
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
@Table(name = "library_users")
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

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "user")
    private List<LendEntity> lends;


}
