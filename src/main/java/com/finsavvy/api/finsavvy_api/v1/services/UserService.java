package com.finsavvy.api.finsavvy_api.v1.services;

import com.finsavvy.api.finsavvy_api.v1.dto.user.UserDto;
import com.finsavvy.api.finsavvy_api.v1.dto.user.UserUpdateDto;
import com.finsavvy.api.finsavvy_api.v1.entities.User;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
    User getUserByUserId(Long userId);
    UserDto getUserProfile();
    UserDto updateUserProfile(UserUpdateDto userUpdateDto);
    String deleteUserProfile();
    String update2FA();
}
