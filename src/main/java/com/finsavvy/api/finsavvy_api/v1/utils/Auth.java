package com.finsavvy.api.finsavvy_api.v1.utils;

import com.finsavvy.api.finsavvy_api.v1.entities.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class Auth {

    /**
     * Get the authenticated user object from the security context
     * @return the User object if available
     * @throws SecurityException if no authentication is found
     */
    public static User getAuthenticatedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication == null) {
            throw new SecurityException("No authentication found in context");
        }

        Object principal = authentication.getPrincipal();
        if (principal instanceof User) {
            return (User) principal;
        }

        throw new SecurityException("Unable to retrieve authenticated user");
    }

    /**
     * Get the ID of the authenticated user
     * @return the user ID
     * @throws SecurityException if no authentication is found
     */
    public static Long getAuthenticatedUserId() {
        return getAuthenticatedUser().getId();
    }
}