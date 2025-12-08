package com.officeproject.backend.Repository;

import com.officeproject.backend.Entity.PasswordReset;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PasswordResetRepository extends MongoRepository<PasswordReset,String> {
    Optional<PasswordReset>findByTokenHash(String tokenHash);
    void deleteByEmail(String email);
}
