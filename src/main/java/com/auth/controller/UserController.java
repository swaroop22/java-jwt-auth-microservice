package com.auth.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;
import java.util.HashMap;
import java.util.Map;

@RestController
public class UserController {

    @GetMapping("/api/user/profile")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getUserProfile(Principal principal) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "User profile endpoint");
        response.put("username", principal.getName());
        response.put("access", "USER or ADMIN");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/admin/dashboard")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAdminDashboard(Principal principal) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "Admin dashboard endpoint");
        response.put("username", principal.getName());
        response.put("access", "ADMIN ONLY");
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/user/info")
    @PreAuthorize("hasAnyRole('USER', 'ADMIN')")
    public ResponseEntity<?> getUserInfo(Principal principal) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "User information");
        response.put("username", principal.getName());
        return ResponseEntity.ok(response);
    }

    @GetMapping("/api/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUsers(Principal principal) {
        Map<String, String> response = new HashMap<>();
        response.put("message", "List of all users");
        response.put("access", "ADMIN ONLY - Full access to manage users");
        return ResponseEntity.ok(response);
    }
}
