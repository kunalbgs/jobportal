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

        // ✅ String → Enum safe conversion
        if (dto.getRole() != null && !dto.getRole().isEmpty()) {
            try {
                user.setRole(Role.valueOf(dto.getRole().trim().toUpperCase()));
            } catch (Exception e) {
                user.setRole(Role.APPLICANT);  // default
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
        // ❌ Never send password in DTO
        dto.setPassword(null);

        // ✅ Enum → String conversion
        dto.setRole(user.getRole() != null ? user.getRole().name() : null);

        return dto;
    }
}
