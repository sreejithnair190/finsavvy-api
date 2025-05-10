package com.finsavvy.api.finsavvy_api.v1.dto;

import com.finsavvy.api.finsavvy_api.v1.enums.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String uuid;
    private String email;
    private String firstName;
    private String lastName;
    private List<Role> userRoles;
    private Integer is2FAEnabled = 0;
    private LocalDateTime deletedAt;
}
