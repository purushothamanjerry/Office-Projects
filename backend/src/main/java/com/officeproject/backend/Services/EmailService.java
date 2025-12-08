package com.officeproject.backend.Services;

public interface EmailService {
    void sendPasswordResetEmail(String toEmail);
    void  sendHtmlEmail(String toEMail,String subject,String htmlBody);
}
