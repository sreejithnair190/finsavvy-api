package com.finsavvy.api.finsavvy_api.v1.filters;

import com.finsavvy.api.finsavvy_api.v1.entities.User;
import com.finsavvy.api.finsavvy_api.v1.services.JwtService;
import com.finsavvy.api.finsavvy_api.v1.services.UserService;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
@RequiredArgsConstructor
public class AuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final UserService userService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String requestTokenHeader = request.getHeader("Authorization");

        // Check if Authorization header is missing or doesn't have the correct format
        if (requestTokenHeader == null || !requestTokenHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);
            return; // Exit early to avoid further processing
        }

        String token = requestTokenHeader.substring(7); // "Bearer " is exactly 7 characters

        Long userId = jwtService.getUserIdFromToken(token);

        // If we couldn't extract a userId or authentication is already set, continue the filter chain
        if (userId == null || SecurityContextHolder.getContext().getAuthentication() != null) {
            filterChain.doFilter(request, response);
            return;
        }

        User user = userService.getUserByUserId(userId);

        // If user is found, set up the authentication
        if (user != null) {
            UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                    user, null, user.getAuthorities() // Assuming User implements UserDetails, otherwise pass null for authorities
            );
            authentication.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );
            SecurityContextHolder.getContext().setAuthentication(authentication);
        }

        // Always continue the filter chain
        filterChain.doFilter(request, response);
    }
}