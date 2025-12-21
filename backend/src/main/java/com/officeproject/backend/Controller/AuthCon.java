package com.officeproject.backend.Controller;

import com.officeproject.backend.Dto.AuthResponseDto;
import com.officeproject.backend.Dto.ForgotPasswordRequest;
import com.officeproject.backend.Dto.ResetRequest;
import com.officeproject.backend.Dto.ResponseDto;
import com.officeproject.backend.Entity.User;
import com.officeproject.backend.Services.AuthService;
import com.officeproject.backend.Utils.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@CrossOrigin("*")
public class AuthCon {

    private final AuthService authService;

    public AuthCon(AuthService authService) {
        this.authService = authService;
    }

    @GetMapping("/test")
    public String test(){
        return "App working";
    }

    @PostMapping("/register")
    public ResponseDto register(@RequestBody User user){
    return authService.addUser(user);
    }
    @PostMapping("/login")
    public ResponseEntity<AuthResponseDto> login(@RequestBody User user) {
        return ResponseEntity.ok(authService.login(user));
    }

    @PostMapping("/forgot")
    public ResponseEntity<ResponseDto>forgotpassword(@RequestBody ForgotPasswordRequest req){
    ResponseDto resp=authService.forgotpassword(req.getEmail());
    return ResponseEntity.ok(resp);
    }
    @PostMapping("/forgot/reset")
    public ResponseEntity<ResponseDto>resetPassword(@RequestBody ResetRequest resetRequest){
        ResponseDto resp=authService.verifyAndReset(resetRequest.getToken(),resetRequest.getNewPassword());
        return ResponseEntity.ok(resp);
    }
    @GetMapping("/me")
    public ResponseEntity<AuthResponseDto> me(
            @RequestHeader("Authorization") String token) {

        if (token.startsWith("Bearer ")) {
            token = token.substring(7);
        }

        if (!JwtUtil.validateToken(token)) {
            return ResponseEntity.status(401).build();
        }

        String userId = JwtUtil.extractUserId(token);
        String email = JwtUtil.extractEmail(token);

        return ResponseEntity.ok(
                AuthResponseDto.builder()
                        .status(true)
                        .message("Token valid")
                        .userId(userId)
                        .email(email)
                        .build()
        );
    }

}
