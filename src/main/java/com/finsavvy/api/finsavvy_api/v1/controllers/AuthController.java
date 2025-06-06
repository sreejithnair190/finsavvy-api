package com.finsavvy.api.finsavvy_api.v1.controllers;

import com.finsavvy.api.finsavvy_api.v1.dto.auth.TokenDto;
import com.finsavvy.api.finsavvy_api.v1.dto.auth.SignInDto;
import com.finsavvy.api.finsavvy_api.v1.dto.auth.SignUpDto;
import com.finsavvy.api.finsavvy_api.v1.dto.user.NewUserDto;
import com.finsavvy.api.finsavvy_api.v1.services.AuthService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.finsavvy.api.finsavvy_api.v1.utils.Constants.API_VERSION;

@RestController
@RequestMapping(API_VERSION + "/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/sign-up")
    public ResponseEntity<NewUserDto> signUp(
            @RequestBody @Valid SignUpDto signUpRequestDto,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        NewUserDto newUserResponseDto = authService.signUp(signUpRequestDto);
        if (newUserResponseDto != null) {
            TokenDto tokenDto = newUserResponseDto.getTokens();
            if (tokenDto != null) {
                String refreshToken = tokenDto.getRefreshToken();
                Cookie cookie = new Cookie("refresh-token", refreshToken);
                // cookie.setHttpOnly(true);
                httpServletResponse.addCookie(cookie);
            }
        }
        return new ResponseEntity<>(newUserResponseDto, HttpStatus.CREATED);
    }

    @PostMapping("/sign-in")
    public ResponseEntity<TokenDto> signIn(
            @RequestBody @Valid SignInDto signInDto,
            HttpServletRequest httpServletRequest,
            HttpServletResponse httpServletResponse
    ) {
        TokenDto tokenDto = authService.signIn(signInDto);

        Cookie cookie = new Cookie("refresh-token", tokenDto.getRefreshToken());
//        cookie.setHttpOnly(true);
        httpServletResponse.addCookie(cookie);

        return ResponseEntity.ok(tokenDto);
    }
}
