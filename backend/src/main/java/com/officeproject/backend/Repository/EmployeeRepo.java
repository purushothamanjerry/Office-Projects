package com.officeproject.backend.Repository;

import com.officeproject.backend.Entity.EmployeeDetails;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface EmployeeRepo extends MongoRepository<EmployeeDetails , String> {
}
