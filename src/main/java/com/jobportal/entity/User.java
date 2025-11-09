package com.jobportal.entity;

import com.jobportal.constant.Role;
import jakarta.persistence.*;
import lombok.Data;

@Entity @Table(name="users")
@Data
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable=false, unique=true)
    private String username;

    @Column(nullable=false, unique=true)
    private String email;

    private String password;               // may be null for OAuth users

    @Enumerated(EnumType.STRING)
    private Role role;                     // APPLICANT/EMPLOYER/ADMIN

    private String provider;               // google/linkedin/local
    private String providerId;             // provider unique id
    private String name;                   // display name
    private String picture;                // avatar URL
}
