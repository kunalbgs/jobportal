package com.jobportal.dto;

import lombok.Data;

@Data
public class UserDTO {
    private Long id;
    private String username;
    private String email;
    private String password; // careful: avoid sending password back in responses
    private String role; // API uses String for role
}
