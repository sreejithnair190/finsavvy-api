package com.finsavvy.api.finsavvy_api.v1.dto.user;

import jakarta.validation.constraints.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserUpdateDto {
    @NotNull(message = "Email is required")
    @NotBlank(message = "Email cannot be empty")
    @Size(max = 255, message = "Email cannot exceed 255 characters")
    @Email(message = "Email should be a valid email")
    private String email;

    @NotNull(message = "First name is required")
    @NotBlank(message = "First name cannot be empty")
    @Size(min = 3, max = 50, message = "Number of characters in first name should be in range (3-50)")
    @Pattern(
            regexp = "^[a-zA-Z]+$",
            message = "First name can only contain letters"
    )
    private String firstName;

    @NotNull(message = "Last name is required")
    @NotBlank(message = "Last name cannot be empty")
    @Size(min = 3, max = 50, message = "Number of characters in last name should be in range (3-50)")
    @Pattern(
            regexp = "^[a-zA-Z]+$",
            message = "Last name can only contain letters"
    )
    private String lastName;
}
