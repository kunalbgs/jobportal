package com.jobportal.mapper;

import com.jobportal.dto.UserDTO;
import com.jobportal.entity.User;
import com.jobportal.constant.Role;

public class UserMapper {

    public static User toEntity(UserDTO dto) {
        if (dto == null) return null;
        User user = new User();
        user.setId(dto.getId());
        user.setUsername(dto.getUsername());
        user.setEmail(dto.getEmail());
        user.setPassword(dto.getPassword());
        // Convert role String -> Role enum safely
        if (dto.getRole() != null) {
            try {
                user.setRole(Role.valueOf(dto.getRole().toUpperCase()));
            } catch (IllegalArgumentException e) {
                // default role if invalid (choose as you need)
                user.setRole(Role.APPLICANT);
            }
        } else {
            user.setRole(Role.APPLICANT);
        }
        return user;
    }

    public static UserDTO toDto(User user) {
        if (user == null) return null;
        UserDTO dto = new UserDTO();
        dto.setId(user.getId());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        // do NOT set password on response in production; set only if needed
        // dto.setPassword(user.getPassword());
        // Convert Role enum -> String (null-safe)
        dto.setRole(user.getRole() != null ? user.getRole().name() : null);
        return dto;
    }
}
