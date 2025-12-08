package com.officeproject.backend.Services;

import com.officeproject.backend.Dto.ResponseDto;
import com.officeproject.backend.Entity.PasswordReset;
import com.officeproject.backend.Entity.User;
import com.officeproject.backend.Repository.AuthRepo;
import com.officeproject.backend.Repository.PasswordResetRepository;
import com.officeproject.backend.Utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.util.StringUtils;

import java.time.Duration;
import java.time.Instant;
import java.util.Optional;


@Service
public class AuthService {

    @Autowired
    private AuthRepo authRepo;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Autowired
    private EmailServiceImpl emailService;
    @Autowired
    private PasswordResetRepository passwordResetRepository;
    private final long Token_Expiry_Minutes=15;
    private final String RESET_BASE_URL="http://localhost:5173/NewPassword";



    public ResponseDto addUser(User user) {
        if(user==null || !StringUtils.hasText(user.getEmail())|| !StringUtils.hasText(user.getPassword()))
            return ResponseDto.builder().status(false).message("Missing Data").build();
        if (authRepo.existsByEmail(user.getEmail())) {
            return ResponseDto.builder().status(false).message("User Already Exist").build();
        }
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        authRepo.save(user);
        return ResponseDto.builder().status(true).message("Registered Successfully").build();
    }

    public ResponseDto login(User user) {
        if(user==null || !StringUtils.hasText(user.getEmail())|| !StringUtils.hasText(user.getPassword()))
            return ResponseDto.builder().status(false).message("Missing Data").build();
        User user1 = authRepo.findByEmail(user.getEmail());
        if (user1 != null && passwordEncoder.matches(user.getPassword(), user1.getPassword())) {
            return ResponseDto.builder().status(true).message("Login Success").build();
        }

        return ResponseDto.builder().status(false).message("invalid credentials").build();
    }


    public ResponseDto forgotpassword(String email) {
        boolean isexist= authRepo.existsByEmail(email);
        if(!isexist){
            return ResponseDto.builder().status(false).message("Invalid email ").build();
        }
        passwordResetRepository.deleteByEmail(email);
        String token= TokenUtil.generateToken();
        String tokenHash=TokenUtil.sha256Hex(token);
        Instant now= Instant.now();
        Instant expiresAt=Instant.now().plus(Duration.ofMinutes(Token_Expiry_Minutes));
        PasswordReset pr=new PasswordReset();
        pr.setEmail(email);
        pr.setTokenHash(tokenHash);
        pr.setExpiryAt(expiresAt);
        pr.setCreatedAt(now);
        pr.setUsed(false);
        passwordResetRepository.save(pr);
        String link=RESET_BASE_URL+"?token="+token;
        String html="<p>You requested a password reset. Click link below (valid for "
                + Token_Expiry_Minutes+ " minutes):</p>"
                + "<p><a href=\"" + link + "\">Reset password</a></p>"
                + "<p>If you didn't request this, ignore this email.</p>";

        emailService.sendHtmlEmail(email,"Password Reset Request",html);
        return ResponseDto.builder().status(true).message("Check your email").build();
    }
    public ResponseDto verifyAndReset(String token , String newPassword){
        if(token==null || token.isEmpty() || newPassword==null || newPassword.isEmpty()){
            return ResponseDto.builder().status(false).message("Missing Data").build();
        }
        String tokenHash=TokenUtil.sha256Hex(token);
        Optional<PasswordReset>optpr=passwordResetRepository.findByTokenHash(tokenHash);
        if(optpr.isEmpty()){
            return ResponseDto.builder().status(false).message("Invalid Token").build();
        }
        PasswordReset pr= optpr.get();
        if(pr.isUsed()){
            return ResponseDto.builder().status(false).message("Token Already Used").build();
        }
        Instant now=Instant.now();
        if(pr.getExpiryAt()==null || pr.getExpiryAt().isBefore(now)){
            return ResponseDto.builder().status(false).message("Token Expired").build();
        }
        String email=pr.getEmail();
        User user=authRepo.findByEmail(email);
        if(user==null){
            return ResponseDto.builder().status(false).message("User Not Found").build();
        }
        String encode=passwordEncoder.encode(newPassword);
        user.setPassword(encode);
        authRepo.save(user);
        pr.setUsed(true);
        passwordResetRepository.save(pr);
        String html = "<p>Your password has been changed successfully. If you did not perform this action, contact support immediately.</p>";
        try {
            emailService.sendHtmlEmail(email, "Password Changed", html);
        } catch (Exception ignored) {
        }

        return ResponseDto.builder()
                .status(true)
                .message("Password updated successfully")
                .build();
    }
    }

