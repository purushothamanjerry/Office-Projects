package com.Officeapp.backendev.Repository;

import com.Officeapp.backendev.Entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    // Custom finders for login or checking existing user
    User findByEmail(String email);
    User findByPhone(String phone);
    boolean existsByEmail(String email);
    boolean existsByPhone(String phone);
}
