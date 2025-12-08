package com.officeproject.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class AcademicDTO {

    private String qualification;
    private String institute;
    private String education_board;
    private String specialization;
    private int from_year;
    private int to_year;
    private double cgpa;

}
