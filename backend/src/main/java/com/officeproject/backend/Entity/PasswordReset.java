package com.officeproject.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.security.PrivateKey;
import java.time.Instant;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Document(collection = "PasswordReset")
public class PasswordReset {

    @Id
    private  String id;
    private String email;
    private String tokenHash;
    private Instant createdAt;
    private Instant expiryAt;
    private boolean  used=false;
}
