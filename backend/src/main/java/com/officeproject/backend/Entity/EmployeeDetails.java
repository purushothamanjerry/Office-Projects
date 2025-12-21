package com.officeproject.backend.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "EmployeeDetails")
public class EmployeeDetails {

    @Id
    private String id;
    private String userId;
    private int employeeId;

    private String firstName;
    private String lastName;

    private String gender;
    private LocalDate dob;

    private LocalDate doj;
    private String phoneNumber;

    private String location;
    private String department;

    private String designation;
    private String manager;

}
