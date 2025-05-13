package com.finsavvy.api.finsavvy_api.v1.services;

import com.finsavvy.api.finsavvy_api.v1.dto.auth.TokenDto;
import com.finsavvy.api.finsavvy_api.v1.dto.auth.SignInDto;
import com.finsavvy.api.finsavvy_api.v1.dto.auth.SignUpDto;
import com.finsavvy.api.finsavvy_api.v1.dto.user.NewUserDto;

public interface AuthService {
    NewUserDto signUp(SignUpDto signUpRequestDto);
    TokenDto signIn(SignInDto signInDto);
}
