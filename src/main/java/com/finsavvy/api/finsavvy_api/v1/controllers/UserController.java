package com.finsavvy.api.finsavvy_api.v1.controllers;

import com.finsavvy.api.finsavvy_api.v1.common.ApiResponse;
import com.finsavvy.api.finsavvy_api.v1.dto.user.UserDto;
import com.finsavvy.api.finsavvy_api.v1.dto.user.UserUpdateDto;
import com.finsavvy.api.finsavvy_api.v1.services.UserService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import static com.finsavvy.api.finsavvy_api.v1.utils.Constants.API_VERSION;

@RestController
@RequestMapping(API_VERSION + "/users")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<ApiResponse<UserDto>> getUserProfile() {
        UserDto userDto = userService.getUserProfile();
        ApiResponse<UserDto> apiResponse = new ApiResponse<>(
                userDto,
                "User profile retrieved successfully"
        );
        return ResponseEntity.ok(apiResponse);
    }

    @PutMapping("/me")
    public ResponseEntity<ApiResponse<UserDto>> updateUserProfile(@Valid @RequestBody UserUpdateDto userUpdateDto) {
        UserDto userDto = userService.updateUserProfile(userUpdateDto);
        ApiResponse<UserDto> apiResponse = new ApiResponse<>(
                userDto,
                "User profile updated successfully"
        );
        return ResponseEntity.ok(apiResponse);
    }

    @DeleteMapping("/me")
    public ResponseEntity<ApiResponse<Object>> deleteUserProfile() {
        String message = userService.deleteUserProfile();
        ApiResponse<Object> apiResponse = new ApiResponse<>(message);
        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/2fa")
    public ResponseEntity<ApiResponse<Object>> update2FA() {
        String message = userService.update2FA();
        ApiResponse<Object> apiResponse = new ApiResponse<>(message);
        return ResponseEntity.ok(apiResponse);
    }

    @PatchMapping("/reset-password")
    public ResponseEntity<UserDto> resetPassword() {
        return null;
    }


}
