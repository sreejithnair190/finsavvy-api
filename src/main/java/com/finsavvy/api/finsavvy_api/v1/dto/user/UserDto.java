package com.finsavvy.api.finsavvy_api.v1.dto.user;

import com.finsavvy.api.finsavvy_api.v1.enums.Role;
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
    private Boolean is2FAEnabled;
    private LocalDateTime deletedAt;
}
