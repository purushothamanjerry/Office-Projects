package com.officeproject.backend.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Document(collection = "Academic")
public class Academic {

    private String id;
    private String qualification;
    private String institute;
    private String education_board;
    private String specialization;
    private int from_year;
    private int to_year;
    private double cgpa;

}
