package com.finsavvy.api.finsavvy_api.v1.services;

import com.finsavvy.api.finsavvy_api.v1.dto.TokenDto;
import com.finsavvy.api.finsavvy_api.v1.dto.UserDto;
import com.finsavvy.api.finsavvy_api.v1.dto.requests.SignInDto;
import com.finsavvy.api.finsavvy_api.v1.dto.requests.SignUpDto;

public interface AuthService {
    UserDto signUp(SignUpDto signUpDto);
    TokenDto signIn(SignInDto signInDto);
}
