package com.officeproject.backend.Repository;

import com.officeproject.backend.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AuthRepo extends MongoRepository<User,String> {
    User findByEmail(String email);
    boolean existsByEmail(String email);
}
