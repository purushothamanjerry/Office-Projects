package com.officeproject.backend.Dto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class AuthResponseDto {

    private boolean status;
    private String message;

    private String token;   // JWT
    private String userId;  // for employee linking
    private String email;
}
