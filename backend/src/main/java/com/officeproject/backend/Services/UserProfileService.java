package com.officeproject.backend.Services;

import com.officeproject.backend.Dto.AcademicDTO;
import com.officeproject.backend.Dto.ResponseDto;
import com.officeproject.backend.Repository.AcademicRepository;
import org.springframework.stereotype.Service;

@Service
public class UserProfileService {

    private final AcademicRepository academicRepository;

    public UserProfileService(AcademicRepository academicRepository) {
        this.academicRepository = academicRepository;
    }

    public ResponseDto academic(AcademicDTO academicDTO) {
        return ResponseDto.builder().status(false).message("hellow").build();
    }
}
