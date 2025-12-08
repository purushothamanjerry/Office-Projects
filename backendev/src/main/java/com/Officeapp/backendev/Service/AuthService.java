package com.Officeapp.backendev.Service;

import com.Officeapp.backendev.DTO.Response;
import com.Officeapp.backendev.Entity.User;
import com.Officeapp.backendev.Repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

@Service
public class AuthService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public AuthService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public ResponseEntity<?> addUser(User user) {
        // Basic validation
        if (user == null || !StringUtils.hasText(user.getEmail()) || !StringUtils.hasText(user.getPassword())) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new Response(false, "Email and password are required"));
        }

        // Normalize phone (store digits-only) if provided
        if (StringUtils.hasText(user.getPhone())) {
            String normalizedPhone = user.getPhone().replaceAll("[^0-9]", "");
            user.setPhone(normalizedPhone);
        }

        // Check duplicates
        if (userRepository.existsByEmail(user.getEmail())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response(false, "Email already registered"));
        }
        if (StringUtils.hasText(user.getPhone()) && userRepository.existsByPhone(user.getPhone())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(new Response(false, "Phone already registered"));
        }

        // Encode password
        String rawPassword = user.getPassword();
        user.setPassword(passwordEncoder.encode(rawPassword));

        // Set default role/status if not provided
        if (!StringUtils.hasText(user.getRole())) {
            user.setRole("user");
        }
        if (!StringUtils.hasText(user.getStatus())) {
            user.setStatus("pending");
        }

        // Save user
        User saved = userRepository.save(user);

        // Clear password before returning
        saved.setPassword(null);

        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    public ResponseEntity<?> login(User user) {
        // 1) Basic request validation
        if (user == null
                || (!StringUtils.hasText(user.getEmail()) && !StringUtils.hasText(user.getPhone()))
                || !StringUtils.hasText(user.getPassword())) {
            return ResponseEntity.ok(new Response(false, "Identifier (email or phone) and password are required"));
        }

        // 2) Determine identifier and normalize phone if needed
        String identifier;
        if (StringUtils.hasText(user.getEmail())) {
            identifier = user.getEmail().trim();
        } else {
            identifier = user.getPhone().replaceAll("[^0-9]", "").trim();
        }

        // 3) Lookup user (by email or phone)
        User found = identifier.contains("@")
                ? userRepository.findByEmail(identifier)
                : userRepository.findByPhone(identifier);

        // 4) If not found -> generic invalid credentials (prevents enumeration)
        if (found == null) {
            return ResponseEntity.ok(new Response(false, "Invalid credentials"));
        }

        // 5) Verify password (raw, encoded)
        if (!passwordEncoder.matches(user.getPassword(), found.getPassword())) {
            return ResponseEntity.ok(new Response(false, "Invalid credentials"));
        }

        // 6) Check account status safely
        String status = found.getStatus();
        if (status == null || !"active".equalsIgnoreCase(status)) {
            String msg = (status == null) ? "Account not active" : status;
            return ResponseEntity.ok(new Response(false, msg));
        }

        // 7) Success — do NOT include password in response
        // Optionally you can return user info (without password) in future via a DTO
        return ResponseEntity.ok(new Response(true, "Login successful"));
    }
}
