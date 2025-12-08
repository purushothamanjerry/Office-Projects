package com.Officeapp.backendev.DTO;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.context.annotation.Bean;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Response {

    private  boolean status;
    private String message;

}
