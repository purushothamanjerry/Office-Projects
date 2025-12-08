package com.officeproject.backend.Controller;

import com.officeproject.backend.Dto.AcademicDTO;
import com.officeproject.backend.Dto.ResponseDto;
import com.officeproject.backend.Services.UserProfileService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserProfileCon {

    private final UserProfileService userProfileService;

    public UserProfileCon(UserProfileService userProfileService) {
        this.userProfileService = userProfileService;
    }

    @PostMapping("/academic")
    public ResponseDto acdamic(@RequestBody AcademicDTO academicDTO){
        ResponseDto res=userProfileService.academic(academicDTO);
        return res;
    }

}
