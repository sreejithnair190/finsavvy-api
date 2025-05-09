package com.finsavvy.api.finsavvy_api.v1.services.Impl;

import com.finsavvy.api.finsavvy_api.v1.entities.User;
import com.finsavvy.api.finsavvy_api.v1.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;

@Service
public class JwtServiceImpl implements JwtService {
    @Value("${jwt.secretKey}")
    private String jwtSecretKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtSecretKey.getBytes(StandardCharsets.UTF_8));
    }

    private Claims getUserClaim(String token) {
        return Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    @Override
    public String generateToken(User user) {
        return  Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
//                .claim("role", Set.of("ADMIN", "USER"))
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + (1000 * 60 * 10)))
                .signWith(getSecretKey())
                .compact();

    }

    @Override
    public Long getUserIdFromToken(String token) {
        Claims userClaim = getUserClaim(token);
        return Long.valueOf(userClaim.getSubject());
    }
}
