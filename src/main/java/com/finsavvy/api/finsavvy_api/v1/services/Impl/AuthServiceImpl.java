package com.finsavvy.api.finsavvy_api.v1.services.Impl;

import com.finsavvy.api.finsavvy_api.v1.dto.TokenDto;
import com.finsavvy.api.finsavvy_api.v1.dto.UserDto;
import com.finsavvy.api.finsavvy_api.v1.dto.requests.SignInRequestDto;
import com.finsavvy.api.finsavvy_api.v1.dto.requests.SignUpRequestDto;
import com.finsavvy.api.finsavvy_api.v1.dto.response.NewUserResponseDto;
import com.finsavvy.api.finsavvy_api.v1.entities.User;
import com.finsavvy.api.finsavvy_api.v1.repositories.UserRepository;
import com.finsavvy.api.finsavvy_api.v1.services.AuthService;
import com.finsavvy.api.finsavvy_api.v1.services.JwtService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final ModelMapper modelMapper;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;


    public NewUserResponseDto signUp(SignUpRequestDto signUpRequestDto) {
        Optional<User> user = userRepository.findByEmail(signUpRequestDto.getEmail());

        if (user.isPresent()){
            throw new BadCredentialsException("User with email already exists: " + signUpRequestDto.getEmail());
        }

        User newUser = modelMapper.map(signUpRequestDto, User.class);
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        User savedUser = userRepository.save(newUser);
        UserDto newUserDto =  modelMapper.map(savedUser, UserDto.class);
        TokenDto tokenDto = jwtService.generateTokens(savedUser);

        return NewUserResponseDto.builder()
                .user(newUserDto)
                .tokens(tokenDto)
                .build();
    }

    public TokenDto signIn(SignInRequestDto signInRequestDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInRequestDto.getEmail(),
                        signInRequestDto.getPassword()
                )
        );
        User user = (User) authentication.getPrincipal();
        return jwtService.generateTokens(user);
    }
}
