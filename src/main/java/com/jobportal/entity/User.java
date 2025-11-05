package com.jobportal.entity;

import jakarta.persistence.*;
import lombok.Data;
import com.jobportal.constant.Role;

@Entity
@Table(name = "users")
@Data
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Make sure name is exactly "username" as used in mappers
    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String email;

    private String password;

    // Role is stored as enum in DB
    @Enumerated(EnumType.STRING)
    private Role role;
}
