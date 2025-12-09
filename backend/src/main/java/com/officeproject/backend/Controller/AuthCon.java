package com.officeproject.backend.Controller;

import com.officeproject.backend.Dto.ForgotPasswordRequest;
import com.officeproject.backend.Dto.ResetRequest;
import com.officeproject.backend.Dto.ResponseDto;
import com.officeproject.backend.Entity.User;
import com.officeproject.backend.Services.AuthService;
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
    public ResponseDto login(@RequestBody User user){
        return authService.login(user);
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
}
