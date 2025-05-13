package com.finsavvy.api.finsavvy_api.v1.services.Impl;

import com.finsavvy.api.finsavvy_api.v1.dto.auth.TokenDto;
import com.finsavvy.api.finsavvy_api.v1.dto.user.UserDto;
import com.finsavvy.api.finsavvy_api.v1.dto.auth.SignInDto;
import com.finsavvy.api.finsavvy_api.v1.dto.auth.SignUpDto;
import com.finsavvy.api.finsavvy_api.v1.dto.user.NewUserDto;
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


    public NewUserDto signUp(SignUpDto signUpRequestDto) {
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

        return NewUserDto.builder()
                .user(newUserDto)
                .tokens(tokenDto)
                .build();
    }

    public TokenDto signIn(SignInDto signInDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInDto.getEmail(),
                        signInDto.getPassword()
                )
        );
        User user = (User) authentication.getPrincipal();
        return jwtService.generateTokens(user);
    }
}
