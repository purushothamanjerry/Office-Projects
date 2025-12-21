package com.officeproject.backend.Entity;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@AllArgsConstructor
@Getter
@Setter
@NoArgsConstructor

@Document(collection = "User")
public class User {
    @Id
    private String id;
    private String name;
    private String email;
    private String password;

}
