package com.Officeapp.backendev.Controller;

import com.Officeapp.backendev.Entity.User;
import com.Officeapp.backendev.Service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin(origins = "*")
public class AuthCon {

    private final AuthService authService;

    @Autowired
    public AuthCon(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/test")
    public String test() {
        return "Testing Successfully";
    }

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return authService.addUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<?>login(@RequestBody User user) {
        return authService.login(user);
    }
}
