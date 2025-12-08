package com.officeproject.backend.Services;

import com.officeproject.backend.Entity.User;
import com.officeproject.backend.Repository.AuthRepo;
import com.officeproject.backend.Repository.PasswordResetRepository;
import jakarta.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

@Service
public class EmailServiceImpl implements EmailService{

    @Autowired(required = false)
    private JavaMailSender mailSender;

    @Autowired
    private PasswordResetRepository passwordResetRepository;
    @Autowired
    private AuthRepo authRepo;
    @Override
    public void sendPasswordResetEmail(String toEmail) {
    if(mailSender==null){
        return;
    }
    try {
        MimeMessage msg=mailSender.createMimeMessage();
        MimeMessageHelper helper=new MimeMessageHelper(msg,true,"UTF-8");
        helper.setTo(toEmail);
        helper.setSubject("Password reset request");
        String html="<p>We received a request to reset your password.</p>"
                + "<p>Click the link below to reset (valid for 15 minutes):</p>"
                 + "<p>If you didn't request this, ignore this email.</p>";
        helper.setText(html,true);
        mailSender.send(msg);
    }catch (Exception e){
        e.printStackTrace();
    }
    }

    @Override
    public void sendHtmlEmail(String toEMail, String subject, String htmlBody) {
        if(mailSender==null){
            return;
        }
        try {
            MimeMessage msg=mailSender.createMimeMessage();
            MimeMessageHelper helper=new MimeMessageHelper(msg,true,"UTF-8");
            helper.setTo(toEMail);
            helper.setSubject(subject);
            helper.setText(htmlBody,true);
            mailSender.send(msg);
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
