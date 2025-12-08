package com.officeproject.backend.Dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class ResponseDto {

    private boolean status;
    private String message;

}
