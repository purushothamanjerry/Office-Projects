package com.officeproject.backend.Controller;

import com.officeproject.backend.Dto.ResponseDto;
import com.officeproject.backend.Entity.EmployeeDetails;
import com.officeproject.backend.Services.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/profile")
public class UserProfileCon {

    @Autowired
    public AuthService authService;

    @PostMapping("/empdetails")
    public  ResponseDto empDetails(@RequestBody EmployeeDetails employeeDetails,String userId){
        return authService.createEmployee(employeeDetails,userId);
    }

}
