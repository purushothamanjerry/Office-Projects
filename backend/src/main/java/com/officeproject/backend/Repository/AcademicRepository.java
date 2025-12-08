package com.officeproject.backend.Repository;

import com.officeproject.backend.Entity.Academic;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface AcademicRepository extends MongoRepository <Academic,String>{

}
