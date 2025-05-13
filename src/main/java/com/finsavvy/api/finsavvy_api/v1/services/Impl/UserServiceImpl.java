package com.finsavvy.api.finsavvy_api.v1.services.Impl;

import com.finsavvy.api.finsavvy_api.v1.dto.user.UserDto;
import com.finsavvy.api.finsavvy_api.v1.dto.user.UserUpdateDto;
import com.finsavvy.api.finsavvy_api.v1.entities.User;
import com.finsavvy.api.finsavvy_api.v1.exceptions.ResourceAlreadyDeletedException;
import com.finsavvy.api.finsavvy_api.v1.exceptions.ResourceNotFoundException;
import com.finsavvy.api.finsavvy_api.v1.repositories.UserRepository;
import com.finsavvy.api.finsavvy_api.v1.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

import static com.finsavvy.api.finsavvy_api.v1.utils.Auth.getAuthenticatedUser;
import static com.finsavvy.api.finsavvy_api.v1.utils.Auth.getAuthenticatedUserId;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByEmail(username)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with email: " + username));
    }

    @Override
    public User getUserByUserId(Long userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userId: " + userId));
    }

    @Override
    public UserDto getUserProfile() throws SecurityException{
        User user =  getAuthenticatedUser();
        return modelMapper.map(user, UserDto.class);
    }

    @Override
    public UserDto updateUserProfile(UserUpdateDto userUpdateDto) {
        Long userId = getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userId: " + userId));
        user.setEmail(userUpdateDto.getEmail());
        user.setFirstName(userUpdateDto.getFirstName());
        user.setLastName(userUpdateDto.getLastName());
        User updatedUser = userRepository.save(user);
        return modelMapper.map(updatedUser, UserDto.class);
    }

    @Override
    public String deleteUserProfile() {
        Long userId = getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userId: " + userId));
        LocalDateTime isUserDeleted = user.getDeletedAt();
        if (isUserDeleted != null) {
            throw new ResourceAlreadyDeletedException("User account already deleted");
        }
        user.setDeletedAt(LocalDateTime.now());
        userRepository.save(user);
        return "Account Deleted Successfully";
    }

    @Override
    public String update2FA() {

        Long userId = getAuthenticatedUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ResourceNotFoundException("User not found with userId: " + userId));
        String message = user.getIs2FAEnabled() ?  "2FA has been disabled" : "2FA has been enabled";
        user.setIs2FAEnabled(!user.getIs2FAEnabled());
        userRepository.save(user);
        return message;
    }
}
