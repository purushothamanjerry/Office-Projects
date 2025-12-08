package com.officeproject.backend.Utils;

import org.apache.logging.log4j.message.Message;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.SecureRandom;
import java.util.Base64;

public class TokenUtil {

    private static final SecureRandom secureRandom=new SecureRandom();

    public static String generateToken(){
    byte[]bytes=new byte[32];
    secureRandom.nextBytes(bytes);
    return Base64.getUrlEncoder().withoutPadding().encodeToString(bytes);
    }

    public static String sha256Hex(String input){
        try{
            MessageDigest md=MessageDigest.getInstance("SHA-256");
            byte[]digest=md.digest(input.getBytes(StandardCharsets.UTF_8));
            StringBuilder sb=new StringBuilder();
            for(byte b:digest){
                sb.append(String.format("%02x",b));
            }
            return sb.toString();
        }catch (Exception e){
            throw  new RuntimeException();
        }
    }
}
