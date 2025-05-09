package com.finsavvy.api.finsavvy_api.v1.services;

import com.finsavvy.api.finsavvy_api.v1.entities.User;

public interface JwtService {
    String generateToken(User user);
    Long getUserIdFromToken(String token);
}
