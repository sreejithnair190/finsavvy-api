package com.finsavvy.api.finsavvy_api.v1.services;

import com.finsavvy.api.finsavvy_api.v1.dto.TokenDto;
import com.finsavvy.api.finsavvy_api.v1.dto.UserDto;
import com.finsavvy.api.finsavvy_api.v1.dto.requests.SignInRequestDto;
import com.finsavvy.api.finsavvy_api.v1.dto.requests.SignUpRequestDto;
import com.finsavvy.api.finsavvy_api.v1.dto.response.NewUserResponseDto;

public interface AuthService {
    NewUserResponseDto signUp(SignUpRequestDto signUpRequestDto);
    TokenDto signIn(SignInRequestDto signInRequestDto);
}
