package com.auth.constant;

/**
 * Application constants for JWT authentication microservice.
 * Contains all constant values used throughout the application.
 */
public class AppConstants {
    
    // JWT Constants
    public static final String JWT_SECRET_KEY = "${jwt.secret}";
    public static final long JWT_EXPIRATION_MS = 86400000; // 24 hours
    public static final String JWT_TOKEN_PREFIX = "Bearer ";
    public static final String JWT_HEADER_STRING = "Authorization";
    
    // API Endpoints
    public static final String API_BASE_PATH = "/api/v1";
    public static final String AUTH_ENDPOINT = "/auth/**";
    public static final String PUBLIC_ENDPOINTS = "/api/v1/auth/**";
    
    // User Roles
    public static final String ROLE_ADMIN = "ADMIN";
    public static final String ROLE_USER = "USER";
    
    // HTTP Status Messages
    public static final String SUCCESS_MESSAGE = "Operation completed successfully";
    public static final String ERROR_MESSAGE = "An error occurred";
    public static final String UNAUTHORIZED_MESSAGE = "Unauthorized access";
    public static final String FORBIDDEN_MESSAGE = "Access forbidden";
    public static final String NOT_FOUND_MESSAGE = "Resource not found";
    
    // Validation Messages
    public static final String INVALID_USERNAME_PASSWORD = "Invalid username or password";
    public static final String USER_NOT_FOUND = "User not found";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String INVALID_TOKEN = "Invalid or expired token";
    
    // Database Constants
    public static final String USER_TABLE = "users";
    public static final int USERNAME_MIN_LENGTH = 3;
    public static final int USERNAME_MAX_LENGTH = 50;
    public static final int PASSWORD_MIN_LENGTH = 6;
    
    // Application Info
    public static final String APP_NAME = "JWT Authentication Microservice";
    public static final String APP_VERSION = "1.0.0";
    public static final String APP_DESCRIPTION = "Java microservice with JWT authentication and role-based access control";
    
    // Prevent instantiation
    private AppConstants() {
        throw new UnsupportedOperationException("This is a utility class and cannot be instantiated");
    }
}
