package com.finsavvy.api.finsavvy_api.v1.services.Impl;

import com.finsavvy.api.finsavvy_api.v1.dto.TokenDto;
import com.finsavvy.api.finsavvy_api.v1.dto.UserDto;
import com.finsavvy.api.finsavvy_api.v1.dto.requests.SignInDto;
import com.finsavvy.api.finsavvy_api.v1.dto.requests.SignUpDto;
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


    public UserDto signUp(SignUpDto signUpDto) {
        Optional<User> user = userRepository.findByEmail(signUpDto.getEmail());

        if (user.isPresent()){
            throw new BadCredentialsException("User with email already exists: " + signUpDto.getEmail());
        }

        User newUser = modelMapper.map(signUpDto, User.class);
        String encodedPassword = passwordEncoder.encode(newUser.getPassword());
        newUser.setPassword(encodedPassword);

        User savedUser = userRepository.save(newUser);

        return modelMapper.map(savedUser, UserDto.class);
    }

    public TokenDto signIn(SignInDto signInDto) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        signInDto.getEmail(),
                        signInDto.getPassword()
                )
        );
        User user = (User) authentication.getPrincipal();
        String token = jwtService.generateToken(user);
        return new TokenDto(token);
    }
}
