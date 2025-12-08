package com.officeproject.backend.Dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Service
public class ResetRequest {

    private String token;
    private String newPassword;

}
